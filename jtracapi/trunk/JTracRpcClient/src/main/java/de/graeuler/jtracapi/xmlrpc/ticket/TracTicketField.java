package de.graeuler.jtracapi.xmlrpc.ticket;

import java.util.List;

import org.apache.xmlrpc.XmlRpcException;

import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracTicketField<T> extends TracInterface {
	
	/**
	 * Get a list of all ticket component names.
	 */
	public List<String> getAll();
	/**
	 * Get a ticket component.
	 */
	public T get(String name) throws XmlRpcException;

	/**
	 * Delete a ticket component
	 */
	public int delete(String name) throws XmlRpcException;

}
