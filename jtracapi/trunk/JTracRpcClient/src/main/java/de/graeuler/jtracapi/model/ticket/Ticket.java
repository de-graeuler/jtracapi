package de.graeuler.jtracapi.model.ticket;

import java.util.Date;
import java.util.Map;


public class Ticket 
{

	private int id;
	private Map<String, Object> attributes;
	private Date timeCreated;
	private Date timeChanged;
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}


	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date date) {
		this.timeCreated = date;
	}

	
	public Date getTimeChanged() {
		return timeChanged;
	}

	public void setTimeChanged(Date date) {
		this.timeChanged = date;
	}

	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}


	public String getAttribute(String name){
		if(null != this.attributes)
			return (String)this.attributes.get(name);
		else
			return null;
	}
	
	public void setAttribute(String name, String value){
		this.attributes.put(name, value);
	}
	
	public String getChangeToken()
	{
		return this.getAttribute("_ts");
	}
	
}
