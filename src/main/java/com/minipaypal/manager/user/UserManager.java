package com.minipaypal.manager.user;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minipaypal.api.beans.TransactionResponse;
import com.minipaypal.api.beans.User;

public class UserManager {
	
	private static Map<String, User> userMap = new HashMap<>();
	private static int currentAccountNumber = 123456;
	
	static {
		User user = new User();
		user.setUserName("dummy");
		user.setLocation("bangalore");
		user.setEmail("dummy@gmail.com");
		createUser(user);
	}
	
	public static int createUser(User user) {
		
		synchronized (UserManager.class) {
			currentAccountNumber += 1;
			user.setAccountNumber(String.valueOf(currentAccountNumber));
			userMap.put(String.valueOf(currentAccountNumber), user);
		}
		
		return currentAccountNumber;
	}
	
	/**
	 * returns the user object for the given account number
	 * 
	 * @param acct_no
	 * @return user
	 */
	public static User getUser(String acct_no) {
		if(userMap.containsKey(acct_no)) {
			return userMap.get(acct_no);
		}
		return null;
	}
	
	public static boolean isDuplicateUser(User user) {
		for(User currUser : userMap.values()) {
			if(user.getEmail().equalsIgnoreCase(currUser.getEmail())) {
				return true;
			}
		}
		return false;
	}

}
