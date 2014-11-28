package com.github.thealchemist.pg_hibernate;


import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.PointTypeEntity;
import com.github.thealchemist.pg_hibernate.types.Point;

/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PointTypeTest extends HibernateTest {

	private static final double X = 1.342366234;
	private static final double Y = Math.PI;
	private static final double EPSILON = .00000001;

	@Test
	public void testSet() throws Exception {
		Point point = new Point(X, Y);
		PointTypeEntity entity = new PointTypeEntity();
		entity.setPoint(point);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		PointTypeEntity fromDb = this.em.find(PointTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getPoint());

		Point2D p1 = point.asPoint2D();
		Point2D p2 = fromDb.getPoint().asPoint2D();
		assertTrue(p1.distance(p2) < EPSILON);

	}

	@Test
	public void testNull() throws Exception {
		PointTypeEntity entity = new PointTypeEntity();

		this.em.persist(entity);

		PointTypeEntity fromDb = this.em.find(PointTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getPoint());

	}
}
