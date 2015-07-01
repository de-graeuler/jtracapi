package de.graeuler.jtracapi.test.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketType;


public class TracTicketTypeTest {

	private static TracTicketType ticketType = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		ticketType = AllTests.trac.getTicketTypeApi();
	}

	@Test
	public void testGetString() {
		try {
			ticketType.create("testtype", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			String p = ticketType.get("testtype");
			assertEquals("10", p);
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			ticketType.get("does not exist");
			fail("Getting a non existing Prio should throw an exception.");
		} catch (XmlRpcException xe) {
		}
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		try {
			ticketType.create("testtype", "10");
		} catch (XmlRpcException xe) {
		}
		List<String> p = ticketType.getAll();
		assertTrue("Type List should not be empty", p.size() > 0);
		assertTrue("Type List should contain 'testtype'",
				p.contains("testtype"));
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testDelete() {

		try {
			ticketType.create("testtype", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			ticketType.delete("testtype");
			fail("deleting a type a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
		}
		try {
			ticketType.create("testtype", "10");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			ticketType.create("testtype", "10");
			fail("Creating should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		try {
			String test = ticketType.get("testtype");
			assertEquals("Test-Type should have value of 10", "10", test);
		} catch (XmlRpcException xe) {
			fail("getting the type should not throw an exception");
		}
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testUpdate() {
		try {
			ticketType.create("testtype", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			ticketType.update("testtype", "11");
			String test = ticketType.get("testtype");
			assertEquals("11", test);
		} catch (XmlRpcException e) {
			fail(e.getMessage());
		}
		try {
			ticketType.delete("testtype");
		} catch (XmlRpcException xe) {
		}
	}
}
