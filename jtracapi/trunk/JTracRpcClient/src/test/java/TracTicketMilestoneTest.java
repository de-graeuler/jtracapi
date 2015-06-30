import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.model.field.TicketMilestoneField;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketMilestone;

public class TracTicketMilestoneTest {
	private static TracTicketMilestone milestone = null;

	@BeforeClass
	public static void setUpBeforeclass() throws Exception {
		AllTests.setUp();
		milestone = AllTests.trac.getTicketMilestoneApi();
	}

	@Test
	public void testGet() {
		try {
			milestone.delete("TestMilestone");
		} catch (XmlRpcException xe) {
		}

		Calendar due = new GregorianCalendar(2014, 07, 14);
		Map<String, Object> ca = new HashMap<String, Object>();
		ca.put("owner", "admin");
		ca.put("description", "Test Milestone owned by admin");
		ca.put("due", due.getTime());
		try {
			milestone.create("TestMilestone", ca);
		} catch (XmlRpcException xe) {
			fail("Creating the TestMilestone should not throw an Exception.");
		}
		try {
			milestone.create("TestMilestone", ca);
			fail("Creating the TestMilestone a second time should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}

		try {
			TicketMilestoneField m;
			m = milestone.get("TestMilestone");
			assertEquals("result name should be PDA PDA General",
					"TestMilestone", m.getName());
			m = milestone.get("Non Existing Milestone");
			fail("getting a non existing milestone should throw an exception.");
		} catch (XmlRpcException xe) {
			assertEquals(404, xe.code);
		}
		try {
			milestone.delete("TestMilestone");
		} catch (XmlRpcException xe) {
		}

	}

	@Test
	public void testGetAll() {
		List<String> lm = milestone.getAll();
		assertTrue( ! lm.isEmpty());
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
