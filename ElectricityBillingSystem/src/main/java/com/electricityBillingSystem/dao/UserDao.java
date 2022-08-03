package com.electricityBillingSystem.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.exceptions.DuplicateEntryException;
import com.electricityBillingSystem.util.HibernateUtil;

public class UserDao implements IUserDAO {

	@Override
	public User searchCustomerById(String customerId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		User customer = (User) session.get(User.class, customerId);
		session.close();
		return customer;
	}

	@Override
	public void payBill(Bill updatedBill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(updatedBill);
		session.getTransaction().commit();

	}
	
	public void updatePaymentStatus(Bill updateBill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String qry = "update bill set payment_status='done' where user_id='" + updateBill.getUserId()+"'";
		NativeQuery nativeQuery = session.createSQLQuery(qry);
		nativeQuery.executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public List<Object[]> getUnpaidBill(String id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String query = "select id, user_id, unit,price,payment_status, bill_generation_date from bill where payment_status = 'not done' and user_id='"+id+"'";
		NativeQuery nativeQuery = session.createSQLQuery(query); 
		List<Object[]> unpaidBillsList = nativeQuery.list();
		session.close();
		
		return unpaidBillsList;

		
	}
	
	@Override
	public List<Object[]> history(String id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String query = "select id, user_id, unit,price,payment_status, bill_generation_date from bill where user_id='"+id+"'";
		NativeQuery nativeQuery = session.createSQLQuery(query); 
		List<Object[]> unpaidBillsList = nativeQuery.list();
		session.close();
		
		return unpaidBillsList;

		
	}

	@Override
	public List<Object[]> getLatestUnpaidBill(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String query = "select month(bill_generation_date)+ from bill  where user_id = '" + id +"' order by bill_generation_date desc limit 1";
				;
		NativeQuery nativeQuery = session.createSQLQuery(query); 
		List<Object[]> unpaidBillsList = nativeQuery.list();
		session.close();
		
		return unpaidBillsList;
	}

}
