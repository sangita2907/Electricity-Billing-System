package com.electricityBillingSystem.dao;

import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.beans.UserRequest;
import com.electricityBillingSystem.exceptions.InvalidUseridPasswordException;

public interface ISystemDAO {
	public User validateUserCredentials(String userId, String userPassword)throws InvalidUseridPasswordException;
	
	public void addConnectionRequest(UserRequest userReq);
}
