/**
 * 
 */
package com.test.demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.CustomNumberEntity;
import demo.NumberFinderImpl;
import junit.framework.Assert;

/**
 * @author Ajin
 *
 */
public class NumberFinderTest {

	Logger logger = LoggerFactory.getLogger(getClass());
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
	public void testContains_happyPath() {
		NumberFinderImpl impl = new NumberFinderImpl();
		String file = getClass().getClassLoader().getResource("numbers.json").getPath();
		List<CustomNumberEntity> customers = impl.readFromFile(file);
		 long startTime = System.currentTimeMillis();
		Assert.assertTrue(impl.contains(-3, customers));
		long endTime = System.currentTimeMillis();
		logger.info("Time taken : " + (endTime - startTime) / 1000 + " secs");
	}

	/**
	 * Test method for {@link demo.NumberFinderImpl#contains(int, java.util.List)}.
	 */
	@Test
	public void testContains_numberMising() {
		NumberFinderImpl impl = new NumberFinderImpl();
		String file = getClass().getClassLoader().getResource("numbers.json").getPath();
		List<CustomNumberEntity> customers = impl.readFromFile(file);
		long startTime = System.currentTimeMillis();
		Assert.assertFalse(impl.contains(-33, customers));
		long endTime = System.currentTimeMillis();
		logger.info("Time taken : " + (endTime - startTime) / 1000 + " secs");
	}

	@Test
	public void testContains_badFile() {
		NumberFinderImpl impl = new NumberFinderImpl();
		String file = getClass().getClassLoader().getResource("numbers2.json").getPath();
		List<CustomNumberEntity> customers = impl.readFromFile(file);
		long startTime = System.currentTimeMillis();
		Assert.assertFalse(impl.contains(-33, customers));
		long endTime = System.currentTimeMillis();
		logger.info("Time taken : " + (endTime - startTime) / 1000 + " secs");
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
		Assert.assertEquals(customers.size(), 10);
		
	}

	@Test
	public void testReadFromFile_InvalidPath() {
		NumberFinderImpl impl = new NumberFinderImpl();

		List<CustomNumberEntity> customers = impl.readFromFile("numbers.jsonfgfg");
		Assert.assertNull(customers);

	}

}
