package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Section;

public class UtilSection extends UtilDB{

	//get all Sections
	public static List<Section> listSection() {
		List<Section> resultList = new ArrayList<Section>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> sec = session.createQuery("FROM Section").list();
			for (Iterator<?> iterator = sec.iterator(); iterator.hasNext();) {
				Section s = (Section) iterator.next();
				resultList.add(s);
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

	//get Section with specified ID
	public static Section getSection(Integer Id) {
		Section section = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			section = (Section) session.createCriteria(Section.class).add(Restrictions.eq("Id", Id)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return section;
	}

	//get Section with specified courseID
	public static List<Section> getSectionByCourse(Integer courseId) {
		List<Section> resultList = new ArrayList<Section>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			List<?> sec = session.createCriteria(Section.class).add(Restrictions.eq("courseId", courseId)).list();
			for (Iterator<?> iterator = sec.iterator(); iterator.hasNext();) {
				Section s = (Section) iterator.next();
				resultList.add(s);
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


	//add Section
	public static boolean addSection(Integer Id, String type, String method,String term) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Section());
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
