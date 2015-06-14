package de.graeuler.jtracapi.xmlrpc.search;

import java.util.List;

import de.graeuler.jtracapi.model.search.SearchFilterList;
import de.graeuler.jtracapi.model.search.SearchResultList;
import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracSearch extends TracInterface {

	/**
	 * Retrieve a list of search filters with each element in the form (name, description).
	 */
	public SearchFilterList getSearchFilters();

	/**
	 * Perform a search using the given filters. Defaults to all if not provided. Results are returned as a list of tuples in the form (href, title, date, author, excerpt).
	 */
	public SearchResultList performSearch(String query, List<String> filters);	
	public SearchResultList performSearch(String query);	

}
