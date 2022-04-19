package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Restrictions;
import entities.Classroom;


public class UtilClass extends Hibernate {

	//get all Classrooms
	public static List<Classroom> listClass() {

		List<Classroom> resultList = new ArrayList<Classroom>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> classes = session.createQuery("FROM Classroom").list();
			for (Iterator<?> iterator = classes.iterator(); iterator.hasNext();) {
				Classroom c = (Classroom) iterator.next();
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
	
	public static List<Classroom> listClass(String keyword) {
		
	      List<Classroom> resultList = new ArrayList<Classroom>();
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         System.out.println((Classroom)session.get(Classroom.class, 1)); 
	         List<?> accs = session.createQuery("FROM Classroom").list();
	         for (Iterator<?> iterator = accs.iterator(); iterator.hasNext();) {
	            Classroom ant = (Classroom) iterator.next();
	            if (ant.getId().startsWith(keyword)) {
	               resultList.add(ant);
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
	
	public static List<Classroom> getClassById() {
		List<Classroom> resultList = new ArrayList<Classroom>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			List<?> c = session.createCriteria(Classroom.class).add(Restrictions.eq("id", id)).list();
			for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
				Classroom sh = (Classroom) iterator.next();
				resultList.add(sh);
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

	//get classroom with specified number
	public Classroom getClass(String Id, String building) {

		Classroom classroom = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			classroom = (Classroom) session.get(Classroom.class, Id);
			transaction.commit();
		} catch (HibernateException e) {;
		if (transaction != null)
			transaction.rollback();
		e.printStackTrace();
		} finally {
			session.close();
		}
		return classroom;
	}

	//get classroom by Room
	public Classroom getRoom(String Id) {

		Classroom classroom = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			classroom = (Classroom) session.get(Classroom.class, Id);
			transaction.commit();
		} catch (HibernateException e) {;
		if (transaction != null)
			transaction.rollback();
		e.printStackTrace();
		} finally {
			session.close();
		}
		return classroom;
	}
	//add Classroom
	public static boolean addClassroom(String Id, String type, String building, String seats, String computers) {

		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(new Classroom(Id, type, building, seats, computers));
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

	//add Classroom
	public boolean addClass(Classroom c) {

		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(c);
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
	
	//add classroom
	public void saveClass(Classroom cl) { // static

		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(cl);
			System.out.println("Object saved successfully.....!!");
			// commit transaction
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateClass(Classroom room) { //static

		Transaction transaction = null;
		//boolean result = true;
		Session session = getSessionFactory().openSession();

		try{
			// start a transaction
			transaction = session.beginTransaction();
			room = (Classroom) session.get(Classroom.class, room.getId());
			session.update(room);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) 
				transaction.rollback();
			e.printStackTrace();
			}finally {
				session.close();
			}
		//return result;
	}

	public static boolean updateCapacity(String roomId, String seats) {

		Transaction transaction = null;
		boolean result = true;
		Session session = getSessionFactory().openSession();

		try{
			// start a transaction
			transaction = session.beginTransaction();
			Classroom cs = (Classroom) session.get(Classroom.class, roomId);
			cs.setSeats(seats);
			session.update(cs);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return result;
	}

	public static boolean removeClass(String Id) {

		Classroom classroom = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			classroom = (Classroom) session.get(Classroom.class, Id);
			session.delete(classroom);
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