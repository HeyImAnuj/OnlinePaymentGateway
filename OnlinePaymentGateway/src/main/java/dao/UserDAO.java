package dao;

import org.hibernate.Session;

import model.user;
import util.HibernateUtil;

public class UserDAO extends GenericDAO<user, Integer> {
	public UserDAO() {
		super(user.class);
	}

	// Method to find user by email
	public user findByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM user WHERE email = :email", user.class).setParameter("email", email)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
