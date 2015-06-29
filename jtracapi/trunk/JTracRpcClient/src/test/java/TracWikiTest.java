import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.xmlrpc.wiki.TracWiki;


public class TracWikiTest {

	private static TracWiki wiki = null;

	@Before
	public void setUp() throws Exception {
		AllTests.setUp();
		wiki = AllTests.trac.getWikiApi();
	}


	@Test
	public void testGetRecentChanges() {
		List<Map<String, Object>> x = wiki.getRecentChanges(new Date(System.currentTimeMillis()- 31*24*60*60*1000));
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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
