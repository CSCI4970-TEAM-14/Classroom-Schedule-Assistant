package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Schedule;
import models.Enrollment;


public class UtilSchedule extends UtilDB{

	//get all Schedules
	public static List<Schedule> listSchedule() {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> schedules = session.createQuery("FROM Schedule").list();
			for (Iterator<?> iterator = schedules.iterator(); iterator.hasNext();) {
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

	//get all schedules on the same day
	public static List<Schedule> getSchedulesByDay(String day) {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			List<?> schedule = session.createCriteria(Schedule.class).add(Restrictions.eq("day", day)).list();
			for (Iterator<?> iterator = schedule.iterator(); iterator.hasNext();) {
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

	//get all schedules associated with a course section by time and day
	public static List<Schedule> getScheduleBySection(Integer sectionId) {
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
	public static List<Schedule> getActiveSchedules(Integer sectionId) {
		List<Schedule> resultList = new ArrayList<Schedule>();

		Session session = getSessionFactory().openSession();
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
	public static boolean updateSchedule(String day, String startTime, String endTime) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Schedule(day, startTime, endTime));
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


	//converts 24hr format to 12hr format
	public static String getFormattedTime(String startTime, String endTime) {
		String result1 = "";
		String[] array1 = startTime.split("\\s*:\\s*");

		if(Integer.parseInt(array1[0]) < 12) {
			if( Integer.parseInt(array1[0]) == 0)
				result1 += "12:" + array1[1] + "am";
			else
				result1 += array1[0] + ":" + array1[1] + "am";

		}
		else {
			if( Integer.parseInt(array1[0]) == 12)
				result1 += array1[0] + ":" + array1[1] + "pm";
			else
				result1 += String.valueOf(Integer.parseInt(array1[0]) - 12) + ":" + array1[1] + "pm";
		}

		String result2 = "";
		String[] array2 = endTime.split("\\s*:\\s*");

		if(Integer.parseInt(array2[0]) < 12) {
			if( Integer.parseInt(array2[0]) == 0)
				result2 += "12:" + array2[1] + "am";
			else
				result2 += array2[0] + ":" + array2[1] + "am";

		}
		else {
			if( Integer.parseInt(array2[0]) == 12)
				result2 += array2[0] + ":" + array2[1] + "pm";
			else
				result2 += String.valueOf(Integer.parseInt(array2[0]) - 12) + ":" + array2[1] + "pm";
		}
		return result1 + " - " + result2;
	}
}