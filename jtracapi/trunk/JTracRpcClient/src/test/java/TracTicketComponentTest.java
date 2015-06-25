import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.field.TicketComponentField;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketComponent;

public class TracTicketComponentTest {

	private static TracApi trac = null;
	private static TracTicketComponent component = null;

	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http://192.168.56.101/test/login/rpc"));
		trac.setBasicAuthentication("admin", "admin");
		component = trac.getTicketComponentApi();
	}

	protected void createTestComponent() {
		try {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("owner", "tester");
			m.put("description", "A Test Component");
			component.create("TestComponent", m);
		} catch (XmlRpcException xe) {
		}
	}

	protected void deleteTestComponent() {
		try {
			component.delete("TestComponent");
		} catch (XmlRpcException xe) {
		}
	}

	@Test
	public void testGetAll() {
		createTestComponent();
		List<String> c = component.getAll();
		assertTrue("Component List should not be empty", c.size() > 0);
		assertTrue("Component List should contain 'TestComponent'",
				c.contains("TestComponent"));
		deleteTestComponent();
	}

	@Test
	public void testGet() {
		createTestComponent();
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
		deleteTestComponent();
	}

	@Test
	public void testDelete() {
		createTestComponent();
		try {
			component.delete("TestComponent");
		} catch (XmlRpcException xe) {
			fail(xe.getMessage());
		}
		try {
			component.delete("TestComponent");
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
			component.create("TestComponent", ca);
		} catch (XmlRpcException xe) {
			fail("Creating the TestComponent should not throw an Exception.");
		}
		try {
			component.create("TestComponent", ca);
			fail("Creating the TestComponent a second time should throw a uniqueness exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
		deleteTestComponent();
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
		deleteTestComponent();
		try {
			component.delete("RenamedTestComponent");
		} catch (XmlRpcException xe) {
		}
		
		createTestComponent();
		Map<String,Object> u = new HashMap<String, Object>();
		updateComponent("TestComponent", u, false);

		u.put("owner", "Tester2");
		u.put("description", "ABCDE");
		updateComponent("TestComponent", u, false);
		try {
			TicketComponentField c = component.get("TestComponent");
			assertEquals("Tester2", c.getOwner());
			assertEquals("ABCDE", c.getDescription());
		} catch (XmlRpcException e) {
		}
		
		// try rename
		u.clear();
		u.put("name", "RenamedTestComponent");
		updateComponent("TestComponent", u, false);

		// after successful rename, TestComponent should not exist, so Exception is expected
		updateComponent("TestComponent", u, true);

		// rename back.
		u.clear();
		u.put("name", "TestComponent");
		updateComponent("RenamedTestComponent", u, false);
		
		deleteTestComponent();
	}

}
