package com.github.thealchemist.pg_hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

import com.github.thealchemist.pg_hibernate.types.Point;
import com.github.thealchemist.pg_hibernate.types.Polygon;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>polygon</b> type.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class PolygonType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.OTHER};
	}

	@Override
    public Class returnedClass() {
		return Polygon.class;
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

		PGpolygon value = (PGpolygon) resultSet.getObject(names[0]);

		if (value == null) {
			return null;
		} else {
			return new Polygon(convert(value.points));
		}
	}

	private Point[] convert( PGpoint[] points ) {
		Point[] newpoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			newpoints[i] = new Point(points[i].x, points[i].y);
		}
		return newpoints;
	}

	private PGpoint[] convert( Point[] points ) {
		PGpoint[] newpoints = new PGpoint[points.length];
		for (int i = 0; i < points.length; i++) {
			newpoints[i] = new PGpoint(points[i].getX(), points[i].getY());
		}
		return newpoints;
	}

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {
		Polygon polygon = (Polygon) value;

		if (value == null) {
			preparedStatement.setNull(i, java.sql.Types.OTHER);
		} else {
			preparedStatement.setObject(i, new PGpolygon(convert(polygon.getPoints())));
		}
	}

	@Override
    public Object deepCopy( Object o ) throws HibernateException {
		if (o == null)
		    return null;
		
		try {
			return ((Polygon) o).clone();
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
