import static org.junit.Assert.fail;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.search.SearchFilterList;
import de.graeuler.jtracapi.model.search.SearchResultList;
import de.graeuler.jtracapi.xmlrpc.search.SearchFilter;
import de.graeuler.jtracapi.xmlrpc.search.TracSearch;


public class TracSearchTest {

	private static TracApi trac = null;
	private static TracSearch search = null;

	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http://intern.synatec.de/projects/pda/login/xmlrpc"));
		trac.setBasicAuthentication("bernhard.graeuler", "lalelu");
		search = trac.getSearchApi();
	}

	@Test
	public void testGetSearchFilters() {
		SearchFilterList sf = search.getSearchFilters();
		fail("Not yet implemented");
	}

	@Test
	public void testPerformSearchStringListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPerformSearchString() {
		SearchResultList srl = search.performSearch("Dumm");
		fail("Not yet implemented");
	}

}
