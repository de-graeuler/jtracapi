package de.graeuler.jtracapi.test.trac;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.graeuler.jtracapi.model.wiki.WikiPageInfo;
import de.graeuler.jtracapi.test.AllTests;
import de.graeuler.jtracapi.xmlrpc.wiki.TracWiki;


public class TracWikiTest {

	private static final String WIKI_TEST_PAGE_NAME = "JTracRpcClientTest";
	private static TracWiki wiki = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllTests.setUp();
		wiki = AllTests.trac.getWikiApi();
		deleteWikiTestPage();
		createWikiTestPage();
	}


	private static void createWikiTestPage() {
		wiki.putPage(WIKI_TEST_PAGE_NAME, "= Test =", new HashMap<String, Object>());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		deleteWikiTestPage();
	}
	

	private static void deleteWikiTestPage() {
		wiki.deletePage(WIKI_TEST_PAGE_NAME);
	}


	@Test
	public void testGetRecentChanges() {
		DateTime changesSince = DateTime.now(DateTimeZone.UTC)
				.minusMinutes(2);
		List<Map<String, Object>> x = wiki.getRecentChanges(changesSince
				.toLocalDateTime().toDate());
	}

	@Test
	public void testGetRPCVersionSupported() {
		int x = wiki.getRPCVersionSupported();
		fail("Not yet implemented");
	}

	@Test
	public void testGetPage() {
		String x = wiki.getPage("WikiStart");
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageVersion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageHTML() {
		String html = wiki.getPageHTML("WikiStart");
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageHTMLVersion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllPages() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageInfo() {
		WikiPageInfo wpi = wiki.getPageInfo(WIKI_TEST_PAGE_NAME);
		assertEquals("admin", wpi.getAuthor());
	}

	@Test
	public void testGetPageInfoVersion() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAttachments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutAttachmentEx() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePage() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testWikiToHtml() {
		fail("Not yet implemented");
	}

}
