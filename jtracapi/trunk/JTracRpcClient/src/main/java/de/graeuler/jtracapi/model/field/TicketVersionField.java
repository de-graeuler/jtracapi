package de.graeuler.jtracapi.model.field;

import java.util.Date;

public class TicketVersionField extends TicketDynamicDescriptionField {

	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date date) {
		this.time = date;
	}
	
}
