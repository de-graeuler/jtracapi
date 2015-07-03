package de.graeuler.jtrac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.converter.TicketTypeConverter;
import de.graeuler.jtracapi.model.system.MethodSignatureBuilder;
import de.graeuler.jtracapi.model.ticket.Ticket;
import de.graeuler.jtracapi.xmlrpc.system.TracSystem;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicket;

public class App {

	public static void main(String[] args) throws MalformedURLException, XmlRpcException {

		Logger log = LoggerFactory.getLogger(App.class);

		TracApi tracApi = new TracApi
		(new URL(
				"http://192.168.56.101/pda/login/rpc"
//				"http://192.168.56.101/test/login/rpc"
				));
		tracApi.setBasicAuthentication("admin", "admin");
		
		TracTicket ticketApi = tracApi.getTicketApi();
		TracSystem systemApi = tracApi.getSystemApi();

		log.info("Querying...");
		List<Integer> ids;
		ids = ticketApi.query("status!=closed&max=40&order=changetime&desc=1");
		log.info(ids.toString());

		log.info("Fetching all...");
		List<Map<String, Object>> listOfMethodCalls = new ArrayList<Map<String, Object>>();
		MethodSignatureBuilder msb = new MethodSignatureBuilder();
		for (Integer id : ids)
			listOfMethodCalls.add(msb.build(MethodSignatureBuilder.TICKET.GET, id ));
		Object[] lo = systemApi.multicall(listOfMethodCalls);

		log.info("Converting...");
		List<Ticket> tl = new ArrayList<Ticket>();
		TicketTypeConverter tcr = new TicketTypeConverter();
		for (Object o : lo) {
			tl.add((Ticket) tcr.convert(((Object[]) o)[0]));
		}
		for (Ticket t : tl)
			log.info(String.format("%d: %s", t.getId(),
					t.getAttribute("summary")));

/*
		log.info("Fetching single tickets...");
		Ticket t;
		for (Integer id : ids) {
			log.info(id.toString());
			t = ticketApi.get(id);
			log.info(t.getAttribute("summary"));
		}
*/
		System.exit(0);

		System.out.println("list 8 not closed");
		ids = ticketApi.query("status!=closed&max=8");
		System.out.println(ids);

		System.out.println("recent of 14 days");
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -14);
		ids = ticketApi.getRecentChanges(cal.getTime());
		System.out.println(ids);
	}

}
