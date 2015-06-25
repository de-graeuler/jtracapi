package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;


public interface TracTicketPriority extends
		TracTicketBaseField<String> {

	@Override
	public String get(String name) throws XmlRpcException;
	
}
