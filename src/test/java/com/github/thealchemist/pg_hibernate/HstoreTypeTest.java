package com.github.thealchemist.pg_hibernate;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.thealchemist.pg_hibernate.spring.HstoreTypeEntity;
import com.google.common.collect.ImmutableMap;


/**
 * @author Jesse Costello-Good, The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HstoreTypeTest extends HibernateTest {

	@Override
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
    @Sql
    public void testSetWithNewMap() throws Exception {
	    HstoreTypeEntity fromDb = em.find(HstoreTypeEntity.class, 37);
        Map<String, String> map = fromDb.getMap();
        map.remove("name");
        map.put("power", "ball");
        em.flush();

        String newMapValue = this.jdbcTemplate.query("select map from HstoreTypeEntity where id = 37", new ResultSetExtractor<String>(){

            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getString(1);
            }});
        assertNotNull(newMapValue);
        /*
         * postgres puts a space around the '=>' operator, which I wanna ignore
         */
        assertThat(newMapValue.replace(" => ", "=>"), is(equalTo("\"power\"=>\"ball\"")));
	}

	@Override
    @Test
	@Sql
	public void testGet() {
        HstoreTypeEntity fromDb = em.find(HstoreTypeEntity.class, 37);
        Map<String, String> map = fromDb.getMap();
        assertThat(map.entrySet(), hasSize(2));
        assertThat(map, hasEntry("name", "kp"));
        assertThat(map, hasEntry("fruits", "apple,pear, lemon"));
	}

	@Override
    @Test
	public void testNull() throws Exception {
		HstoreTypeEntity entity = new HstoreTypeEntity();

		em.persist(entity);

		HstoreTypeEntity fromDb = em.find(HstoreTypeEntity.class, entity.getId());

		assertNotNull(fromDb);
		assertNull(fromDb.getMap());

	}
}
