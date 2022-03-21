package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Building;

public class UtilBuilding extends UtilDB{

	//get all buildings
	public static List<Building> listBuilding() {
		List<Building> resultList = new ArrayList<Building>();

		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> buildings = session.createQuery("FROM Building").list();
			for (Iterator<?> iterator = buildings.iterator(); iterator.hasNext();) {
				Building b = (Building) iterator.next();
				resultList.add(b);
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

	//get Building
	public static Building getBuilding(String code) {
		
		Building building = null;
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			building = (Building) session.get(Building.class, code);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return building;
	}
}