package com.electricityBillingSystem.services;

import static com.electricityBillingSystem.util.ElectricityBillingSystemUtil.closeApplication;
import static com.electricityBillingSystem.util.ElectricityBillingSystemUtil.logout;
import static com.electricityBillingSystem.util.ReadUserData.getIntegerValue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.electricityBillingSystem.beans.Bill;
import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.dao.IUserDAO;
import com.electricityBillingSystem.dao.UserDao;

public class UserServices implements IUserServices {

	String line = new String(new char[40]).replace('\0', '-');

	@Override
	public void userHomePage(User user) throws Exception {

		System.out.println("________________________________________");
		System.out.println("********** User Home Page *************");
		System.out.println("________________________________________");
		System.out.println("1. View Unpaid Bill");
		System.out.println("2. View Your profile");
		System.out.println("3. History");
		System.out.println("4. Pay Bill");
		System.out.println("5. Logout");
		System.out.println("6. Close Application");
		System.out.println("________________________________________");
		int option = getIntegerValue("Option: ");

		switch (option) {
		case 1:
			viewUnpaidBills(user);
			break;
		case 2:
			displayUserInformation(user);
			break;

		case 3:
			history(user);
			break;

		case 4:
			payBill(user);
			break;
		case 5:
			logout(user);
			break;
		case 6:
			closeApplication();
			break;
		default:
			System.out.println("Please choose a correct option..\n");
			userHomePage(user);
			break;

		}

	}

	@Override
	public void displayUserInformation(User user) throws Exception {
		System.out.println("________________________________________");
		System.out.println("************** Your Profile ************");
		System.out.println("________________________________________\n");
		System.out.println("User ID: " + user.getId());
		System.out.println("Full Name: " + user.getName());

		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(user.getDob());

		System.out.println("Date of Birth: " + date);
		System.out.println("Mobile Number: " + user.getPhoneNumber());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Address: " + user.getAddress());
		System.out.println("\n****************************************");
		System.out.println("1. Edit Profile");
		System.out.println("2. Back to Home");
		System.out.println("3. Logout");
		System.out.println("4. Close Application");
		System.out.println("________________________________________");
		// System.out.println("Enter your choice:");
		int option = getIntegerValue("Option: ");

		switch (option) {
		case 1:
			break;
		case 2:
			userHomePage(user);
			break;
		case 3:
			logout(user);
			break;
		case 4:
			closeApplication();
			break;
		default:
			System.out.println("Please choose a correct option..\n");
			displayUserInformation(user);
			break;
		}
	}

	@Override
	public void generateBill(User user) throws Exception {

		System.out.println("________________________________________");
		System.out.println("************** Bill Generator **********");
		System.out.println("________________________________________");

		System.out.println("________________________________________");
		User customer = searchCustomerById(user.getId());
		if (customer == null) {
			System.out.println("Unable to find Customer.\nPlease check the Id..");
		} else {
			System.out.println("________________________________________");
			System.out.println("Generating Bill for - ");
			System.out.println("Name - " + customer.getName());
			System.out.println("For any query call Customer on " + customer.getPhoneNumber());
			System.out.println("________________________________________\n");

			// payBill();
			userHomePage(user);
		}

	}

	@Override
	public User searchCustomerById(String customerId) {

		IUserDAO userDao = new UserDao();
		User customer = userDao.searchCustomerById(customerId);
		return customer;

	}

	@Override
	public void payBill(User user) throws Exception {
		IUserDAO userDao = new UserDao();
		Bill unpaidBill = new Bill();

		List<Object[]> ebill = userDao.getUnpaidBill(user.getId());
		Object[] bill = ebill.get(0);
		if (bill != null) {
			unpaidBill.setId((String) bill[0]);
			unpaidBill.setUserId((String) bill[1]);
			unpaidBill.setUnit(((Integer) bill[2]).intValue());
			unpaidBill.setPrice(((BigDecimal) bill[3]).doubleValue());
			unpaidBill.setPaymentStatus((String) bill[4]);
			unpaidBill.setBillGenerationDate((Timestamp) bill[5]);
		}
		int amount = getIntegerValue("enter amount to be paid");

		if (amount == unpaidBill.getPrice()) {

			unpaidBill.setPaymentStatus("done");
			unpaidBill.setPaymentDate(new java.util.Date());
			userDao.payBill(unpaidBill);
			userDao.updatePaymentStatus(unpaidBill);
			userHomePage(user);
		} else {
			System.out.println("Please pay all amount at once! ");

			payBill(user);
		}

	}

	public void history(User user) throws Exception {
		IUserDAO userDao = new UserDao();
		List<Object[]> bills = userDao.history(user.getId());
		System.out.println("________________________________________");
		System.out.println("************** History ***********");
		if (bills != null) {
			for (Object[] bill : bills) {
				Bill unpaidBill = new Bill();
				unpaidBill.setId((String) bill[0]);
				unpaidBill.setUserId((String) bill[1]);
				unpaidBill.setUnit(((Integer) bill[2]).intValue());
				unpaidBill.setPrice(((BigDecimal) bill[3]).doubleValue());
				unpaidBill.setPaymentStatus((String) bill[4]);
				unpaidBill.setBillGenerationDate((Timestamp) bill[5]);
				System.out.println(line);
				System.out.println("\nBill Id: " + unpaidBill.getId());
				System.out.println("User Id: " + unpaidBill.getUserId());
				System.out.println("User Name: " + user.getName());
				System.out.println("Unit: " + unpaidBill.getUnit());
				System.out.println("price: " + unpaidBill.getPrice());
				System.out.println("Generated Date: " + unpaidBill.getBillGenerationDate());
				System.out.println("Payment Status: " + unpaidBill.getPaymentStatus());
			}

		} else {
			System.out.println("There is no bill due for " + user.getName());
		}
		System.out.println("\n****************************************");
		System.out.println("1. Back to Home");
		System.out.println("2. Logout");
		System.out.println("3. Close Application");
		System.out.println("________________________________________");
		System.out.println("Enter your choice:");
		int option = getIntegerValue("Option: ");

		switch (option) {
		case 1:
			userHomePage(user);
			break;
		case 2:
			logout(user);
			break;
		case 3:
			closeApplication();
			break;
		default:
			System.out.println("Please choose a correct option..\n");
			viewUnpaidBills(user);
			break;
		}

	}

	@Override
	public void viewUnpaidBills(User user) throws Exception {

		IUserDAO userDao = new UserDao();
		List<Object[]> bills = userDao.getUnpaidBill(user.getId());
		System.out.println("________________________________________");
		System.out.println("**************Unpaid Bills ***********");
		if (bills != null) {
			for (Object[] bill : bills) {
				Bill unpaidBill = new Bill();
				unpaidBill.setId((String) bill[0]);
				unpaidBill.setUserId((String) bill[1]);
				unpaidBill.setUnit(((Integer) bill[2]).intValue());
				unpaidBill.setPrice(((BigDecimal) bill[3]).doubleValue());
				unpaidBill.setPaymentStatus((String) bill[4]);
				unpaidBill.setBillGenerationDate((Timestamp) bill[5]);
				if (unpaidBill.getPaymentStatus().equalsIgnoreCase("not done")) {
					System.out.println(line);
					System.out.println("\nBill Id: " + unpaidBill.getId());
					System.out.println("User Id: " + unpaidBill.getUserId());
					System.out.println("User Name: " + user.getName());
					System.out.println("Unit: " + unpaidBill.getUnit());
					System.out.println("price: " + unpaidBill.getPrice());
					System.out.println("Generated Date: " + unpaidBill.getBillGenerationDate());
					System.out.println("Payment Status: " + unpaidBill.getPaymentStatus());
				}
			}

		} 
		System.out.println("\n****************************************");
		System.out.println("1. Back to Home");
		System.out.println("2. Logout");
		System.out.println("3. Close Application");
		System.out.println("________________________________________");
		System.out.println("Enter your choice:");
		int option = getIntegerValue("Option: ");

		switch (option) {
		case 1:
			userHomePage(user);
			break;
		case 2:
			logout(user);
			break;
		case 3:
			closeApplication();
			break;
		default:
			System.out.println("Please choose a correct option..\n");
			viewUnpaidBills(user);
			break;
		}

	}

}
