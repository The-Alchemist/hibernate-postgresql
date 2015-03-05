package com.github.thealchemist.pg_hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

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
public class IntegerArrayType implements UserType {

	@Override
    public int[] sqlTypes() {
		return new int[]{java.sql.Types.ARRAY};
	}

	@Override
    public Class<Integer[]> returnedClass() {
		return Integer[].class;
	}

	@Override
    public boolean equals( Object o, Object o1 ) throws HibernateException {
		if (o == null && o1 == null)
			return true;
		else if (o == null || o1 == null)
			return false;
		return Arrays.equals((Integer[]) o, (Integer[]) o1);
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

    /**
     * Supports both int[] and Integer[], yay!
     */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        // use JDBC array type, which is fairly new, so tread carefully
        if (o == null) {
			preparedStatement.setNull(i, java.sql.Types.ARRAY);
		} else if(o instanceof Integer[]){
		    Integer[] myArray = (Integer[]) o;
            Array inArray = preparedStatement.getConnection().createArrayOf("integer", myArray);
            preparedStatement.setArray(i, inArray);
		}
		else if(o instanceof int[]) {
			int[] myArray = (int[]) o;
            Array inArray = preparedStatement.getConnection().createArrayOf("integer", wrap(myArray));
			preparedStatement.setArray(i, inArray);
		} else
		{
		    throw new IllegalArgumentException("Invalid typeof input: " + o.getClass().getName());
		}
	}

	private static Object[] wrap(int[] intArray) {
	    Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = Integer.valueOf(intArray[i]);
        }
        return result;
    }

    @Override
    public Object deepCopy( Object o ) throws HibernateException {
        if (o == null) return null;
        else if(o instanceof Integer[])
        {
            Integer[] array = (Integer[]) o;
            return array.clone();
        }
        else if(o instanceof int[])
        {
            int[] array = (int[]) o;
            return array.clone();
        } else {
            throw new IllegalArgumentException("Invalid type: " + o.getClass().getName());
        }
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
