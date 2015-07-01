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

import de.graeuler.jtracapi.model.field.TicketVersionField;
import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketVersion;


public class TracTicketVersionTest {

	private static TracTicketVersion version = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		version = AllTests.trac.getTicketVersionApi();
		deleteTestVersion();
		createTestVersion();
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		deleteTestVersion();
	}

	protected static void createTestVersion() {
		createTestVersion("TestVersion");
	}

	protected static void createTestVersion(String name) {
		try {
			DateTime  dateTimeDue = DateTime.now().plusMonths(6);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("time", dateTimeDue.toDate());
			m.put("description", "A Test Version");
			version.create(name, m);
		} catch (XmlRpcException xe) {
			fail("unable to create TestVersion");
		}
	}

	protected static void deleteTestVersion() {
		deleteTestVersion("TestVersion");
	}

	protected static void deleteTestVersion(String name) {
		try {
			version.delete(name);
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		List<String> c = version.getAll();
		assertTrue("Version List should not be empty", c.size() > 0);
		assertTrue("Version List should contain 'TestVersion'",
				c.contains("TestVersion"));
	}

	@Test
	public void testGet() {
		try {
			TicketVersionField m;
			m = version.get("TestVersion");
			assertEquals("result name should be PDA PDA General",
					"TestVersion", m.getName());
			m = version.get("Non Existing Version");
			fail("getting a non existing version should throw an exception.");
		} catch (XmlRpcException xe) {
			assertEquals(404, xe.code);
		}
	}

	@Test
	public void testDelete() {
		createTestVersion("TestDeleteVersion");
		try {
			version.delete("TestDeleteVersion");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			version.delete("TestDeleteVersion");
			fail("deleting a version a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		Map<String, Object> ca = new HashMap<String, Object>();
		DateTime  dateTimeDue = DateTime.now().plusMonths(6);
		ca.put("date", dateTimeDue.toDate());
		ca.put("description", "Test Version due in six months");
		try {
			version.create("TestCreateVersion", ca);
		} catch (XmlRpcException xe) {
			fail("Creating the TestVersion should not throw an Exception.");
		}
		try {
			version.create("TestCreateVersion", ca);
			fail("Creating the TestVersion a second time should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		deleteTestVersion("TestCreateVersion");
	}

	protected void updateVersion(String c, Map<String,Object> u, boolean exceptionExpected)
	{
		try {
			version.update(c, u);
			if (exceptionExpected)
				fail("Exception expected when updating the Version");
		} catch (XmlRpcException e) {
			if ( ! exceptionExpected)
				fail("Unexpected Exception occurred.");
		}
	}
	@Test
	public void testUpdate() {
		deleteTestVersion("RenamedTestVersion");
		deleteTestVersion("TestUpdateVersion");
		createTestVersion("TestUpdateVersion");
		
		Map<String,Object> u = new HashMap<String, Object>();
		updateVersion("TestUpdateVersion", u, false);

		DateTime  dateTimeCompleted = DateTime.now().plusMonths(6).plusDays(7);
		Date compareDate = dateTimeCompleted.toDate();
		u.put("time", compareDate);
		u.put("description", "ABCDE");
		updateVersion("TestUpdateVersion", u, false);
		try {
			TicketVersionField c = version.get("TestUpdateVersion");
			assertEquals(compareDate.getTime() / 1000, c.getTime().getTime() / 1000);
			assertEquals("ABCDE", c.getDescription());
		} catch (XmlRpcException e) {
		}
		
		// try rename
		u.clear();
		u.put("name", "RenamedTestVersion");
		updateVersion("TestUpdateVersion", u, false);

		// after successful rename, TestVersion should not exist, so Exception is expected
		updateVersion("TestUpdateVersion", u, true);

		// rename back.
		u.clear();
		u.put("name", "TestUpdateVersion");
		updateVersion("RenamedTestVersion", u, false);
		
		deleteTestVersion("TestUpdateVersion");
	}

}
