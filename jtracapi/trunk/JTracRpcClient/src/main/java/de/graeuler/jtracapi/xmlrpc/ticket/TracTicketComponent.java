package de.graeuler.jtracapi.xmlrpc.ticket;

import de.graeuler.jtracapi.model.field.TicketComponentField;

public interface TracTicketComponent extends TracTicketDynamicField<TicketComponentField>{

	@Override
	public TicketComponentField get(String name);
	
}
