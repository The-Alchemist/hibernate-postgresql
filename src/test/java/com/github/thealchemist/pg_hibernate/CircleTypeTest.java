package com.github.thealchemist.pg_hibernate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.CircleTypeEntity;
import com.github.thealchemist.pg_hibernate.types.Circle;
import com.github.thealchemist.pg_hibernate.types.Point;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CircleTypeTest extends HibernateTest {

	private static final double X = 5.23;
	private static final double Y = 9.71234;
	private static final double R = 4.10293;

	@Override
    @Test
	public void testSet() throws Exception {
		Circle circle = new Circle(new Point(X, Y), R);
		CircleTypeEntity entity = new CircleTypeEntity();
		entity.setCircle(circle);

		em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		CircleTypeEntity fromDb = em.find(CircleTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getCircle());

		assertTrue(circle.equals(fromDb.getCircle()));

	}

    @Test
    @Sql
    public void testGet() {
        CircleTypeEntity fromDb = em.find(CircleTypeEntity.class, 37);
        assertNotNull(fromDb);
        assertThat(fromDb.getCircle().getRadius(), is(equalTo(37.0)));
        assertThat(fromDb.getCircle().getCenter(), is(equalTo(new Point(1.0, 1.0))));
   }

	@Override
    @Test
	public void testNull() throws Exception {
		CircleTypeEntity entity = new CircleTypeEntity();

		em.persist(entity);

		CircleTypeEntity fromDb = em.find(CircleTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getCircle());

	}
}
