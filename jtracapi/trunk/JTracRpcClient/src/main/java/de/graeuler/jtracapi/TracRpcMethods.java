package de.graeuler.jtracapi;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.apache.xmlrpc.common.TypeConverter;
import org.apache.xmlrpc.common.TypeConverterFactoryImpl;

import de.graeuler.jtracapi.converter.SearchFilterListTypeConverter;
import de.graeuler.jtracapi.converter.SearchResultListTypeConverter;
import de.graeuler.jtracapi.converter.TicketActionListTypeConverter;
import de.graeuler.jtracapi.converter.TicketComponentFieldTypeConverter;
import de.graeuler.jtracapi.converter.TicketFieldListTypeConverter;
import de.graeuler.jtracapi.converter.TicketMilestoneFieldTypeConverter;
import de.graeuler.jtracapi.converter.TicketTypeConverter;
import de.graeuler.jtracapi.converter.TicketVersionFieldTypeConverter;
import de.graeuler.jtracapi.model.field.TicketComponentField;
import de.graeuler.jtracapi.model.field.TicketMilestoneField;
import de.graeuler.jtracapi.model.field.TicketVersionField;
import de.graeuler.jtracapi.model.search.SearchFilterList;
import de.graeuler.jtracapi.model.search.SearchResultList;
import de.graeuler.jtracapi.model.ticket.Ticket;
import de.graeuler.jtracapi.model.ticket.TicketActionList;
import de.graeuler.jtracapi.model.ticket.TicketFieldList;
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

public enum TracRpcMethods {

	SYSTEM ("system", TracSystem.class),
	SEARCH ("search", TracSearch.class),
	TICKET ("ticket", TracTicket.class),
	WIKI   ("wiki"  , TracWiki.class),
	TICKET_COMPONENT  ("ticket.component" , TracTicketComponent.class),
	TICKET_MILESTONE  ("ticket.milestone" , TracTicketMilestone.class),
	TICKET_PRIORITY   ("ticket.priority"  , TracTicketPriority.class),
	TICKET_RESOLUTION ("ticket.resolution", TracTicketResolution.class),
	TICKET_SEVERITY   ("ticket.severity"  , TracTicketSeverity.class),
	TICKET_STATUS     ("ticket.status"    , TracTicketStatus.class),
	TICKET_TYPE       ("ticket.type"      , TracTicketType.class),
	TICKET_VERSION    ("ticket.version"   , TracTicketVersion.class)
	;
	
	String pRemoteName;
	Class<?> pClass;

	TracRpcMethods(String id, Class<?> pClass) {
		this.pRemoteName = id;
		this.pClass = pClass;
	}

	protected Object buildXmlRpcAccessObject(XmlRpcClientConfig config) {
		ClientFactory factory = getClientFactory(config);
		Object object = factory.newInstance(Thread.currentThread()
				.getContextClassLoader(), pClass, pRemoteName);
		return object;
	}

	protected ClientFactory getClientFactory(XmlRpcClientConfig config) {
		XmlRpcClient client = new XmlRpcClient();

		// This is how you would use Apache Commons http client (v3.1) for
		// digest authentication
		// sadly, with tracd and Digest Authentication this causes an
		// org.apache.commons.httpclient.ProtocolException: The server
		// 192.168.1.90 failed to respond with a valid HTTP response
		// As Digest Authentication works with other HTTP Clients (cURL and
		// Firefox tested), I think it is a bug in the commons httpclient.
		//
		// XmlRpcCommonsTransportFactory tf = new
		// XmlRpcCommonsTransportFactory(client);
		// HttpClient httpClient = new HttpClient();
		// AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, 80,
		// "java-debian.de");
		// httpClient.getState().setCredentials(authScope, new
		// UsernamePasswordCredentials("admin", "admin"));
		// tf.setHttpClient(httpClient);
		// client.setTransportFactory(tf);

		client.setConfig(config);
		ClientFactory factory = new ClientFactory(client,
				new TypeConverterFactoryImpl() {

					@Override
					public TypeConverter getTypeConverter(
							@SuppressWarnings("rawtypes") Class pClass) {

						if (SearchFilterList.class.equals(pClass))
							return new SearchFilterListTypeConverter();

						if (SearchResultList.class.equals(pClass))
							return new SearchResultListTypeConverter();

						if (Ticket.class.equals(pClass))
							return new TicketTypeConverter();

						if (TicketActionList.class.equals(pClass))
							return new TicketActionListTypeConverter();

						if (TicketFieldList.class.equals(pClass))
							return new TicketFieldListTypeConverter();

						if (TicketComponentField.class.equals(pClass))
							return new TicketComponentFieldTypeConverter();

						if (TicketMilestoneField.class.equals(pClass))
							return new TicketMilestoneFieldTypeConverter();

						if (TicketVersionField.class.equals(pClass))
							return new TicketVersionFieldTypeConverter();

						return super.getTypeConverter(pClass);

					}

				});
		return factory;
	}
	
}
