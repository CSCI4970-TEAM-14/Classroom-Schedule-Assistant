package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Course;

public class UtilCourse extends UtilDB{

	//get all Courses
	public static List<Course> listCourse() {
		List<Course> resultList = new ArrayList<Course>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> courses = session.createQuery("FROM Course").list();
			for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
				Course c = (Course) iterator.next();
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

	//get Course with specified ID
	public static Course getCourse(Integer Id) {
		Course course = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			course = (Course) session.createCriteria(Course.class).add(Restrictions.eq("Id", Id)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return course;
	}

	//add Course
	public static boolean addCourse(Integer Id, String title, String department, String term, String building) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Course());
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