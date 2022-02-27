package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Instructor;

public class UtilInstructor extends UtilDB{

	//get all Instructors
	public static List<Instructor> listInstruct() {
		List<Instructor> resultList = new ArrayList<Instructor>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> instructors = session.createQuery("FROM Instructor").list();
			for (Iterator<?> iterator = instructors.iterator(); iterator.hasNext();) {
				Instructor in = (Instructor) iterator.next();
				resultList.add(in);
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

	//get Instructor with specified ID
	public static Instructor getInstructor(Integer Id) {
		Instructor instructor = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			instructor = (Instructor) session.createCriteria(Instructor.class).add(Restrictions.eq("Id", Id)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return instructor;
	}

	//add Instructor
	public static boolean addInstructor(Integer Id, String firstName, String lastName, String dept) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Instructor());
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