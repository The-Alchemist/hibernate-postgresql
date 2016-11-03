package com.github.thealchemist.pg_hibernate;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.InetAddress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.InetAddressEntity;
import com.google.common.net.InetAddresses;

/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class InetAddressTypeTest extends HibernateTest {

    @Override
    @Test
	public void testSet() throws Exception {
		InetAddress address = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
		InetAddressEntity entity = new InetAddressEntity();
		entity.setAddress(address);

		this.em.persist(entity);
		this.em.flush();

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		InetAddressEntity fromDb = this.em.find(InetAddressEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getAddress());

		assertEquals(address, fromDb.getAddress());

	}

    @Test
    @Sql
    public void testGet() throws Exception {
        InetAddressEntity fromDb = em.find(InetAddressEntity.class, 37);
        InetAddress address = fromDb.getAddress();
        assertThat(address.getHostAddress(), is(equalTo("192.168.1.137")));
    }

    @Test
    @Sql
    public void testGetIpv6() throws Exception {
        InetAddressEntity fromDb = em.find(InetAddressEntity.class, 37);
        InetAddress address = fromDb.getAddress();
        assertThat(address, is(equalTo(InetAddresses.forString("2001:db8::1"))));
    }


    @Override
    @Test
	public void testNull() throws Exception {
		InetAddressEntity entity = new InetAddressEntity();

		this.em.persist(entity);
		InetAddressEntity fromDb = this.em.find(InetAddressEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getAddress());

	}
}
