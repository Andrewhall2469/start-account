package com.qa.service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class Repoistory implements AccountRepository {
	
	
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil jSON;
	
	public Account findAccount(String accountNumber) 
	{
		return em.find(Account.class, accountNumber);
	}
	
	@Override
	public String getAllAccounts() 
	{
		Query query = em.createQuery("SELECT a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return jSON.getJSONForObject(accounts);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account)
	{
		Account newAccount = jSON.getObjectForJSON(account, Account.class);
		em.persist(newAccount);
		return "Account added";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String updateAccount(String accountNumber, String update) {
		Account accountUpdate = jSON.getObjectForJSON(update, Account.class);
		Account findAcc = findAccount(accountNumber);
		if (update != null) 
		{
			findAcc = accountUpdate;
			em.merge(findAcc);
		}
		return "Account updated";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(String accountNumber) {
		Account accountDelete = findAccount(accountNumber);
		if (accountDelete != null) 
		{
			em.remove(accountDelete);
		}
		return "Account removed from database";
	}
	
	public void setUtil(JSONUtil jSON) {
		this.jSON = jSON;
	}

	public void manager(EntityManager em) {
		this.em = em;
	}
	

}
