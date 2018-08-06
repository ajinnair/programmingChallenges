package com.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class UniqueCountriesProblemTest {

	@Test
	public void testGetUniqueCountries() {
		int M[][]= {  {5, 4, 4},
		        {4, 3, 4},
		        {3, 2, 4},
		        {2, 2, 2},
		        {3, 3, 4},
		        {1, 4, 4},
		        {4, 1, 1}
		    };
		
		UniqueCountriesProblem countriesProblem = new UniqueCountriesProblem();
		
		
		Assert.assertEquals("Expected Unique countries doesn't match", 11, countriesProblem.getUniqueCountries(M));
	}

}
