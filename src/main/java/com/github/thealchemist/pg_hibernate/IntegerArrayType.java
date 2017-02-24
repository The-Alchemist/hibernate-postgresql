package com.github.thealchemist.pg_hibernate;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's native <code>intarray</code> type.
 *
 * @see http://stackoverflow.com/a/26231403/423943
 */
public class IntegerArrayType implements UserType {

	private static final int[] JDBC_TYPES = new int[]{java.sql.Types.ARRAY};

    @Override
    public int[] sqlTypes() {
		return JDBC_TYPES;
	}

	@Override
    public Class<Integer[]> returnedClass() {
		return Integer[].class;
	}

	@Override
    public boolean equals( Object o1, Object o2 ) throws HibernateException {
		if (o1 == null && o2 == null)
			return true;
		else if (o1 == null || o2 == null)
			return false;
		if(o1 instanceof int[] && o2 instanceof int[]) {
		    return Arrays.equals(((int[]) o1), ((int[])o2));
		} else {
		    return Arrays.equals((Integer[]) o1, (Integer[]) o2);
		}
	}

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sessionImplementor, Object o) throws HibernateException, SQLException {

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
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {
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
		} else {
		    throw new IllegalArgumentException("Invalid type of input: " + o.getClass().getName());
		}
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
            throw new IllegalArgumentException("Invalid type to copy: " + o.getClass().getName());
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

    private static Object[] wrap(int[] intArray) {
        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = Integer.valueOf(intArray[i]);
        }
        return result;
    }
}
