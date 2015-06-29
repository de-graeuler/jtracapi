import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.ticket.Ticket;
import de.graeuler.jtracapi.model.ticket.TicketAction;
import de.graeuler.jtracapi.model.ticket.TicketActionList;
import de.graeuler.jtracapi.model.ticket.TicketField;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicket;

public class TracTicketTest {

	private static TracApi trac = null;
	private static TracTicket ticket = null;
	private static Integer testTicketId;

	@BeforeClass
	public static void setUp() throws Exception {
		trac = new TracApi(new URL("http://192.168.56.101/test/login/rpc"));
		trac.setBasicAuthentication("admin", "admin");
		ticket = trac.getTicketApi();
		createTestTicket();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		deleteTestTickets();
	}

	private static void deleteTestTickets() {
		try {
			List<Integer> tickets = ticket.query("summary=TicketSummary");
			for (Integer integer : tickets) {
				ticket.delete(integer);
			}
		} catch (Throwable ignore) {
		}
	}

	private static void createTestTicket() {
		Map<String, Object> a = new HashMap<String, Object>();
		a.put("owner", "jquerytest");
		try {
			testTicketId = ticket.create("TicketSummary", "TicketDescription",
					a);
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testQuery() {
		List<Integer> tickets;
		try {
			tickets = ticket.query("owner=jquerytest");
			assertTrue("someLibraryMethod should return 'true'",
					tickets.size() > 0);
		} catch (XmlRpcException xe) {
			fail("A valid query should not cause an exception.");
		}
		try {
			tickets = ticket.query("invalidSyntax == && shouldraiseException ");
			fail("An invalid query should raise an exception.");
		} catch (XmlRpcException xe) {
			assertEquals(1, xe.code);
		}
	}

	@Test
	public void testGetRecentChanges() {
		try {
			// get and update ticket to get recent changes
			Ticket t = ticket.get(testTicketId);
			Map<String, Object> a = new HashMap<String, Object>();
			a.put("cc", "cc.test@example.com");
			a.put("_ts", t.getChangeToken());
			ticket.update(testTicketId, "TestingGetRecentChanges", a);

			// using joda time as per
			// http://stackoverflow.com/questions/308683/how-can-i-get-the-current-date-and-time-in-utc-or-gmt-in-java
			// http://www.mkyong.com/java/java-convert-date-and-time-between-timezone/
			DateTime changesSince = DateTime.now(DateTimeZone.UTC)
					.minusMinutes(2);
			List<Integer> l = ticket.getRecentChanges(changesSince
					.toLocalDateTime().toDate());
			assertTrue(!l.isEmpty());
		} catch (XmlRpcException xe) {

		}
	}

	@Test
	public void testGetActions() {
		try {
			TicketActionList<TicketAction> lta = ticket
					.getActions(testTicketId);
			assertTrue(!lta.isEmpty());
		} catch (XmlRpcException xe) {
			fail("This should not throw an exception.");
		}

		try {
			ticket.getActions(-1);
			fail("This should throw an exception.");
		} catch (XmlRpcException xe) {
			assertEquals(404, xe.code);
		}

	}

	@Test
	public void testGet() {
		try {
			ticket.get(-1);
			fail("Getting a non exitsing ticket should raise an exception");
		} catch (XmlRpcException xe) {
			assertEquals(404, xe.code);
		}
		try {
			Ticket t = ticket.get(testTicketId);
			assertEquals(testTicketId, t.getId());
		} catch (XmlRpcException xe) {
			fail("This should raise an exception");
		}
	}

	@Test
	public void testCreate() {
		Integer ticketId = null;
		try {
			Map<String, Object> a = new HashMap<String, Object>();
			a.put("owner", "create.test");
			ticketId = ticket.create("Test Ticket Create",
					"Testing to create a ticket.", a);
		} catch (Throwable e) {
			fail("This should not raise an exception");
		}
		
		try {
			List<Integer> testIds = ticket.query("owner=create.test&summary=Test Ticket Create");
			assertEquals(ticketId, testIds.get(0));
		} catch (Throwable ignore)
		{
		}
		
		try {
			Map<String, Object> a = new HashMap<String, Object>();
			a.put("owner", "create.test");
			ticketId = ticket.create(null, null);
			fail("This should raise an exception");
		} catch (XmlRpcException xe) {
			assertEquals(0, xe.code);
		}
		
		try {
			List<Integer> testIds = ticket.query("owner=create.test");
			for (Integer id : testIds) {
				ticket.delete(id);
			}
		} catch (Throwable ignore)
		{
			
		}
		
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeLog() {
		try {
			List<List<Object>> log = ticket.changeLog(testTicketId, 0);
			assertTrue("wait", log.size() > 0);
		} catch (XmlRpcException xe) {
			fail("Getting a tickets change log should not throw an exception.");
		}
	}

	@Test
	public void testListAttachments() {
		try {
			List<List<Object>> a = ticket.listAttachments(433);
			assertTrue("list is not empty", a.size() > 0);
		} catch (XmlRpcException xe) {
			fail("listing a tickets Attachments should not throw an exception");
		}
	}

	@Test
	public void testGetAttachment() {
		try {
			ticket.getAttachment(testTicketId, "testfile.png");
			fail("getting a non existing attachment should throw an exception.");
		} catch (XmlRpcException e) {
			assertEquals(404, e.code);
		}
	}

	@Test
	public void testPutAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTicketFields() {
		List<TicketField> ltf = ticket.getTicketFields();
		assertTrue(!ltf.isEmpty());
	}

}
