package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

//import entities.Classroom;
import entities.Enrollment;
import entities.Schedule;


public class UtilSchedule extends UtilDB{

	//get all Schedules
	public static List<Schedule> listSchedule() {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = Hibernate.getSessionFactory().openSession();
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

	//get all schedules on the same day
	public static List<Schedule> getSchedulesByDay(String day) {
		
		List<Schedule> resultList = new ArrayList<Schedule>();
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

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
	public static List<Schedule> getScheduleBySection(Integer sectionId) {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = Hibernate.getSessionFactory().openSession();
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
	public static List<Schedule> getActiveSchedules(Integer sectionId) {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = Hibernate.getSessionFactory().openSession();
		Transaction tx = null;
		Enrollment en = null;

		try {
			tx = session.beginTransaction();

			List<?> sched = session.createCriteria(Schedule.class).add(Restrictions.eq("sectionId", sectionId)).list();
			for (Iterator<?> iterator = sched.iterator(); iterator.hasNext();) {
				Schedule sh = (Schedule) iterator.next();
				if(en.getSeats() != -1)
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

	//add Schedules
	public static boolean updateSchedule(String day, String startTime, String endTime, int sectionId) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Schedule s = (Schedule) session.get(Schedule.class, sectionId);
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
		Session session = Hibernate.getSessionFactory().openSession();
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

	//get int formatted time: 11:30 to 1130
	public static int getIntTime(String time) {
		String[] array = time.split("\\s*:\\s*");
		return (Integer.parseInt(array[0]) * 100) + Integer.parseInt(array[1]);
	}

	//checks whether the given start and end time are available during the given date
	public static boolean available(String day, String startTime, String endTime) {
		List<Schedule> list = UtilSchedule.getSchedulesByDay(day);
		if( list.size() == 0)
			return true;

		int start = UtilSchedule.getIntTime(startTime);
		int end = UtilSchedule.getIntTime(endTime);

		// adjust for midnight
		if(end == 0)
			end = 2400;

		//for each existing Schedule check whether there is any overlap
		for(Schedule sched: list) {
			if( ( start >= UtilSchedule.getIntTime(sched.getStartTime()) && 
					start <= UtilSchedule.getIntTime(sched.getEndTime()) ) || 
					( end >= UtilSchedule.getIntTime(sched.getStartTime()) && 
					end <= UtilSchedule.getIntTime(sched.getEndTime()) ))
				return false;
		}

		return true;
	}
}