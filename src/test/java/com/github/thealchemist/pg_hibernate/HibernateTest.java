package com.github.thealchemist.pg_hibernate;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.thealchemist.pg_hibernate.spring.PersistenceJPAConfig;


/**
 * @author The Alchemist
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {PersistenceJPAConfig.class})
@Transactional()
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    SqlScriptsTestExecutionListener.class
    })
@TransactionConfiguration(defaultRollback=true)
@Ignore
@Sql("classpath:/schema.sql")
public abstract class HibernateTest  {
    /*
     * created by Spring
     */
    @Inject
    protected DataSource ds;
    @Inject
    protected Environment env;
    @Inject
    protected JdbcTemplate jdbcTemplate;
    @Inject
    protected SimpleJdbcInsert simpleJdbcInsert;
    @Resource
    protected ResourceLoader resourceLoader;

    @PersistenceContext
    protected EntityManager em;

    public abstract void testSet() throws Exception;
    public abstract void testNull() throws Exception;
    public abstract void testGet() throws Exception;

}
