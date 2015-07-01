package de.graeuler.jtracapi.test.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketResolution;

public class TracTicketResolutionTest {

	private static TracTicketResolution resolution = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		resolution = AllTests.trac.getTicketResolutionApi();
	}

	@Test
	public void testGetString() {
		try {
			resolution.create("testresolution", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			String p = resolution.get("testresolution");
			assertEquals("10", p);
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			resolution.get("does not exist");
			fail("Getting a non existing Prio should throw an exception.");
		} catch (XmlRpcException xe) {
		}
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		try {
			resolution.create("testresolution", "10");
		} catch (XmlRpcException xe) {
		}
		List<String> p = resolution.getAll();
		assertTrue("Priority List should not be empty", p.size() > 0);
		assertTrue("Priority List should contain 'testprio'", p.contains("testresolution"));
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testDelete() {

		try {
			resolution.create("testresolution", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			resolution.delete("testresolution");
			fail("deleting a prio a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
		}
		try {
			resolution.create("testresolution", "10");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			resolution.create("testresolution", "10");
			fail("Creating should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		try
		{
			String test = resolution.get("testresolution");
			assertEquals("Test-Priority should have value of 10", "10", test);
		} catch (XmlRpcException xe) {
			fail("getting the prio should not throw an exception");
		}
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testUpdate() {
		try {
			resolution.create("testresolution", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			resolution.update("testresolution", "11");
			String test = resolution.get("testresolution");
			assertEquals("11", test);
		} catch (XmlRpcException e) {
			fail(e.getMessage());
		}
		try {
			resolution.delete("testresolution");
		} catch (XmlRpcException xe) {
		}
	}
	
}