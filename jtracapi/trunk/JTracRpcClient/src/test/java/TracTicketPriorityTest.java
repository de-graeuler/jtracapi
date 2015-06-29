import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketPriority;

public class TracTicketPriorityTest {

	private static TracTicketPriority priority = null;

	@Before
	public void setUp() throws Exception {
		AllTests.setUp();
		priority = AllTests.trac.getTicketPriorityApi();
	}

	@Test
	public void testGetString() {
		try {
			priority.create("testprio", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			String p = priority.get("testprio");
			assertEquals("10", p);
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			priority.get("does not exist");
			fail("Getting a non existing Prio should throw an exception.");
		} catch (XmlRpcException xe) {
		}
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		try {
			priority.create("testprio", "10");
		} catch (XmlRpcException xe) {
		}
		List<String> p = priority.getAll();
		assertTrue("Priority List should not be empty", p.size() > 0);
		assertTrue("Priority List should contain 'testprio'", p.contains("testprio"));
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testDelete() {

		try {
			priority.create("testprio", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			priority.delete("testprio");
			fail("deleting a prio a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
		}
		try {
			priority.create("testprio", "10");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			priority.create("testprio", "10");
			fail("Creating should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		try
		{
			String test = priority.get("testprio");
			assertEquals("Test-Priority should have value of 10", "10", test);
		} catch (XmlRpcException xe) {
			fail("getting the prio should not throw an exception");
		}
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testUpdate() {
		try {
			priority.create("testprio", "10");
		} catch (XmlRpcException xe) {
		}
		try {
			priority.update("testprio", "11");
			String test = priority.get("testprio");
			assertEquals("11", test);
		} catch (XmlRpcException e) {
			fail(e.getMessage());
		}
		try {
			priority.delete("testprio");
		} catch (XmlRpcException xe) {
		}
	}

}
