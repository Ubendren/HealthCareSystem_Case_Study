package com.healthcaresystem.spring.util;



import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilites {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static{
	try
	{
		
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		/*Configuration config=new Configuration().configure();
		reg=(ServiceRegistry) new ServiceRegistryBuilder().applySettings(config.getProperties());
		factory=config.buildSessionFactory(reg);
		System.out.println("session created");*/
	}
	catch(HibernateException e)
	{
		System.err.println("Error creating Session: " + e);
		throw new ExceptionInInitializerError(e);
	}
}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException ignored) {
				// log.error("Couldn't close SessionFactory", ignored);
			}
		}
	}
}





