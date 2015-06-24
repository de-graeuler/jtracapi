package de.graeuler.jtracapi.model.field;

import java.util.Date;


public class TicketMilestone extends TicketDynamicDescriptionField {

	private Date due;
	
	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

}
