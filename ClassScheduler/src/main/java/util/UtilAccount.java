package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Account;

public class UtilAccount extends Hibernate {

	public static List<Account> listAccounts() {
		
		List<Account> resultList = new ArrayList<Account>(); 
		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> accounts = session.createQuery("FROM Account").list();
			for (Iterator<?> iterator = accounts.iterator(); iterator.hasNext();) {
				Account account = (Account) iterator.next();
				resultList.add(account);
			}
			transaction.commit();
		} catch (HibernateException e) {
			if ( transaction != null)
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


	public static Account getAccount(String username) {
		
		Account account = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("username", username)).uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		 //System.out.println("User's Name :" + account.getFirstName());
		return account;
	}

	//validate username
	public boolean validate(String username, String password) {

		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		Account acc = null;
		//boolean result = true;

		try {
			// start a transaction
			transaction = session.beginTransaction();
			
			acc = (Account) session.createQuery("FROM Account A WHERE A.username = :username").setParameter("username", username).uniqueResult();

			if (acc != null && acc.getPassword().equals(password)) {
				return true;
			}
			// commit transaction
			transaction.commit();
		} catch (HibernateException e) {
			//result = false;
			if (transaction != null) 
				transaction.rollback();
			e.printStackTrace();
			} finally {
				session.close();
			}
		return false;
	}

	public static boolean createAccounts(String username, String firstName, String lastName, String email, String password) {
		
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Account(username, firstName, lastName, email, password));
			System.out.println("Object saved successfully.....!!");
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
	
	public static void createAccount(String username, String firstname, String lastname, String email, String password) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Account(username, firstname, lastname, email, password));
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }


	public void saveAccount(Account acc) { //static
		
		Transaction transaction = null;
		//boolean result = true;
		Session session = getSessionFactory().openSession();
		try  {
			// start a transaction
			transaction = session.beginTransaction();
			session.save(acc);
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

}
