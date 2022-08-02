package com.electricityBillingSystem.services;

import java.util.Objects;

import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.dao.ISystemDAO;
import com.electricityBillingSystem.dao.SystemDAO;
import com.electricityBillingSystem.exceptions.InvalidUseridPasswordException;

import static com.electricityBillingSystem.util.ReadUserData.getStringValue;


public class SystemServices implements ISystemServices{

	@Override
	public void login() throws Exception {
		System.out.println("________________________________________");
		System.out.println("**************** Log In ****************");
		
		String userId = getStringValue(" ID*: ");
		String userPassword = getStringValue("Password*: ");
		
		ISystemDAO systemDao = new SystemDAO();
		try {
			User user = systemDao.validateUserCredentials(userId, userPassword);
			if (Objects.isNull(user)) {
				System.out.println("Please enter correct email and password..");
			} else {
				if(user.getRoleId() == 1) {
					IAdminServices adminServices = new AdminServices();
					adminServices.adminHomePage(user);
				}else {
					//userHomePage();
				}
			}
		} catch (InvalidUseridPasswordException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
