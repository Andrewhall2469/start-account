package com.qa.service;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Account;

@Transactional(SUPPORTS)
public class Repoistory {
	
	public List<Account> getAllAccounts() 
	{
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account", Account.class);
		return query.getResultList();
	}
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	public Account findAccount(String accountNumber) 
	{
		return em.find(Account.class, accountNumber);
	}
	
	@Transactional(REQUIRED)
	public Account createAccount(Account account)
	{
		Account accountCreate = new Account("Andy", "H", "1234");
		em.getTransaction().begin();
		em.persist(accountCreate);
		em.getTransaction().commit();
		return accountCreate;
	}
	
	@Transactional(REQUIRED)
	public Account updateAccount(String accountNumber) {
		Account accountUpdate = em.find(Account.class, accountNumber);
		accountUpdate.setFirstName("John");
		accountUpdate.setSecondName("Johnson");
		em.getTransaction().commit();
		return accountUpdate;
	}
	
	@Transactional(REQUIRED)
	public Account deleteAccount(String accountNumber) {
		Account accountDelete = em.find(Account.class, accountNumber);
		em.getTransaction().begin();
		em.remove(accountDelete);
		em.getTransaction().commit();
		return accountDelete;
	}
	
	

}
