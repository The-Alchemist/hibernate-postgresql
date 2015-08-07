package com.github.thealchemist.pg_hibernate.spi;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.hibernate.usertype.UserType;

import com.github.thealchemist.pg_hibernate.BoxType;
import com.github.thealchemist.pg_hibernate.CircleType;
import com.github.thealchemist.pg_hibernate.HstoreType;
import com.github.thealchemist.pg_hibernate.InetAddressType;
import com.github.thealchemist.pg_hibernate.IntegerArrayType;
import com.github.thealchemist.pg_hibernate.LineSegmentType;
import com.github.thealchemist.pg_hibernate.PointType;
import com.github.thealchemist.pg_hibernate.PolygonType;
import com.github.thealchemist.pg_hibernate.StringArrayType;

public class TheAlchemistHibernateIntegrator implements Integrator {

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        autoRegisterUsertypes(configuration);

    }

    private void autoRegisterUsertypes(Configuration configuration) {
        registerClass(configuration, new BoxType());
        registerClass(configuration, new CircleType());
        registerClass(configuration, new HstoreType());
        registerClass(configuration, new InetAddressType());
        registerClass(configuration, new IntegerArrayType());
        registerClass(configuration, new LineSegmentType());
        registerClass(configuration, new PointType());
        registerClass(configuration, new PolygonType());
        registerClass(configuration, new StringArrayType());
        /*
         * I'm not gonna auto-register the xml type because it overwrites String and I'm not sure how that works yet
         */
        // registerClass(configuration, new XMLType());
    }

    private void registerClass(Configuration configuration, UserType type) {
        configuration.registerTypeOverride(type, new String[]{type.returnedClass().getName()});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    	// no-op
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }

    protected void doIntegrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }
}
