import java.net.URL;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.graeuler.jtracapi.TracApi;

@RunWith(Suite.class)
@SuiteClasses({ TracSearchTest.class, TracTicketTest.class,
		TracTicketComponentTest.class, TracTicketMilestoneTest.class,
		TracTicketPriorityTest.class, TracTicketResolutionTest.class,
		TracTicketStatusTest.class, TracWikiTest.class })
public class AllTests {

	private static final String TRAC_RPC_URL = "http://192.168.56.101/test/login/rpc";
	private static final String TRAC_USERNAME = "admin";
	private static final String TRAC_PASSWORD = "admin";

	public static TracApi trac;
	private static boolean isSetUp = false;

	@BeforeClass
	public static void setUp() throws Exception {
		if (!isSetUp) {
			trac = new TracApi(new URL(TRAC_RPC_URL));
			trac.setBasicAuthentication(TRAC_USERNAME, TRAC_PASSWORD);
			isSetUp = true;
		}
	}
}
