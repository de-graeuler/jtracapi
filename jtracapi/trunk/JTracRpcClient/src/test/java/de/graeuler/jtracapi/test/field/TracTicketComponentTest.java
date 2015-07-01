package de.graeuler.jtracapi.test.field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.model.field.TicketComponentField;
import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketComponent;

public class TracTicketComponentTest {

	private static TracTicketComponent component = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		component = AllTests.trac.getTicketComponentApi();
		deleteTestComponent();
		createTestComponent();
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		deleteTestComponent();
	}

	protected static void createTestComponent() {
		createTestComponent("TestComponent");
	}

	protected static void createTestComponent(String name) {
		try {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("owner", "tester");
			m.put("description", "A Test Component");
			component.create(name, m);
		} catch (XmlRpcException xe) {
		}
	}

	protected static void deleteTestComponent() {
		deleteTestComponent("TestComponent");
	}

	protected static void deleteTestComponent(String name) {
		try {
			component.delete(name);
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		List<String> c = component.getAll();
		assertTrue("Component List should not be empty", c.size() > 0);
		assertTrue("Component List should contain 'TestComponent'",
				c.contains("TestComponent"));
	}

	@Test
	public void testGet() {
		try {
			TicketComponentField m;
			m = component.get("TestComponent");
			assertEquals("result name should be PDA PDA General",
					"TestComponent", m.getName());
			m = component.get("Non Existing Component");
			fail("getting a non existing component should throw an exception.");
		} catch (XmlRpcException xe) {
			assertEquals(404, xe.code);
		}
	}

	@Test
	public void testDelete() {
		createTestComponent("TestDeleteComponent");
		try {
			component.delete("TestDeleteComponent");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			component.delete("TestDeleteComponent");
			fail("deleting a component a second time should throw an exception.");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testCreate() {
		Map<String, Object> ca = new HashMap<String, Object>();
		ca.put("owner", "admin");
		ca.put("description", "Test Component owned by admin");
		try {
			component.create("TestCreateComponent", ca);
		} catch (XmlRpcException xe) {
			fail("Creating the TestComponent should not throw an Exception.");
		}
		try {
			component.create("TestCreateComponent", ca);
			fail("Creating the TestComponent a second time should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		deleteTestComponent("TestCreateComponent");
	}

	protected void updateComponent(String c, Map<String,Object> u, boolean exceptionExpected)
	{
		try {
			component.update(c, u);
			if (exceptionExpected)
				fail("Exception expected when updating the Component");
		} catch (XmlRpcException e) {
			if ( ! exceptionExpected)
				fail("Unexpected Exception occurred.");
		}
	}
	@Test
	public void testUpdate() {
		deleteTestComponent("RenamedTestComponent");
		createTestComponent("TestUpdateComponent");
		
		Map<String,Object> u = new HashMap<String, Object>();
		updateComponent("TestUpdateComponent", u, false);

		u.put("owner", "Tester2");
		u.put("description", "ABCDE");
		updateComponent("TestUpdateComponent", u, false);
		try {
			TicketComponentField c = component.get("TestUpdateComponent");
			assertEquals("Tester2", c.getOwner());
			assertEquals("ABCDE", c.getDescription());
		} catch (XmlRpcException e) {
		}
		
		// try rename
		u.clear();
		u.put("name", "RenamedTestComponent");
		updateComponent("TestUpdateComponent", u, false);

		// after successful rename, TestComponent should not exist, so Exception is expected
		updateComponent("TestUpdateComponent", u, true);

		// rename back.
		u.clear();
		u.put("name", "TestUpdateComponent");
		updateComponent("RenamedTestComponent", u, false);
		
		deleteTestComponent("TestUpdateComponent");
	}

}
