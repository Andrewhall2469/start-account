package com.qa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT")
		public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length = 4, name="ACCOUNT_NUMBER")
	private int accountNumber;
	
	@Column(length = 50, name="FIRST_NAME")
	private String firstName;
	
	@Column(length = 50, name="SECOND_NAME")
	private String secondName;

	public Account(String firstName, String secondName, int accountNumber) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

}
