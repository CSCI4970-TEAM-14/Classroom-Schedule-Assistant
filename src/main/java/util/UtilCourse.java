package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

//import entities.Classroom;
import entities.Course;

public class UtilCourse extends Hibernate {

	//get all Courses
	public static List<Course> listCourse() {
		
		List<Course> resultList = new ArrayList<Course>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> courses = session.createQuery("FROM Course").list();
			for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
				Course c = (Course) iterator.next();
				resultList.add(c);
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

	//get Course with specified ID
	public static Course getCourse(String Id) {
		
		Course course = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			course = (Course) session.get(Course.class,Id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return course;
	}

	//add Course
	public static boolean addCourse(String Id, String title, String department, String term, String building) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(new Course(Id, title, department, term, building));
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
	
	public static boolean removeCourse(String Id) {

		Course course = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			course = (Course) session.get(Course.class, Id);
			session.delete(course);
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