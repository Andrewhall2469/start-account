package com.qa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MapRepositoryTest {

	private JSONUtil jSON;
	
	@InjectMocks
	private MapRepository mapRepository;
	
	@Before
	public void setup() 
	{
		jSON = new JSONUtil();
		mapRepository = new MapRepository();
		mapRepository.setjSON(jSON);
	}
	
	@Test
	public void testGetAllAccounts() {
		mapRepository.initialise();
		String value = "[{\"firstName\":\"Andrew\",\"secondName\":\"Hall\",\"accountNumber\":\"1239\"}]";
		assertEquals(value, mapRepository.getAllAccounts());
	}
	
	@Test
	public void testUpdateAccount()
	{
		String actualValue = mapRepository.updateAccount("1234", "[{\"firstName\":\"Andrewwwwwwww\",\"secondName\":\"Halllllll\",\"accountNumber\":\"1240\"}]");
		String expectedValue = "Account Updated";
		assertEquals(expectedValue, actualValue);
	}
	@Test
	public void testDeleteAccount()
	{
		String actualValue = mapRepository.deleteAccount("1239");
		String expectedValue = "Account Removed";
		assertEquals(expectedValue, actualValue);	
	}
	

}
