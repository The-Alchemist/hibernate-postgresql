package com.github.thealchemist.pg_hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.github.thealchemist.pg_hibernate.spring.RectangleTypeEntity;
import com.github.thealchemist.pg_hibernate.types.Point;
import com.github.thealchemist.pg_hibernate.types.Rectangle;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RectangleTypeTest extends HibernateTest {

	private static final double X1 = 54;
	private static final double Y1 = 5.6667;
	private static final double X2 = 1.342366234;
	private static final double Y2 = 0.0001;

	@Test
	public void testSet() throws Exception {
		Rectangle rect = new Rectangle(new Point(X1, Y1), new Point(X2, Y2));
		RectangleTypeEntity entity = new RectangleTypeEntity();
		entity.setRect(rect);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		RectangleTypeEntity fromDb = this.em.find(RectangleTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getRect());

		assertTrue(rect.equals(fromDb.getRect()));

	}

	@Test
	public void testNull() throws Exception {
		RectangleTypeEntity entity = new RectangleTypeEntity();

		this.em.persist(entity);

		RectangleTypeEntity fromDb = this.em.find(RectangleTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getRect());

	}
}
