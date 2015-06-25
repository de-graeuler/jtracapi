package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;

public interface TracTicketBaseField<T> extends TracTicketField<T> {

	/**
	 * Create a new ticket component with the given value.
	 */
	abstract public int create(String name, T value) throws XmlRpcException;

	/**
	 * 	Update ticket component with the given value.
	 */
	public int update(String name, T value) throws XmlRpcException;
}
