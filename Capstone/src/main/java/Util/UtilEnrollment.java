package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Enrollment;

public class UtilEnrollment extends UtilDB{

	//get all Enrollments
	public static List<Enrollment> listEnroll() {
		List<Enrollment> resultList = new ArrayList<Enrollment>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> enroll = session.createQuery("FROM Enrollment").list();
			for (Iterator<?> iterator = enroll.iterator(); iterator.hasNext();) {
				Enrollment en = (Enrollment) iterator.next();
				resultList.add(en);
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
	public static Enrollment getEnrollment(Integer seat) {
		Enrollment enrollment = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			enrollment = (Enrollment) session.createCriteria(Enrollment.class).add(Restrictions.eq("seat", seat)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return enrollment;
	}

	//update enrollment
	public static boolean UpdateEnrollment(Integer seats, String year, String term, Integer section) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Enrollment());
			tx.commit();
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (java.lang.NumberFormatException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
