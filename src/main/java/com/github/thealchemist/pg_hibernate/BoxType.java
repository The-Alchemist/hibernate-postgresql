package com.github.thealchemist.pg_hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGbox;

import com.github.thealchemist.pg_hibernate.types.Box;
import com.github.thealchemist.pg_hibernate.types.Point;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>box</b> type.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class BoxType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] { java.sql.Types.OTHER };
    }

    @Override
    public Class<Box> returnedClass() {
        return Box.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == null && o1 == null)
            return true;
        else if (o == null || o1 == null)
            return false;
        return o.equals(o1);
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sessionImplementor, Object o)
            throws HibernateException, SQLException {
        if (strings.length != 1)
            throw new IllegalArgumentException("strings.length != 1, strings = " + strings);

        PGbox value = (PGbox) resultSet.getObject(strings[0]);

        if (value == null) {
            return null;
        } else {
            Point p1 = new Point(value.point[0].x, value.point[0].y);
            Point p2 = new Point(value.point[1].x, value.point[1].y);
            return new Box(p1, p2);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {

        Box rect = (Box) o;

        if (o == null) {
            preparedStatement.setNull(i, java.sql.Types.OTHER);
        } else {
            preparedStatement.setObject(i, new PGbox(rect.getP1().getX(), rect.getP1().getY(), rect.getP2().getX(),
                    rect.getP2().getY()));
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        if (o == null)
            return null;
        try {
            return ((Box) o).clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e.toString());
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
