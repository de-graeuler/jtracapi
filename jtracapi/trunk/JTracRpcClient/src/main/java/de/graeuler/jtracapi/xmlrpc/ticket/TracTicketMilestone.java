package de.graeuler.jtracapi.xmlrpc.ticket;

import de.graeuler.jtracapi.model.field.TicketMilestone;

public interface TracTicketMilestone extends TracTicketDynamicField<TicketMilestone> {

	@Override
	public TicketMilestone get(String name);
	
}
