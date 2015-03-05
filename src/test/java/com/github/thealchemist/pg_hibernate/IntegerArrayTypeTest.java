package com.github.thealchemist.pg_hibernate;


import java.util.Arrays;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.IntegerArrayEntity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Sql
public class IntegerArrayTypeTest extends HibernateTest {

    @Override
    @Test
	public void testSet() throws Exception {
		Integer[] values = new Integer[]{10, 22, 37};
		IntegerArrayEntity entity = new IntegerArrayEntity();
		entity.setIntegers(values);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		IntegerArrayEntity fromDb = this.em.find(IntegerArrayEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getIntegers());

		assertTrue(Arrays.equals(values, fromDb.getIntegers()));
	}
    
    @Test
    @Sql
    public void testSetViaSql() throws Exception {
        IntegerArrayEntity fromDb = this.em.find(IntegerArrayEntity.class, 39);

        assertNotNull(fromDb);
        assertNotNull(fromDb.getIntegers());

        assertThat(fromDb.getIntegers(), is(equalTo(new Integer[]{76,  -5, 1001})));
    }

    @Test
    @Sql
    public void testGet() throws Exception {

        IntegerArrayEntity fromDb = this.em.find(IntegerArrayEntity.class, 37);

        assertNotNull(fromDb);
        assertThat(fromDb.getIntegers(), is(equalTo(new Integer[]{10, 22, 37})));

    }

    @Override
    @Test
	public void testNull() throws Exception {
        IntegerArrayEntity entity = new IntegerArrayEntity();

		this.em.persist(entity);

		IntegerArrayEntity fromDb = this.em.find(IntegerArrayEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getIntegers());

	}
}
