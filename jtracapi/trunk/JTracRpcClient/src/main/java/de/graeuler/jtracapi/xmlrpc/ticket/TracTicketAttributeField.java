package de.graeuler.jtracapi.xmlrpc.ticket;

import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import de.graeuler.jtracapi.model.field.TicketAttributeField;

public interface TracTicketAttributeField<T> extends TracTicketField<TicketAttributeField> {

	/**
	 * Create a new ticket component with the given attributes.
	 */
	abstract public int create(String name, Map<String, Object> attributes) throws XmlRpcException;

	/**
	 * 	Update ticket component with the given attributes.
	 */
	public int update(String name, Map<String, Object> attributes) throws XmlRpcException;

}
