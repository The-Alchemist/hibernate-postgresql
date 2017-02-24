package com.github.thealchemist.pg_hibernate;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import com.google.common.net.InetAddresses;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>inet</b> type.
 *
 * @author Jesse Costello-Good, The Alchemist
 */
public class InetAddressType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.OTHER};
	}

	@Override
    public Class<InetAddress> returnedClass() {
		return InetAddress.class;
	}

	@Override
    public boolean equals( Object o, Object o1 ) throws HibernateException {
		if (o == null && o1 == null)
			return true;
		else if (o == null || o1 == null)
			return false;
		return o.equals(o1);
	}

	 @Override
	 public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sessionImplementor, Object owner) throws HibernateException, SQLException {

		if (names.length != 1)
			throw new IllegalArgumentException("names.length != 1, names = " + names);

		String value = resultSet.getString(names[0]);

		if (value == null) {
			return null;
		} else {
			return InetAddresses.forString(value);
		}
	}

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {

		if (value == null) {
			preparedStatement.setNull(i, java.sql.Types.OTHER);
		} else {
			PGobject object = new PGobject();
			object.setValue(((InetAddress) value).getHostAddress());
			object.setType("inet");
			preparedStatement.setObject(i, object);
		}
	}

	@Override
    public Object deepCopy( Object o ) throws HibernateException {
		if (o == null) return null;
		try {
			return InetAddress.getByAddress(((InetAddress) o).getAddress());
		}
		catch (UnknownHostException e) {
			throw new AssertionError("this can't happen!");
		}
	}

	@Override
    public boolean isMutable() {
		return false;
	}

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
