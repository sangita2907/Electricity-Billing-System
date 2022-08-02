package com.electricityBillingSystem.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.exceptions.DuplicateEntryException;
import com.electricityBillingSystem.util.HibernateUtil;

public class AdminDAO implements IAdminDAO{

	@Override
	public void registerNewCustomer(User user)
			throws SQLIntegrityConstraintViolationException, DuplicateEntryException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
	}

	@Override
	public List<Object[]> getAllCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String query = "select * from user where role_id = 2";
		NativeQuery nativeQuery = session.createSQLQuery(query); 
		List<Object[]> customerList = nativeQuery.list();
		session.close();
		
		return customerList;
		
	}

	@Override
	public User searchCustomerById(String customerId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		User customer = (User) session.get(User.class, customerId);
		session.close();	
		return customer;
	}

	@Override
	public void saveBill(Bill generatedBill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(generatedBill);
		session.getTransaction().commit();
		
	}

	@Override
	public List<Object[]> getUnpaidBills() {
Session session = HibernateUtil.getSessionFactory().openSession();
		
		String query = "select id, user_id, unit,price, bill_generation_date from bill where payment_status = 'not done'";
		NativeQuery nativeQuery = session.createSQLQuery(query); 
		List<Object[]> unpaidBillsList = nativeQuery.list();
		session.close();
		
		return unpaidBillsList;
	}

}
