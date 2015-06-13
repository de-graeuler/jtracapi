package de.graeuler.jtracapi.model.ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketField {

	private String name;
	private String label;
	private String type;
	private String value;
	private Boolean isOptional;
	private List<String> options = new ArrayList<String>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Boolean isOptional() {
		return isOptional;
	}
	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
	}
	
	public List<String> getOptions() {
		return options;
	}
	public void addOption(String option) {
		this.options.add(option);
	}
	
}
