package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

import entities.Account;
import entities.Building;

public class UtilBuilding extends Hibernate {

	//get all buildings
	public static List<Building> listBuilding() {
		List<Building> resultList = new ArrayList<Building>();

		Session session = getSessionFactory().openSession();
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
	
	public static List<Account> listAccounts(String keyword) {
	      List<Account> resultList = new ArrayList<Account>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((Account)session.get(Account.class, 1)); 
	         List<?> accs = session.createQuery("FROM Account").list();
	         for (Iterator<?> iterator = accs.iterator(); iterator.hasNext();) {
	            Account ant = (Account) iterator.next();
	            if (ant.getFirstName().startsWith(keyword)) {
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

	//get Building
	public static Building getBuilding(String code) {
		
		Building building = null;
		Session session = getSessionFactory().openSession();
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