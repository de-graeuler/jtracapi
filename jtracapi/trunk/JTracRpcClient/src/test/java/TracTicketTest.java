import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
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

	
	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http:/192.168.56.101/test/login/rpc"));
		trac.setBasicAuthentication("admin", "admin");
		ticket = trac.getTicketApi();
	}

	@Test
	public void testQuery() {
		List<Integer> tickets = ticket.query("status!=closed&max=8");
		assertTrue("someLibraryMethod should return 'true'", tickets.size() > 0);
	}

	@Test
	public void testGetRecentChanges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActions() {
		TicketActionList<TicketAction> lta = ticket.getActions(490);
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		Ticket t = ticket.get(433);
		assertTrue("wait", (Integer) t.getId() == 433);
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
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
		List<List<Object>> log = ticket.changeLog(433, 0);
		assertTrue("wait", log.size() > 0);
	}

	@Test
	public void testListAttachments() {
		List<List<Object>> a = ticket.listAttachments(433);
		assertTrue("list is not empty", a.size() > 0);
	}

	@Test
	public void testGetAttachment() {
		byte[] data = ticket.getAttachment(433, "duplicate_shiftname_boltcaselist_station.png");
		try {
			FileOutputStream fos = new FileOutputStream(new File("duplicate_shiftname_boltcaselist_station.png"));
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("data submitted", data.length > 0);
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
		for(TicketField tif : ltf)
		{
			System.out.println(tif.getName() + ": " + tif.getOptions());
		}
				
		fail("Not yet implemented");
	}

}
