package com.electricityBillingSystem.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.beans.UserRequest;

/**
 * @author Sangita Halder
 * @since Feb 2022
 */

public class HibernateUtil {
	static SessionFactory sessionFactory = null;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Bill.class);
		configuration.addAnnotatedClass(UserRequest.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
