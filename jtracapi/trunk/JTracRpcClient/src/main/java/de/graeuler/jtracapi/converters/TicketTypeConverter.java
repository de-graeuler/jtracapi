package de.graeuler.jtracapi.converters;

import java.util.Date;
import java.util.Map;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.ticket.Ticket;

public class TicketTypeConverter implements TypeConverter {
	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof Ticket;
	}

	@Override
	public Object convert(Object pObject) {
		Object[] p = (Object[]) pObject;
		Ticket t = new Ticket();
		t.setId((Integer)p[0]);
		t.setTimeCreated((Date)p[1]);
		t.setTimeChanged((Date)p[2]);
		@SuppressWarnings("unchecked")
		Map<String,Object> a = (Map<String,Object>)p[3];		
		t.setAttributes(a);
		return t;
	}

	@Override
	public Object backConvert(Object result) {
		Ticket t = (Ticket) result;
		Object[] x = new Object[]{
			t.getId(), t.getTimeCreated(), t.getTimeChanged(), t.getAttributes()
		};
		return x;
	}
}