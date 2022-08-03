package com.electricityBillingSystem.services;



import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;

public interface IUserServices {
	
	public void userHomePage(User user) throws Exception; 
	
	public void displayUserInformation(User user) throws Exception;
	
	public void generateBill(User user) throws Exception;
	
	public User searchCustomerById(String customerId);
	
	public void payBill(User user) throws Exception;
	
	public void viewUnpaidBills(User user) throws Exception;
	
	public void history(User user) throws Exception;

	
}
