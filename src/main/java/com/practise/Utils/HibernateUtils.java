package com.practise.Utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.practise.Entity.Address;
import com.practise.Entity.Student;

public class HibernateUtils {


	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				/*
				 * 1. create-drop=create new table every time
				 * 2.update will add entry everytime
				 * 3.validate will check duplicate data
				 */
				settings.put(Environment.HBM2DDL_AUTO, "validate");

				// c3p0 configuration
				settings.put(Environment.C3P0_MIN_SIZE, 5); // Minimum size of pool
				settings.put(Environment.C3P0_MAX_SIZE, 20); // Maximum size of pool
				settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);// Number of connections acquired at a time when
																	// pool is exhausted
				settings.put(Environment.C3P0_TIMEOUT, 1800); // Connection idle time
				settings.put(Environment.C3P0_MAX_STATEMENTS, 150); // PreparedStatement cache size

				settings.put(Environment.C3P0_CONFIG_PREFIX + ".initialPoolSize", 5);

//				settings.put(Environment.USE_QUERY_CACHE, true);
//				settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Student.class);
				configuration.addAnnotatedClass(Address.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}


}
