package de.graeuler.jtracapi.converter;

import java.util.Map;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.ticket.TicketField;
import de.graeuler.jtracapi.model.ticket.TicketFieldList;

public class TicketFieldListTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof TicketFieldList;
	}

	@Override
	public Object convert(Object pObject) {
		Object[] x = (Object[]) pObject;
		TicketFieldList<TicketField> ltf = new TicketFieldList<TicketField>();
		
		for (Object field : x) {
			@SuppressWarnings("unchecked")
			Map<String,Object> fieldMap = (Map<String,Object>) field;
			TicketField tf = new TicketField();
			tf.setName((String) fieldMap.get("name"));
			tf.setLabel((String) fieldMap.get("label"));
			tf.setIsOptional(Boolean.TRUE.equals((Boolean)fieldMap.get("optional")));
			tf.setType((String) fieldMap.get("type"));
			if ( null != fieldMap.get("options"))
			{
				Object[] lo = (Object[]) fieldMap.get("options");
				for (Object option : lo) {
					tf.addOption((String) option);
				}
			}
			ltf.add(tf);
		}
		return ltf;
	}

	@Override
	public Object backConvert(Object result) {
		assert false; // In a client environment this should never be reached.
		return null;
	}

}
