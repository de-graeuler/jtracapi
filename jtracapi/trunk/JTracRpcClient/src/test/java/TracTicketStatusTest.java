import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketStatus;


public class TracTicketStatusTest {

	private static TracTicketStatus status;

	@BeforeClass
	public static void setUp() throws Exception {
		AllTests.setUp();
		status = AllTests.trac.getTicketStatusApi();
	}

	@Test
	public void testGetAll() {
		List<String> lts = status.getAll();
		assertTrue(lts.contains("new"));
	}

}
