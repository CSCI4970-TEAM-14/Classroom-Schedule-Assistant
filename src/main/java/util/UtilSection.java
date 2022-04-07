package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

//import entities.Classroom;
import entities.Section;

public class UtilSection extends Hibernate {

	//get all Sections
	public static List<Section> listSection() {
		List<Section> resultList = new ArrayList<Section>();

		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> sec = session.createQuery("FROM Section").list();
			for (Iterator<?> iterator = sec.iterator(); iterator.hasNext();) {
				Section s = (Section) iterator.next();
				resultList.add(s);
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

	//get Section with specified ID
	public static Section getSection(String Id) {
		Section section = null;

		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			section = (Section) session.get(Section.class, Id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return section;
	}

	//get Section with specified courseID
	public static Section getSectionByCourse(String courseId) {
		Section sec = null;

		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			sec = (Section) session.get(Section.class, courseId);
			transaction.commit();
			
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sec;
	}	


	//add Section
	public static boolean addSection(String Id, String courseId, String type, String method, String enroll, String room, String instructor, String meeting,String term) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Section(Id, courseId, type, method, enroll, room, instructor, meeting, term));
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
	
	public static boolean removeSection(String Id) {

		Section sec = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			sec = (Section) session.get(Section.class, Id);
			session.delete(sec);
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
