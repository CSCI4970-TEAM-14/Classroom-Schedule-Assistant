package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Classroom;

public class UtilClass extends UtilDB{

	//get all Classrooms
	public static List<Classroom> listClass() {
		List<Classroom> resultList = new ArrayList<Classroom>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> classes = session.createQuery("FROM Classroom").list();
			for (Iterator<?> iterator = classes.iterator(); iterator.hasNext();) {
				Classroom c = (Classroom) iterator.next();
				resultList.add(c);
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

	//get classroom with specified number
	public static Classroom getClass(Integer Id) {
		Classroom classroom = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			classroom = (Classroom) session.createCriteria(Classroom.class).add(Restrictions.eq("Id", Id)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return classroom;
	}

	//add Classroom
	public static boolean addClassroom(Integer Id, String type, String building, Integer seats, Integer computers) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Classroom());
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