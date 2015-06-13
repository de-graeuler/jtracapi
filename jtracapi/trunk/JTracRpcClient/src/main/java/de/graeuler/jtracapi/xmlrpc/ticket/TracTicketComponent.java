package de.graeuler.jtracapi.xmlrpc.ticket;

import java.util.List;
import java.util.Map;

import de.graeuler.jtracapi.model.ticket.TicketComponent;
import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracTicketComponent extends TracInterface{

	/**
	 * Get a list of all ticket component names.
	 */
	public List<String> getAll();
	/**
	 * Get a ticket component.
	 */
	public TicketComponent get(String name);

	/**
	 * Delete a ticket component
	 */
	public int delete(String name);

	/**
	 * Create a new ticket component with the given attributes.
	 */
	public int create(String name, Map<Object, Object> attributes);

	/**
	 * 	Update ticket component with the given attributes.
	 */
	public int update(String name, Map<Object, Object> attributes);
}
