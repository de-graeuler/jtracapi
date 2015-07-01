package de.graeuler.jtracapi.test.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.model.field.TicketMilestoneField;
import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketMilestone;

public class TracTicketMilestoneTest {
	
	private static TracTicketMilestone milestone = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		milestone = AllTests.trac.getTicketMilestoneApi();
		deleteTestMilestone();
		createTestMilestone();
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		deleteTestMilestone();
	}

	protected static void createTestMilestone() {
		createTestMilestone("TestMilestone");
	}

	protected static void createTestMilestone(String name) {
		try {
			DateTime  dateTimeDue = DateTime.now().plusMonths(6);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("due", dateTimeDue.toDate());
			m.put("description", "A Test Milestone");
			milestone.create(name, m);
		} catch (XmlRpcException xe) {
		}
	}

	protected static void deleteTestMilestone() {
		deleteTestMilestone("TestMilestone");
	}

	protected static void deleteTestMilestone(String name) {
		try {
			milestone.delete(name);
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		List<String> c = milestone.getAll();
		assertTrue("Milestone List should not be empty", c.size() > 0);
		assertTrue("Milestone List should contain 'TestMilestone'",
				c.contains("TestMilestone"));
	}

	@Test
	public void testGet() {
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
	}

	@Test
	public void testDelete() {
		createTestMilestone("TestDeleteMilestone");
		try {
			milestone.delete("TestDeleteMilestone");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			milestone.delete("TestDeleteMilestone");
			fail("deleting a milestone a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		Map<String, Object> ca = new HashMap<String, Object>();
		DateTime  dateTimeDue = DateTime.now().plusMonths(6);
		ca.put("due", dateTimeDue.toDate());
		ca.put("description", "Test Milestone due in six months");
		try {
			milestone.create("TestCreateMilestone", ca);
		} catch (XmlRpcException xe) {
			fail("Creating the TestMilestone should not throw an Exception.");
		}
		try {
			milestone.create("TestCreateMilestone", ca);
			fail("Creating the TestMilestone a second time should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		deleteTestMilestone("TestCreateMilestone");
	}

	protected void updateMilestone(String c, Map<String,Object> u, boolean exceptionExpected)
	{
		try {
			milestone.update(c, u);
			if (exceptionExpected)
				fail("Exception expected when updating the Milestone");
		} catch (XmlRpcException e) {
			if ( ! exceptionExpected)
				fail("Unexpected Exception occurred.");
		}
	}
	@Test
	public void testUpdate() {
		deleteTestMilestone("RenamedTestMilestone");
		createTestMilestone("TestUpdateMilestone");
		
		Map<String,Object> u = new HashMap<String, Object>();
		updateMilestone("TestUpdateMilestone", u, false);

		DateTime  dateTimeCompleted = DateTime.now().plusMonths(6).plusDays(7);
		Date compareDate = dateTimeCompleted.toDate();
		u.put("completed", compareDate);
		u.put("description", "ABCDE");
		updateMilestone("TestUpdateMilestone", u, false);
		try {
			TicketMilestoneField c = milestone.get("TestUpdateMilestone");
			assertEquals(compareDate.getTime() / 1000, c.getCompleted().getTime() / 1000);
			assertEquals("ABCDE", c.getDescription());
		} catch (XmlRpcException e) {
		}
		
		// try rename
		u.clear();
		u.put("name", "RenamedTestMilestone");
		updateMilestone("TestUpdateMilestone", u, false);

		// after successful rename, TestMilestone should not exist, so Exception is expected
		updateMilestone("TestUpdateMilestone", u, true);

		// rename back.
		u.clear();
		u.put("name", "TestUpdateMilestone");
		updateMilestone("RenamedTestMilestone", u, false);
		
		deleteTestMilestone("TestUpdateMilestone");
	}

}
