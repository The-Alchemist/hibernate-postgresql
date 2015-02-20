package com.github.thealchemist.pg_hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's native <code>intarray</code> type.
 *
 * @see http://stackoverflow.com/a/26231403/423943
 */
public class IntArrayType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.ARRAY};
	}

	@Override
    public Class<int[]> returnedClass() {
		return int[].class;
	}

	@Override
    public boolean equals( Object o, Object o1 ) throws HibernateException {
		if (o == null && o1 == null)
			return true;
		else if (o == null || o1 == null)
			return false;
		return Arrays.equals((int[]) o, (int[]) o1);
	}

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {

		if (strings.length != 1)
			throw new IllegalArgumentException("strings.length != 1, strings = " + strings);

		Array value = resultSet.getArray(strings[0]);

		if (value == null) {
			return null;
		} else {
			return value.getArray();
		}
	}

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

		int[] myArray = (int[]) o;

		if (o == null) {
			preparedStatement.setNull(i, java.sql.Types.ARRAY);
		} else {
			StringBuilder buffer = new StringBuilder();
			for (int j = 0; j < myArray.length; j++) {
				buffer.append(myArray[j]);
				if (j < myArray.length - 1)
					buffer.append(",");
			}

			PGobject object = new PGobject();
			object.setValue("{" + buffer + "}");
			preparedStatement.setObject(i, object);
		}
	}

	@Override
    public Object deepCopy( Object o ) throws HibernateException {
		if (o == null) return null;
		return ((int[]) o).clone();
	}

	@Override
    public boolean isMutable() {
		return true;
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
