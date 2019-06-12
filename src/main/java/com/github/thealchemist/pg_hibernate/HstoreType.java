package com.github.thealchemist.pg_hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import org.postgresql.util.HStoreConverter;

// courtesy of: http://backtothefront.net/2011/storing-sets-keyvalue-pairs-single-db-column-hibernate-postgresql-hstore-type/
public class HstoreType implements UserType {

    public HstoreType() {
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        if(o == null) {
            return null;
        }
        else {
            // It's not a true deep copy, but we store only String instances, and they
            // are immutable, so it should be OK
            Map m = (Map) o;
            return new HashMap(m);
        }
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public boolean equals(Object o1, Object o2) throws HibernateException {
        Map m1 = (Map) o1;
        Map m2 = (Map) o2;
        return m1.equals(m2);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sessionImplementor, Object owner) throws HibernateException, SQLException {
        String col = names[0];
        String val = resultSet.getString(col);
        return val == null ? HStoreConverter.fromString("") : HStoreConverter.fromString(val);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {
        String s = HStoreConverter.toString((Map) value);
        preparedStatement.setObject(i, s, Types.OTHER);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @Override
    public Class<Map> returnedClass() {
        return Map.class;
    }

    @Override
    public int[] sqlTypes() {
        /*
         * i'm not sure what value should be used here, but it works, AFAIK only
         * length of this array matters, as it is a column span (1 in our case)
         */
        return new int[] { Types.INTEGER };
    }
}