package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Classroom;
import entities.Section;

public class UtilSection extends UtilDB{

	//get all Sections
	public static List<Section> listSection() {
		List<Section> resultList = new ArrayList<Section>();

		Session session = Hibernate.getSessionFactory().openSession();
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
	public static Section getSection(Integer Id) {
		Section section = null;

		Session session = Hibernate.getSessionFactory().openSession();
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
	public static Section getSectionByCourse(Integer courseId) {
		Section sec = null;

		Session session = Hibernate.getSessionFactory().openSession();
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
	public static boolean addSection(int Id, String type, String method, String instructor, String term, int enroll, String meeting, int room, int courseId) {
		Session session = Hibernate.getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Section(Id, type, method, instructor, term, enroll, meeting, room, courseId));
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
	
	public static boolean removeSection(Integer Id) {

		Section sec = null;
		Session session = Hibernate.getSessionFactory().openSession();
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
