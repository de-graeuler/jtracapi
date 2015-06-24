import static org.junit.Assert.fail;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.field.TicketMilestone;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketMilestone;


public class TracTicketMilestoneTest {
	private static TracApi trac = null;
	private static TracTicketMilestone milestone = null;

	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http://intern.synatec.de/projects/pda/login/xmlrpc"));
		trac.setBasicAuthentication("bernhard.graeuler", "lalelu");
		milestone = trac.getTicketMilestoneApi();
	}

	@Test
	public void testGetString() {
		TicketMilestone tm = milestone.get("PA v. 2.3.0");
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		List<String> lm = milestone.getAll();
		fail("Not yet implemented");
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
