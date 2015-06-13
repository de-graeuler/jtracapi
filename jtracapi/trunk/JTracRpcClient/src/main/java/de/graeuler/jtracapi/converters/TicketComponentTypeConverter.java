package de.graeuler.jtracapi.converters;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.ticket.TicketComponent;

public class TicketComponentTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof TicketComponent;
	}

	@Override
	public Object convert(Object pObject) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String, Object>) pObject;
		TicketComponent c = new TicketComponent();
		c.setName((String)map.get("name"));
		c.setOwner((String)map.get("owner"));
		c.setDescription((String)map.get("description"));
		return c;
	}

	@Override
	public Object backConvert(Object result) {
		TicketComponent c = (TicketComponent) result;
		HashMap<String,Object> x = new HashMap<String,Object>();
		x.put("name", c.getName());
		x.put("owner", c.getOwner());
		x.put("description", c.getDescription());
		return x;
	}

}
