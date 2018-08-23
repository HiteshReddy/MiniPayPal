package com.minipaypal.api.beans;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class User implements Serializable {

	@XmlElement
	private String accountNumber;
	@XmlElement
	private String userName;
	@XmlElement
	private String location;
	@XmlElement
	@NotNull
	private String email;
	@XmlElement
	@NotNull
	private UserType userType;
	@XmlElement
	private long amount = 0;
	@XmlElement
	@NotNull
	private CurrencyType currency;

	public User() {

	}

	public User(String accountNumber, String userName, String location,
			String email, UserType userType, long amount, CurrencyType currency) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.location = location;
		this.email = email;
		this.userType = userType;
		this.amount = amount;
		this.currency = currency;
	}

	public User(String userName, String location,
				String email, UserType userType, long amount, CurrencyType currency) {
		super();
		this.userName = userName;
		this.location = location;
		this.email = email;
		this.userType = userType;
		this.amount = amount;
		this.currency = currency;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}
}