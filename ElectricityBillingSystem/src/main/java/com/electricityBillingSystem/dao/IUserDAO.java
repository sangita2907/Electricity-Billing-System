package com.electricityBillingSystem.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.electricityBillingSystem.exceptions.DuplicateEntryException;
import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;

public interface IUserDAO {
	
	
	
	
	public User searchCustomerById(String customerId);
	
	public void payBill(Bill generatedBill);
	
	public List<Object[]> getUnpaidBill(String id);
	
	public List<Object[]> getLatestUnpaidBill(String id);
	
	public void updatePaymentStatus(Bill updateBill) ;
	
	public List<Object[]> history(String id);
}
