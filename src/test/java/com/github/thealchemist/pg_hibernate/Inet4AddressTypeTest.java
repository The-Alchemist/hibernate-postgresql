package com.github.thealchemist.pg_hibernate;


import static org.junit.Assert.*;

import java.net.Inet4Address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.Inet4AddressEntity;

/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Inet4AddressTypeTest extends HibernateTest {

    @Test
	public void testSet() throws Exception {
		Inet4Address address = (Inet4Address) Inet4Address.getByAddress(new byte[]{127, 0, 0, 1});
		Inet4AddressEntity entity = new Inet4AddressEntity();
		entity.setAddress(address);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		Inet4AddressEntity fromDb = this.em.find(Inet4AddressEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getAddress());

		assertEquals(address, fromDb.getAddress());

	}

    @Test
	public void testNull() throws Exception {
		Inet4AddressEntity entity = new Inet4AddressEntity();

		this.em.persist(entity);
		Inet4AddressEntity fromDb = this.em.find(Inet4AddressEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getAddress());

	}
}
