package com.electricityBillingSystem.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadUserData {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static Integer getIntegerValue(String msg) {
		System.out.println("Enter " + msg);
		int value = 0;
		try {
			value = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter any one of the above numbers as input....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getStringValue(String msg) throws IOException {
		// String value = null;
		System.out.println("Enter " + msg);
		String value = br.readLine();
		return value;
	}
	
	
	public static Long getLongValue(String msg) {
		System.out.println("Enter " + msg);
		Long value = 0L;
		try {
			value = Long.parseLong(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a correct input....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static Date getDateValue(String msg) {
		System.out.println("Enter " + msg);
		String value = null;
		try {
			value = br.readLine();
		} catch (IOException e) {
			System.out.println("Please enter a correct input...");

		}

		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(value);
		} catch (ParseException e) {
			System.out.println("Please enter a correct input..");
			// e.printStackTrace();
		}
		return date;
	}
}
