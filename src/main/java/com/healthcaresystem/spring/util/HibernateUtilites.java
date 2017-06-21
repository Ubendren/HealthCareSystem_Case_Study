package com.healthcaresystem.spring.util;



import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilites {
	
	private static SessionFactory factory;
	private static ServiceRegistry reg;
	static{
	try
	{
		Configuration config=new Configuration().configure();
		reg=(ServiceRegistry) new ServiceRegistryBuilder().applySettings(config.getProperties());
		factory=config.buildSessionFactory(reg);
		System.out.println("session created");
	}
	catch(HibernateException e)
	{
		System.out.println("session created with error");
	}
}
	
	public static SessionFactory getSessionFactory()
	{
		return factory;
	}
}
