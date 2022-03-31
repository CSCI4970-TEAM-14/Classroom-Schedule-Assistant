package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

//import entities.Classroom;
import entities.Instructor;

public class UtilInstructor extends Hibernate{

	//get all Instructors
	public static List<Instructor> listInstruct() {
		
		List<Instructor> resultList = new ArrayList<Instructor>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> instructors = session.createQuery("FROM Instructor").list();
			for (Iterator<?> iterator = instructors.iterator(); iterator.hasNext();) {
				Instructor in = (Instructor) iterator.next();
				resultList.add(in);
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

	//get Instructor with specified ID
	public static Instructor getInstructor(Integer Id) {
		
		Instructor instructor = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			instructor = (Instructor) session.get(Instructor.class, Id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}catch (java.lang.NumberFormatException e) {
			if (transaction != null)
				transaction.rollback();
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
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Instructor(Id, firstName, lastName, dept));
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
	
	public static boolean removeInstructor(Integer Id) {

		Instructor in = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			in = (Instructor) session.get(Instructor.class, Id);
			session.delete(in);
			transaction.commit();
		} catch (HibernateException e) {
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