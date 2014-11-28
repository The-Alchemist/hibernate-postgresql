package com.github.thealchemist.pg_hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.PolygonTypeEntity;
import com.github.thealchemist.pg_hibernate.types.Point;
import com.github.thealchemist.pg_hibernate.types.Polygon;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PolygonTypeTest extends HibernateTest {

	Point[] points = new Point[]{
		new Point(1, 1),
		new Point(5, 8),
		new Point(4.25232, 9.1232837),
		new Point(9.123723, 4.1),
		new Point(0.000001, 65)
	};

	@Test
	public void testSet() throws Exception {
		Polygon polygon = new Polygon(points);
		PolygonTypeEntity entity = new PolygonTypeEntity();
		entity.setPolygon(polygon);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		PolygonTypeEntity fromDb = this.em.find(PolygonTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getPolygon());

		assertTrue(polygon.equals(fromDb.getPolygon()));

	}

	@Test
	public void testNull() throws Exception {
		PolygonTypeEntity entity = new PolygonTypeEntity();

		this.em.persist(entity);

		PolygonTypeEntity fromDb = this.em.find(PolygonTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getPolygon());

	}

}
