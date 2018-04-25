package com.qa.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;


@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

	@InjectMocks
	private Repoistory repository;

	@Mock
	private EntityManager em;

	@Mock
	private Query query;
	
	private JSONUtil jSON;
	
	@Before
	public void setup() {
		repository.manager(em);
		jSON = new JSONUtil();
		repository.setUtil(jSON);
	}
	
	@Test
	public void createAccount() 
	{
		String actualValue = repository.createAccount("{\"firstName\":\"Hall\",\"secondName\":\"Andy\",\"accountNumber\":\"1237\"}");
		String expectedValue = "Account added";
		Assert.assertEquals(actualValue , expectedValue);
	}
	
	
	@Test
	public void testGetAllAccounts() 
	{
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> testAccounts = new ArrayList<Account>();
		testAccounts.add(new Account("Hall", "Andy", "12347"));
		Mockito.when(query.getResultList()).thenReturn(testAccounts);
		String expectedValue = "[{\"firstName\":\"Hall\",\"secondName\":\"Andy\",\"accountNumber\":\"12347\"}]";
		String actualValue = repository.getAllAccounts();
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testUpdateAccount()
	{
		String expectedValue = repository.updateAccount("12347", "{\"firstName\":\"Hall\",\"secondName\":\"Andy\",\"accountNumber\":\"1234\"}");
		String actualValue = "Account updated"; 
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testDeleteAccount() 
	{
		String actualValue = repository.deleteAccount("12347");
		String expectedValue = "Account removed from database";
		Assert.assertEquals(actualValue, expectedValue);
		
	}

}
