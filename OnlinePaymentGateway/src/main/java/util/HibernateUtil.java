package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	
	 public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	 }

	 public static void shutdown() {
	        getSessionFactory().close();
	 }

	 public static Session getSession() {
	        return sessionFactory.openSession();
	 }

}
