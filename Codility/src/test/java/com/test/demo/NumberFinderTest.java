/**
 * 
 */
package com.test.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import demo.CustomNumberEntity;
import demo.NumberFinderImpl;
import junit.framework.Assert;

/**
 * @author Ajin
 *
 */
public class NumberFinderTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link demo.NumberFinderImpl#contains(int, java.util.List)}.
	 */
	@Test
	public void testContains() {
		NumberFinderImpl impl = new NumberFinderImpl();
		String file = getClass().getClassLoader().getResource("numbers.json").getPath();
		List<CustomNumberEntity> customers = impl.readFromFile(file);
		 long startTime = System.currentTimeMillis();
		System.out.println(impl.contains(67, customers));
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken : "+(endTime-startTime)/1000 + " secs");
	}

	/**
	 * Test method for {@link demo.NumberFinderImpl#readFromFile(java.lang.String)}.
	 */
	@Test
	public void testReadFromFile_happyPath() {
		NumberFinderImpl impl = new NumberFinderImpl();
		String file = getClass().getClassLoader().getResource("numbers.json").getPath();
		List<CustomNumberEntity> customers = impl.readFromFile(file);
			Assert.assertEquals(customers.get(0).getNumber(), "67");
			Assert.assertEquals(customers.size(), 9);
		
	}

}
