package com.github.thealchemist.pg_hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>inet</b> type.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class Inet4AddressType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.OTHER};
	}

	@Override
    public Class returnedClass() {
		return Inet4Address.class;
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
	 public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {

		if (strings.length != 1)
			throw new IllegalArgumentException("strings.length != 1, strings = " + strings);

		String value = resultSet.getString(strings[0]);

		if (value == null) {
			return null;
		} else {
			try {
				StringTokenizer tokenizer = new StringTokenizer(value, ".");
				byte[] parts = new byte[4];
				for (int i = 0; tokenizer.hasMoreTokens(); i++) {
					parts[i] = Byte.parseByte(tokenizer.nextToken());
				}
				return Inet4Address.getByAddress(parts);
			}
			catch (Exception e) {
				throw new HibernateException("error parsing inet: " + value, e);
			}
		}
	}

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

		if (o == null) {
			preparedStatement.setNull(i, java.sql.Types.OTHER);
		} else {
			PGobject object = new PGobject();
			object.setValue(((Inet4Address) o).getHostAddress());
			preparedStatement.setObject(i, object);
		}
	}

	@Override
    public Object deepCopy( Object o ) throws HibernateException {
		if (o == null) return null;
		try {
			return Inet4Address.getByAddress(((Inet4Address) o).getAddress());
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
