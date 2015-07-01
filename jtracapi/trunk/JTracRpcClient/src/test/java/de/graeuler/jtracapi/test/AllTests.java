package de.graeuler.jtracapi.test;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.test.field.TracTicketComponentTest;
import de.graeuler.jtracapi.test.field.TracTicketMilestoneTest;
import de.graeuler.jtracapi.test.field.TracTicketPriorityTest;
import de.graeuler.jtracapi.test.field.TracTicketResolutionTest;
import de.graeuler.jtracapi.test.field.TracTicketSeverityTest;
import de.graeuler.jtracapi.test.field.TracTicketStatusTest;
import de.graeuler.jtracapi.test.field.TracTicketTypeTest;
import de.graeuler.jtracapi.test.field.TracTicketVersionTest;
import de.graeuler.jtracapi.test.trac.TracSearchTest;
import de.graeuler.jtracapi.test.trac.TracTicketTest;
import de.graeuler.jtracapi.test.trac.TracWikiTest;

@RunWith(Suite.class)
@SuiteClasses({ TracSearchTest.class, TracTicketTest.class,
		TracTicketComponentTest.class, TracTicketMilestoneTest.class,
		TracTicketPriorityTest.class, TracTicketResolutionTest.class,
		TracTicketSeverityTest.class, TracTicketStatusTest.class,
		TracTicketTypeTest.class, TracTicketVersionTest.class,
		TracWikiTest.class })
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
