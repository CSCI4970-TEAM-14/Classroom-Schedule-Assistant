package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;

import entities.Department;

public class UtilDepartment extends Hibernate{

	//get all Departments
	public static List<Department> listDepartment() {
		List<Department> resultList = new ArrayList<Department>();

		Session session = getSessionFactory().openSession();
		Transaction transaction = null; 

		try {
			transaction = session.beginTransaction();
			List<?> depts = session.createQuery("FROM Department").list();
			for (Iterator<?> iterator = depts.iterator(); iterator.hasNext();) {
				Department d = (Department) iterator.next();
				resultList.add(d);
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

	//get Department with specified code
	public static Department getDepartment(String code) {
		
		Department dept = null;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			dept = (Department) session.get(Department.class, code);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dept;
	}
}
