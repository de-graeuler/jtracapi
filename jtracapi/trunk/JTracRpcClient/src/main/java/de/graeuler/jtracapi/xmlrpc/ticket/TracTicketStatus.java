package de.graeuler.jtracapi.xmlrpc.ticket;

import org.apache.xmlrpc.XmlRpcException;



public interface TracTicketStatus extends
		TracTicketBaseField<String> {

	@Override
	@Deprecated
	public String get(String name) throws XmlRpcException;
	
	@Override
	@Deprecated
	public int update(String name, String value) throws XmlRpcException;
	
	@Override
	@Deprecated
	public int create(String name, String value) throws XmlRpcException; 
	
	@Override
	@Deprecated
	public int delete(String name) throws XmlRpcException;
}
