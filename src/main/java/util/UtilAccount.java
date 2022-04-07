package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

import entities.Account;

public class UtilAccount extends Hibernate {

	//get all accounts
	public static List<Account> listAccounts() {
		
		List<Account> resultList = new ArrayList<Account>(); //null?
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

	//get account with username
	public static Account getAccount(String Id) {
		
		Account account = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			account = (Account) session.get(Account.class, Id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	//validate username
	public boolean validate(String userId, String password) {

		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		Account acc = null;
		boolean result = true;

		try {
			// start a transaction
			transaction = session.beginTransaction();
			
			acc = (Account) session.createQuery("FROM Account a WHERE a.id = :userId").setParameter("userId", userId).uniqueResult();

			if (acc != null && acc.getPassword().equals(password)) {
				return true;
			}
			// commit transaction
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

	//create a new account
	public static boolean createAccount(String id, String firstName, String lastName, String email, String password) {
		
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Account(id, firstName, lastName, email, password));
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


	public static boolean saveAccount(Account acc) {
		
		Transaction transaction = null;
		boolean result = true;
		Session session = getSessionFactory().openSession();

		try  {
			// start a transaction
			transaction = session.beginTransaction();
			session.save(acc);
			System.out.println("Object saved successfully.....!!");
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			result = false;
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return result;
	}

}
