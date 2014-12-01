package com.github.thealchemist.pg_hibernate;


import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.StringArrayEntity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class StringArrayTypeTest extends HibernateTest {

    @Override
    @Test
	public void testSet() throws Exception {
		String[] values = new String[]{"a", "z", "mm"};
		StringArrayEntity entity = new StringArrayEntity();
		entity.setStrings(values);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		StringArrayEntity fromDb = this.em.find(StringArrayEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getStrings());

		assertTrue(Arrays.equals(values, fromDb.getStrings()));

	}

    @Test
    @Sql
    public void testGet() throws Exception {

        StringArrayEntity fromDb = this.em.find(StringArrayEntity.class, 37);

        assertNotNull(fromDb);
        assertThat(fromDb.getStrings(), is(equalTo(new String[]{"a", "b", "c"})));

    }

    @Override
    @Test
	public void testNull() throws Exception {
		StringArrayEntity entity = new StringArrayEntity();

		this.em.persist(entity);

		StringArrayEntity fromDb = this.em.find(StringArrayEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getStrings());

	}
}
