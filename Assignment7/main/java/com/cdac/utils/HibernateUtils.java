package com.cdac.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
public @interface getSessionFactory {

	}

private static SessionFactory sessionFactory;
static {
System.out.println("in init block");	
sessionFactory = new Configuration()
.configure()
.buildSessionFactory();
}

public static SessionFactory getSessionFactory() {
	return sessionFactory;
}


}

