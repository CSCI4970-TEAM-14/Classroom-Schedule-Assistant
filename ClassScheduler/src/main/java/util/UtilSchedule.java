package util;

import java.util.List;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Classroom;
import entities.Enrollment;
import entities.Schedule;


public class UtilSchedule extends Hibernate {

	//get all Schedules
	public static List<Schedule> listSchedule() {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> schedules = session.createQuery("FROM Schedule").list();
			for (Iterator<?> iterator = schedules.iterator(); iterator.hasNext();) {
				Schedule sh = (Schedule) iterator.next();
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
	
	public static List<Schedule> listSchedules(String keyword) {
	      List<Schedule> resultList = new ArrayList<Schedule>();
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         System.out.println((Schedule)session.get(Schedule.class, 1)); 
	         List<?> sch = session.createQuery("FROM Schedule").list();
	         for (Iterator<?> iterator = sch.iterator(); iterator.hasNext();) {
	            Schedule sh = (Schedule) iterator.next();
	            if (sh.getDay().startsWith(keyword)) {
	               resultList.add(sh);
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
	
		public static Schedule addSchedule(Schedule sh) {

			Transaction transaction = null;
			// boolean result = true;
			Session session = getSessionFactory().openSession();

			try {
				// start a transaction
				transaction = session.beginTransaction();
				session.save(sh);
				System.out.println("Object saved successfully.....!!");
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				// result = false;
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return sh;
		}
		
		//add

		public void saveSchedule(Schedule sh) { //static
			
			Transaction transaction = null;
			//boolean result = true;
			Session session = getSessionFactory().openSession();
			try  {
				// start a transaction
				transaction = session.beginTransaction();
				session.save(sh);
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
		}

	//get all schedules on the same day
	public static List<Schedule> getSchedulesByDay() {
		List<Schedule> resultList = new ArrayList<Schedule>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		String day = null;

		try {
			transaction = session.beginTransaction();
			List<?> schedule = session.createCriteria(Schedule.class).add(Restrictions.eq("day", day)).list();
			for (Iterator<?> iterator = schedule.iterator(); iterator.hasNext();) {
				Schedule sh = (Schedule) iterator.next();
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

	//get all schedules associated with a course section by time and day
	public static List<Schedule> getScheduleBySection(String sectionId) {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			List<?> sched = session.createCriteria(Schedule.class).add(Restrictions.eq("sectionId", sectionId)).list();
			for (Iterator<?> iterator = sched.iterator(); iterator.hasNext();) {
				Schedule sh = (Schedule) iterator.next();
				resultList.add(sh);
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

	//get all active Schedules associated with a sectionId
	public static List<Schedule> getActiveSchedules(String sectionId) {
		List<Schedule> resultList = new ArrayList<Schedule>();
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Enrollment en = new Enrollment();

		try {
			tx = session.beginTransaction();

			List<?> sched = session.createCriteria(Schedule.class).add(Restrictions.eq("sectionId", sectionId)).list();
			for (Iterator<?> iterator = sched.iterator(); iterator.hasNext();) {
				Schedule sh = (Schedule) iterator.next();
				if(en.getSeats().length() != -1)
					resultList.add(sh);
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
	
	public static boolean addSchedule(String day, String start, String end) {

		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(new Classroom(day, start, end));
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

	//add Schedules
	public static boolean updateSchedule(String day, String startTime, String endTime) {
		
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Schedule s = (Schedule) session.get(Schedule.class,day);
			s.setDate(day);
			s.setEndTime(endTime);
			s.setStartTime(startTime);
			session.update(s);
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
	
	public static boolean removeSchedule(String day, String startTime, String endTime, int sectionId) {

		Schedule sch = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = true;

		try {
			transaction = session.beginTransaction();
			sch = (Schedule) session.get(Schedule.class, sectionId);
			session.delete(sch);
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
	
	public void updateSchedule(Schedule sh) {
	   
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
		    session.update(sh);
		    tx.commit();
		} catch (HibernateException e) {
			//result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (java.lang.NumberFormatException e) {
			//result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	  }
}
