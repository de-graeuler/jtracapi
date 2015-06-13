package de.graeuler.jtracapi.model.ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketActionInputField {

	private String name;
	private String value;
	private List<String> options = new ArrayList<String>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getOptions() {
		return options;
	}
	public void addOption(String option){
		this.options.add(option);
	}
	

}
