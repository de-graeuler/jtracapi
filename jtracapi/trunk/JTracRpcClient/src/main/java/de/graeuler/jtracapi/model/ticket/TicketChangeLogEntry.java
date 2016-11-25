package de.graeuler.jtracapi.model.ticket;

import java.util.Date;

public class TicketChangeLogEntry {

	private Boolean permanent;
	private Date time;
	private String author;
	private String field;
	private String oldValue;

	public void setTime(Date time) {
		this.time = time;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public void setPermanent(Boolean permanent) {
		this.permanent = permanent;
	}

	public Boolean getPermanent() {
		return permanent;
	}

	public Date getTime() {
		return time;
	}

	public String getAuthor() {
		return author;
	}

	public String getField() {
		return field;
	}

	public String getOldValue() {
		return oldValue;
	}


}
