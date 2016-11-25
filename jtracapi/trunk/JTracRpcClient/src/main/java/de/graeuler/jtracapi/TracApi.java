package de.graeuler.jtracapi;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import de.graeuler.jtracapi.xmlrpc.search.TracSearch;
import de.graeuler.jtracapi.xmlrpc.system.TracSystem;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicket;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketComponent;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketMilestone;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketPriority;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketResolution;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketSeverity;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketStatus;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketType;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketVersion;
import de.graeuler.jtracapi.xmlrpc.wiki.TracWiki;

/*
 * @author bernhard.graeuler, @date 10.06.15 10:10
 */
public class TracApi {
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

	public TracApi(URL serviceUrl) {
		config.setServerURL(serviceUrl);
	}

	public void setBasicAuthentication(String username, String password) {
		config.setBasicUserName(username);
		config.setBasicPassword(password);
	}

	public TracSystem getSystemApi() {
		TracSystem system = (TracSystem) TracRpcMethods.SYSTEM
				.buildXmlRpcAccessObject(config);
		return system;
	}

	public TracSearch getSearchApi() {
		TracSearch search = (TracSearch) TracRpcMethods.SEARCH
				.buildXmlRpcAccessObject(config);
		return search;
	}

	public TracTicket getTicketApi() {
		TracTicket ticket = (TracTicket) TracRpcMethods.TICKET
				.buildXmlRpcAccessObject(config);
		return ticket;
	}

	public TracTicketComponent getTicketComponentApi() {
		TracTicketComponent component = (TracTicketComponent) TracRpcMethods.TICKET_COMPONENT
				.buildXmlRpcAccessObject(config);
		return component;
	}

	public TracTicketMilestone getTicketMilestoneApi() {
		TracTicketMilestone milestone = (TracTicketMilestone) TracRpcMethods.TICKET_MILESTONE
				.buildXmlRpcAccessObject(config);
		return milestone;
	}

	public TracTicketPriority getTicketPriorityApi() {
		TracTicketPriority priority = (TracTicketPriority) TracRpcMethods.TICKET_PRIORITY
				.buildXmlRpcAccessObject(config);
		return priority;
	}

	public TracTicketResolution getTicketResolutionApi() {
		TracTicketResolution resolution = (TracTicketResolution) TracRpcMethods.TICKET_RESOLUTION
				.buildXmlRpcAccessObject(config);
		return resolution;
	}

	public TracTicketSeverity getTicketSeverityApi() {
		TracTicketSeverity severity = (TracTicketSeverity) TracRpcMethods.TICKET_SEVERITY
				.buildXmlRpcAccessObject(config);
		return severity;
	}

	public TracTicketStatus getTicketStatusApi() {
		TracTicketStatus status = (TracTicketStatus) TracRpcMethods.TICKET_STATUS
				.buildXmlRpcAccessObject(config);
		return status;
	}

	public TracTicketType getTicketTypeApi() {
		TracTicketType type = (TracTicketType) TracRpcMethods.TICKET_TYPE
				.buildXmlRpcAccessObject(config);
		return type;
	}

	public TracTicketVersion getTicketVersionApi() {
		TracTicketVersion version = (TracTicketVersion) TracRpcMethods.TICKET_VERSION
				.buildXmlRpcAccessObject(config);
		return version;
	}

	public TracWiki getWikiApi() {
		TracWiki wiki = (TracWiki) TracRpcMethods.WIKI
				.buildXmlRpcAccessObject(config);
		return wiki;
	}

}
