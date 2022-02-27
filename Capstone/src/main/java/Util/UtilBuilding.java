package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Building;

public class UtilBuilding extends UtilDB{

	//get all buildings
	public static List<Building> listBuilding() {
		List<Building> resultList = new ArrayList<Building>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> buildings = session.createQuery("FROM Building").list();
			for (Iterator<?> iterator = buildings.iterator(); iterator.hasNext();) {
				Building b = (Building) iterator.next();
				resultList.add(b);
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

	//get Building
	public static Building getBuilding(String code) {
		Building building = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			building = (Building) session.createCriteria(Building.class).add(Restrictions.eq("code", code)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return building;
	}
}