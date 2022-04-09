package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Restrictions;

import entities.Instructor;
import entities.Schedule;

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
	
	public static List<Instructor> listInstructors(String keyword) {
	      List<Instructor> resultList = new ArrayList<Instructor>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((Instructor)session.get(Instructor.class, 1)); 
	         List<?> accs = session.createQuery("FROM Instructor").list();
	         for (Iterator<?> iterator = accs.iterator(); iterator.hasNext();) {
	            Instructor in = (Instructor) iterator.next();
	            if (in.getFirstName().startsWith(keyword)) {
	               resultList.add(in);
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

	//get Instructor with specified ID
	public static Instructor getInstructor(String id) {
		
		Instructor instructor = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			instructor = (Instructor) session.get(Instructor.class, id);
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
	public static boolean addInstructor(String id, String firstName, String lastName, String dept) {
		
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Instructor(id, firstName, lastName, dept));
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
	
	//add
	public void saveInstructor(Instructor in) { //static
		
		Transaction transaction = null;
		//boolean result = true;
		Session session = getSessionFactory().openSession();
		try  {
			// start a transaction
			transaction = session.beginTransaction();
			session.save(in);
			System.out.println("Object saved successfully.....!!");
			// commit transaction
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
				e.printStackTrace();
			}finally {
				session.close();
			}
		//return acc ;
	}
	
	public static List <Instructor> getInstructorByName() {
		List<Instructor> resultList = new ArrayList<Instructor>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		String First = null;

		try {
			transaction = session.beginTransaction();
			List<?> in = session.createCriteria(Schedule.class).add(Restrictions.eq("First", First)).list();
			for (Iterator<?> iterator = in.iterator(); iterator.hasNext();) {
				Instructor i = (Instructor) iterator.next();
				resultList.add(i);
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
	
	public static boolean removeInstructor(String id) {

		Instructor in = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			in = (Instructor) session.get(Instructor.class, id);
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