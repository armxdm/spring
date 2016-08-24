package com.exist.utility;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class FactoryBuilder{
    private SessionFactory factory;
	private static AnnotationConfiguration cfg;
	private static StandardServiceRegistryBuilder builder;
	
    {
        try{
			cfg = new AnnotationConfiguration()
						.addAnnotatedClass(com.exist.model.Contact.class)
						.addAnnotatedClass(com.exist.model.Role.class)
						.addAnnotatedClass(com.exist.model.Person.class)
						.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
						.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
						.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
						.setProperty("hibernate.connection.username", "postgres")
						.setProperty("hibernate.connection.password", "root")
						.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider")
						.setProperty("hibernate.cache.use_second_level_cache","true")
						.setProperty("hibernate.cache.use_query_cache","true")
						.setProperty("hibernate.enable_lazy_load_no_trans","true")
						.setProperty("hibernate.show_sql","false")
						.setProperty("hibernate.cache.provider_class","org.hibernate.cache.EhCacheProvider")
						.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory");
			builder= new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());;
			factory = cfg.buildSessionFactory(builder.build());
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public SessionFactory getSessionFactory(){
		return factory;
	}
}

