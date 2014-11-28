package com.github.thealchemist.pg_hibernate;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.HstoreTypeEntity;
import com.google.common.collect.ImmutableMap;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HstoreTypeTest extends HibernateTest {


	@Test
	public void testSet() throws Exception {
		Map<String, String> m = ImmutableMap.of("a", "1", "b", "2", "c", "3");
		HstoreTypeEntity entity = new HstoreTypeEntity();
		entity.setMap(m);

		em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		HstoreTypeEntity fromDb = em.find(HstoreTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getMap());

		assertThat(entity.getMap(), is(equalTo(fromDb.getMap())));

	}

	@Test
	public void testNull() throws Exception {
		HstoreTypeEntity entity = new HstoreTypeEntity();

		em.persist(entity);

		HstoreTypeEntity fromDb = em.find(HstoreTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getMap());

	}
}
