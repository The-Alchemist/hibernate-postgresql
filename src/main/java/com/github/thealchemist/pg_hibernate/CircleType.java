package com.github.thealchemist.pg_hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGcircle;

import com.github.thealchemist.pg_hibernate.types.Circle;
import com.github.thealchemist.pg_hibernate.types.Point;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>circle</b> type. <br>
 * <br>
 * Note: this implementation unfortunately uses string parsing of the PostgreSQL data because of a problem (oversite?)
 * of the PostgreSQL JDBC driver.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class CircleType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] { java.sql.Types.OTHER };
    }

    @Override
    public Class returnedClass() {
        return Circle.class;
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
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o)
            throws HibernateException, SQLException {
        if (strings.length != 1)
            throw new IllegalArgumentException("strings.length != 1, strings = " + strings);

        PGcircle value = (PGcircle) resultSet.getObject(strings[0]);

        if (value == null) {
            return null;
        } else {
            /*
             * Because of an oversite in the Postgres JDBC library, we have to parse a circle from its string
             * representation: <(5.23,9.71234),4.10293>
             */
            String asString = value.getValue();
            double cx = 0, cy = 0, r = 0;
            try {
                cx = Double.parseDouble(asString.substring(asString.indexOf("(") + 1, asString.indexOf(",")));
                cy = Double.parseDouble(asString.substring(asString.indexOf(",") + 1, asString.indexOf(")")));
                r = Double.parseDouble(asString.substring(asString.lastIndexOf(",") + 1, asString.indexOf(">")));
            } catch (RuntimeException e) {
                throw new HibernateException("error parsing circle: " + asString, e);
            }
            return new Circle(new Point(cx, cy), r);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

        Circle c = (Circle) o;

        if (o == null) {
            preparedStatement.setNull(i, java.sql.Types.OTHER);
        } else {
            preparedStatement.setObject(i, new PGcircle(c.getCenter().getX(), c.getCenter().getY(), c.getRadius()));
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        if (o == null)
            return null;
        try {
            return ((Circle) o).clone();
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
