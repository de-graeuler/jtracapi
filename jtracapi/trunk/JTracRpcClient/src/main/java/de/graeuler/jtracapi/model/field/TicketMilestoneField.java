package de.graeuler.jtracapi.model.field;

import java.util.Date;


public class TicketMilestoneField extends TicketDynamicDescriptionField {

	private Date due = null;
	private Date completed = null;
	
	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Date getCompleted() {
		return completed;
	}
	
	public void setCompleted(Date completed) {
		this.completed = completed;
	}

}
