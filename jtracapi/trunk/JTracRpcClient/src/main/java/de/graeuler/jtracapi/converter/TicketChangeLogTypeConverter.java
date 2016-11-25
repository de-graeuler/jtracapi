package de.graeuler.jtracapi.converter;

import java.util.Date;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.ticket.TicketChangeLog;
import de.graeuler.jtracapi.model.ticket.TicketChangeLogEntry;

public class TicketChangeLogTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return  pObject instanceof TicketChangeLog;
	}

	@Override
	public Object convert(Object pObject) {
		Object[] lo = (Object[]) pObject;
		TicketChangeLog tcl = new TicketChangeLog();
		for (int i = 0; i < lo.length; i++) {
			Object[] ocl = (Object[]) lo[i];
			TicketChangeLogEntry tcle = new TicketChangeLogEntry();
			tcle.setTime((Date) ocl[0]);
			tcle.setAuthor((String) ocl[1]);
			tcle.setField((String) ocl[2]);
			tcle.setOldValue((String) ocl[3]);
			tcle.setOldValue((String) ocl[4]);
			tcle.setPermanent((Boolean) (1 == (Integer) ocl[5]));
			tcl.add(tcle);
		}
		return tcl;
	}

	@Override
	public Object backConvert(Object result) {
		// TODO Auto-generated method stub
		return null;
	}

}
