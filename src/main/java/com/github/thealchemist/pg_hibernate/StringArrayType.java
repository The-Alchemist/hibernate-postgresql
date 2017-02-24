package com.github.thealchemist.pg_hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's native <b>varchar[]</b> type.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class StringArrayType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.OTHER};
	}

	@Override
    public Class returnedClass() {
		return String[].class;
	}

	@Override
    public boolean equals( Object o, Object o1 ) throws HibernateException {
		if (o == null && o1 == null)
			return true;
		else if (o == null || o1 == null)
			return false;
		return Arrays.equals((String[]) o, (String[]) o1);
	}

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sessionImplementor, Object owner) throws HibernateException, SQLException {

		if (names.length != 1)
			throw new IllegalArgumentException("names.length != 1, names = " + names);

		String value = resultSet.getString(names[0]);

		if (value == null) {
			return null;
		} else if (value.length() < 2) {
			return new String[]{};
		} else {
			StringTokenizer tokenizer = new StringTokenizer(value.substring(1, value.length() - 1), ",");
			String[] values = new String[tokenizer.countTokens()];
			int i = 0;
			while (tokenizer.hasMoreTokens()) {
				values[i++] = tokenizer.nextToken();
			}
			return values;
		}
	}

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {

		String[] strings = (String[]) value;

		if (value == null) {
			preparedStatement.setNull(i, java.sql.Types.OTHER);
		} else {
			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < strings.length; j++) {
				buffer.append("\"" + strings[j] + "\"");
				if (j < strings.length - 1)
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
		return ((String[]) o).clone();
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
