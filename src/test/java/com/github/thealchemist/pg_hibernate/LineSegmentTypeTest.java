package com.github.thealchemist.pg_hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.github.thealchemist.pg_hibernate.spring.LineSegmentTypeEntity;
import com.github.thealchemist.pg_hibernate.types.LineSegment;
import com.github.thealchemist.pg_hibernate.types.Point;

/**
 * @author Jesse Costello-Good, The Alchemist
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class LineSegmentTypeTest extends HibernateTest {

	private static final double X1 = 54;
	private static final double Y1 = 5.6667;
	private static final double X2 = 1.342366234;
	private static final double Y2 = 0.0001;

	@Override
    @Test
	public void testSet() throws Exception {
		LineSegment line = new LineSegment(new Point(X1, Y1), new Point(X2, Y2));
		LineSegmentTypeEntity entity = new LineSegmentTypeEntity();
		entity.setLine(line);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		LineSegmentTypeEntity fromDb = this.em.find(LineSegmentTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getLine());

		assertTrue(line.equals(fromDb.getLine()));

	}
	@Test
	@Sql
    public void testGet() throws Exception {
	    LineSegmentTypeEntity fromDb = this.em.find(LineSegmentTypeEntity.class, 37);

        assertNotNull(fromDb);
        LineSegment line = fromDb.getLine();
        assertNotNull(line);
        assertThat(line.getP1().getX(), is(equalTo(1.0)));
        assertThat(line.getP1().getY(), is(equalTo(1.0)));
        assertThat(line.getP2().getX(), is(equalTo(5.0)));
        assertThat(line.getP2().getY(), is(equalTo(5.0)));
	}

	@Test
	@Sql
    public void testGetAlternateSyntax() throws Exception {
	    LineSegmentTypeEntity fromDb = this.em.find(LineSegmentTypeEntity.class, 37);

        assertNotNull(fromDb);
        LineSegment line = fromDb.getLine();
        assertNotNull(line);
        assertThat(line.getP1().getX(), is(equalTo(6.0)));
        assertThat(line.getP1().getY(), is(equalTo(7.0)));
        assertThat(line.getP2().getX(), is(equalTo(8.0)));
        assertThat(line.getP2().getY(), is(equalTo(9.0)));
    }

    @Override
    @Test
	public void testNull() throws Exception {
		LineSegmentTypeEntity entity = new LineSegmentTypeEntity();

		this.em.persist(entity);

		LineSegmentTypeEntity fromDb = this.em.find(LineSegmentTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getLine());

	}
}
