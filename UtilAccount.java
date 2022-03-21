package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

import entities.Account;

public class UtilAccount extends UtilDB{

	//get all accounts
	public static List<Account> listAccounts() {
		
		List<Account> resultList = new ArrayList<Account>(); //null?
		Session session = Hibernate.getSessionFactory().openSession();
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
	public static Account getAccount(String username) {
		
		Account account = null;
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			//account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("username", username)).uniqueResult();
			account = (Account) session.get(Account.class, username);
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
	public boolean validate(int userId, String password) {

		Transaction transaction = null;
		Session session = Hibernate.getSessionFactory().openSession();
		Account acc = null;

		try {
			// start a transaction
			transaction = session.beginTransaction();
			
			acc = (Account) session.createQuery("FROM Account a WHERE a.username = :userName").setParameter("userName", userId)
					.uniqueResult();

			if (acc != null && acc.getPassword().equals(password)) {
				return true;
			}
			// commit transaction
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) 
				transaction.rollback();
			e.printStackTrace();
			} finally {
				session.close();
			}
		return false;
	}

	//create a new account
	public static boolean createAccount(int Id, String firstName, String lastName, String email, String password) {
		
		Session session = Hibernate.getSessionFactory().openSession();
		boolean result = true;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(new Account(Id, firstName, lastName, email, password));
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
		Session session = Hibernate.getSessionFactory().openSession();

		try  {
			// start a transaction
			transaction = session.beginTransaction();
			session.save(acc);
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