package Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import models.Department;

public class UtilDepartment extends UtilDB{

	//get all Departments
	public static List<Department> listDepartment() {
		List<Department> resultList = new ArrayList<Department>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> depts = session.createQuery("FROM Department").list();
			for (Iterator<?> iterator = depts.iterator(); iterator.hasNext();) {
				Department d = (Department) iterator.next();
				resultList.add(d);
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

	//get Department with specified code
	public static Department getDepartment(String code) {
		Department dept = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			dept = (Department) session.createCriteria(Department.class).add(Restrictions.eq("code", code)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dept;
	}
}