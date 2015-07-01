package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;

import de.graeuler.jtracapi.model.field.TicketVersionField;

public interface TracTicketVersion extends TracTicketAttributeField<TicketVersionField> {

	@Override
	public TicketVersionField get(String name) throws XmlRpcException;
	
}
