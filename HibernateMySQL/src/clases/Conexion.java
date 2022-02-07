package clases;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexion {

	private static SessionFactory sessionFactory = null;
	
	static {
		try {
		 sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

		} catch (Exception e) {
			
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
