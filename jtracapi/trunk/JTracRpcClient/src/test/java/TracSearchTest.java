import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.model.search.SearchFilterList;
import de.graeuler.jtracapi.model.search.SearchResultList;
import de.graeuler.jtracapi.xmlrpc.search.TracSearch;

public class TracSearchTest {

	private static TracApi trac = null;
	private static TracSearch search = null;

	@Before
	public void setUp() throws Exception {
		trac = new TracApi(new URL("http://192.168.56.101/test/login/rpc"));
		trac.setBasicAuthentication("admin", "admin");
		search = trac.getSearchApi();
	}

	@Test
	public void testGetSearchFilters() {
		SearchFilterList sf = search.getSearchFilters();
		assertTrue(sf.size()>0);
	}

	@Test
	public void testPerformSearchStringListOfString() {
		SearchResultList srl = search.performSearch("Start", Arrays.asList(new String[]{"wiki"}));
		assertTrue(srl.size() > 1);
		srl.clear();
		srl.addAll(search.performSearch("Start", Arrays.asList(new String[]{"non-existing-search-category"})));
		assertTrue(srl.size() == 0);
	}

	@Test
	public void testPerformSearchString() {
		SearchResultList srl = search.performSearch("Start");
		assertTrue(srl.size() > 1);
	}

}
