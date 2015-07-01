package de.graeuler.jtracapi.test.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketSeverity;


public class TracTicketSeverityTest {

	private static TracTicketSeverity severity = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		severity = AllTests.trac.getTicketSeverityApi();
	}

	@Test
	public void testGetString() {
		try {
			severity.create("testseverity", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			String p = severity.get("testseverity");
			assertEquals("10", p);
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			severity.get("does not exist");
			fail("Getting a non existing Prio should throw an exception.");
		} catch (XmlRpcException xe) {
		}
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		try {
			severity.create("testseverity", "10");
		} catch (XmlRpcException xe) {
		}
		List<String> p = severity.getAll();
		assertTrue("Severity List should not be empty", p.size() > 0);
		assertTrue("Severity List should contain 'testseverity'",
				p.contains("testseverity"));
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testDelete() {

		try {
			severity.create("testseverity", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			severity.delete("testseverity");
			fail("deleting a severity a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
		}
		try {
			severity.create("testseverity", "10");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			severity.create("testseverity", "10");
			fail("Creating should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		try {
			String test = severity.get("testseverity");
			assertEquals("Test-Severity should have value of 10", "10", test);
		} catch (XmlRpcException xe) {
			fail("getting the severity should not throw an exception");
		}
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testUpdate() {
		try {
			severity.create("testseverity", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			severity.update("testseverity", "11");
			String test = severity.get("testseverity");
			assertEquals("11", test);
		} catch (XmlRpcException e) {
			fail(e.getMessage());
		}
		try {
			severity.delete("testseverity");
		} catch (XmlRpcException xe) {
		}
	}

}
