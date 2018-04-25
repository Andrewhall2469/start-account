package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
@ApplicationScoped
public class MapRepository implements AccountRepo {

	
	
	@Inject
	private JSONUtil jSON;
	
	private Map<String, Account> map;
	
	public MapRepository() 
	{
	this.map = new HashMap<String, Account>();
	initialise();
	
	}
	public void initialise() {
		Account account = new Account("Andrew", "Hall", "1239");
		map.put("1239", account);
		
	}
	@Override
	public String getAllAccounts() {
		return jSON.getJSONForObject(map.values());
	}

	@Override
	public String createAccount(String account) {
		Account create = jSON.getObjectForJSON(account, Account.class);
		map.put("1239", create);
		return "Created";
	}

	@Override
	public String updateAccount(String accountNumber, String update) {
		Account updateAccount = jSON.getObjectForJSON(update, Account.class);
		map.put(accountNumber, updateAccount);
		return "Account Updated";
	}

	@Override
	public String deleteAccount(String accountNumber) {
		map.remove(accountNumber);
		return "Account Removed";
	}
	public void setjSON(JSONUtil jSON) {
		this.jSON = jSON;
	}

}
