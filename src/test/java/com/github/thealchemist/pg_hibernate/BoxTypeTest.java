package com.github.thealchemist.pg_hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.github.thealchemist.pg_hibernate.spring.BoxTypeEntity;
import com.github.thealchemist.pg_hibernate.types.Point;
import com.github.thealchemist.pg_hibernate.types.Box;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BoxTypeTest extends HibernateTest {

	private static final double X1 = 54;
	private static final double Y1 = 5.6667;
	private static final double X2 = 1.342366234;
	private static final double Y2 = 0.0001;

	@Override
    @Test
	public void testSet() throws Exception {
		Box rect = new Box(new Point(X1, Y1), new Point(X2, Y2));
		BoxTypeEntity entity = new BoxTypeEntity();
		entity.setBox(rect);

		this.em.persist(entity);

		assertNotNull(entity.getId());
		assertTrue(entity.getId().intValue() > 0);

		BoxTypeEntity fromDb = this.em.find(BoxTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNotNull(fromDb.getBox());

		assertTrue(rect.equals(fromDb.getBox()));

	}

	@Test
	@Sql
    public void testGet() throws Exception {

        BoxTypeEntity fromDb = this.em.find(BoxTypeEntity.class, 37);

        assertNotNull(fromDb);
        Box box = fromDb.getBox();
        assertNotNull(box);
        /*
         * points are re-ordered from upper right to lower left
         *
         * http://www.postgresql.org/docs/9.2/static/datatype-geometric.html
         */
        assertThat(box.getP1(), is(equalTo(new Point(5, 5))));
        assertThat(box.getP2(), is(equalTo(new Point(1, 1))));

    }

	@Override
    @Test
	public void testNull() throws Exception {
		BoxTypeEntity entity = new BoxTypeEntity();

		this.em.persist(entity);

		BoxTypeEntity fromDb = this.em.find(BoxTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getBox());

	}
}
