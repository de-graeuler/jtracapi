package de.graeuler.jtracapi.xmlrpc.ticket;

import de.graeuler.jtracapi.model.field.TicketPriority;

public interface TracTicketPriority extends
		TracTicketDynamicField<TicketPriority> {

	@Override
	public TicketPriority get(String name);
	
}
