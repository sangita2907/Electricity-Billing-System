package com.electricityBillingSystem.util;

import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.App;

public class ElectricityBillingSystemUtil {
	
	public static void closeApplication() {
		System.out.println("Application Closed...");
		System.exit(0);
	}
	
	public static void adminLogout(User user) {
		user = null;
		System.out.println("Successfully Logout...");
		try {
			App.main(new String[0]);
		} catch (Exception e) {
			System.out.println("Problem Occured..");
			e.printStackTrace();
		}
	}
}
