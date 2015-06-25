package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;

import de.graeuler.jtracapi.model.field.TicketMilestoneField;

public interface TracTicketMilestone extends TracTicketAttributeField<TicketMilestoneField> {

	@Override
	public TicketMilestoneField get(String name) throws XmlRpcException;
	
}
