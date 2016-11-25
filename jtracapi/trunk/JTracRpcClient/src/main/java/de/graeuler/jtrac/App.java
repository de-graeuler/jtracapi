package de.graeuler.jtrac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.graeuler.jtracapi.TracApi;
import de.graeuler.jtracapi.converter.TicketMilestoneFieldTypeConverter;
import de.graeuler.jtracapi.converter.TicketTypeConverter;
import de.graeuler.jtracapi.model.field.TicketMilestoneField;
import de.graeuler.jtracapi.model.system.MultiCallSignatureList;
import de.graeuler.jtracapi.model.ticket.Ticket;
import de.graeuler.jtracapi.xmlrpc.system.TracSystem;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicket;
import de.graeuler.jtracapi.xmlrpc.ticket.TracTicketMilestone;

public class App {

	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws MalformedURLException,
			XmlRpcException {

		TracApi tracApi = new TracApi(new URL(
				"http://192.168.56.101/pda/login/rpc"
		// "http://192.168.56.101/test/login/rpc"
				));
		tracApi.setBasicAuthentication("admin", "admin");

		TracTicketMilestone milestoneApi = tracApi.getTicketMilestoneApi();
		TracTicket ticketApi = tracApi.getTicketApi();
		TracSystem systemApi = tracApi.getSystemApi();

		MultiCallSignatureList msl = new MultiCallSignatureList();

		List<String> milestoneList = milestoneApi.getAll();
		for (String milestone : milestoneList) {
			msl.add(MultiCallSignatureList.MILESTONE.GET, milestone);
		}
		Object[] milestoneArray = systemApi.multicall(msl);
		TicketMilestoneFieldTypeConverter mftConverter = new TicketMilestoneFieldTypeConverter();
		List<TicketMilestoneField> milestoneFieldList = new ArrayList<TicketMilestoneField>();
		for (Object milestone : milestoneArray) {
			milestoneFieldList.add((TicketMilestoneField) mftConverter
					.convert(((Object[]) milestone)[0]));
		}

		Collections.sort(milestoneFieldList,
				new Comparator<TicketMilestoneField>() {

					@Override
					public int compare(TicketMilestoneField o1,
							TicketMilestoneField o2) {
						Date d1 = o1.getDue();
						Date d2 = o2.getDue();
						if (d1 == null && d2 != null)
							return 1;
						if (d1 != null && d2 == null)
							return -1;
						if (d1 == null && d2 == null)
							return 0;
						return d1.compareTo(d2);
					}
				});

		log.info("Querying...");

		List<Integer> ids;
		Iterator<TicketMilestoneField> mi = milestoneFieldList.iterator();
		while (mi.hasNext()) {
			TicketMilestoneField mf = (TicketMilestoneField) mi.next();
			if (null != mf.getCompleted())
				continue;
			log.info(mf.getName());

			ids = ticketApi.query("status!=closed&milestone=" + mf.getName()
					+ "&order=created");
			msl.clear();
			log.info("Fetching all...");
			for (Integer id : ids)
				msl.add(MultiCallSignatureList.TICKET.GET, id);
			Object[] lo = systemApi.multicall(msl);

			log.info("Converting...");
			List<Ticket> tl = new ArrayList<Ticket>();
			TicketTypeConverter tcr = new TicketTypeConverter();
			for (Object o : lo) {
				tl.add((Ticket) tcr.convert(((Object[]) o)[0]));
			}
			for (Ticket t : tl)
				log.info(String.format("	%d: %s", t.getId(),
						t.getAttribute("summary")));

		}

	}

}
