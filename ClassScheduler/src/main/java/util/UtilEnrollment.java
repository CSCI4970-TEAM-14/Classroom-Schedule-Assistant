package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

import entities.Enrollment;

public class UtilEnrollment extends Hibernate{

	//get all Enrollments
	public static List<Enrollment> listEnroll() {
		
		List<Enrollment> resultList = new ArrayList<Enrollment>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> enroll = session.createQuery("FROM Enrollment").list();
			for (Iterator<?> iterator = enroll.iterator(); iterator.hasNext();) {
				Enrollment en = (Enrollment) iterator.next();
				resultList.add(en);
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	
	public static List<Enrollment> listEnroll(String keyword) {
		List<Enrollment> resultList = new ArrayList<Enrollment>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Enrollment) session.get(Enrollment.class, 1));
			List<?> accs = session.createQuery("FROM Enrollment").list();
			for (Iterator<?> iterator = accs.iterator(); iterator.hasNext();) {
				Enrollment en = (Enrollment) iterator.next();
				if (en.getSection().startsWith(keyword)) {
					resultList.add(en);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	//get Enrollment with specified #seats
	public static Enrollment getEnrollment(String seat) {
		
		Enrollment enrollment = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			enrollment = (Enrollment) session.get(Enrollment.class, seat);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return enrollment;
	}

	//update enrollment
	public static boolean UpdateEnrollment(String section, String seats) {
		
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Enrollment en = (Enrollment) session.get(Enrollment.class, section );
			en.setSeats(seats);
			session.update(en);
			transaction.commit();
		} catch (HibernateException e) {
			result = false;
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} catch (java.lang.NumberFormatException e) {
			result = false;
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
}