package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;

import de.graeuler.jtracapi.model.field.TicketComponentField;

public interface TracTicketComponent extends TracTicketAttributeField<TicketComponentField>{

	@Override
	public TicketComponentField get(String name) throws XmlRpcException;
	
}
