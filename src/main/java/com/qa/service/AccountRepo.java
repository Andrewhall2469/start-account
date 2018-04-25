package com.qa.service;

public interface AccountRepo {

	String getAllAccounts();

	String createAccount(String account);

	String updateAccount(String accountNumber, String update);

	String deleteAccount(String accountNumber);

}