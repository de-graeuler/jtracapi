import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.ticket.TicketComponent;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketComponent;


public class TracTicketComponentTest {

	private static TracApi trac = null;
	private static TracTicketComponent component = null;

	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http://intern.synatec.de/projects/pda/login/xmlrpc"));
		trac.setBasicAuthentication("bernhard.graeuler", "lalelu");
		component = trac.getTicketComponentApi();
	}

	@Test
	public void testGetAll() {
		List<String> c = component.getAll();
		assertTrue("Component List should nt be empty", c.size() > 0);
	}

	@Test
	public void testGet() {
		TicketComponent m = component.get("PDA General");
		assertEquals("result name should be PDA Top n Report", "PDA General", m.getName());
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
