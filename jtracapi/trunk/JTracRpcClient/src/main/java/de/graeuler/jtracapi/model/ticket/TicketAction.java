package de.graeuler.jtracapi.model.ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketAction {

	private String action;
	private String label;
	private String hint;
	private List<TicketActionInputField> inputFields = new ArrayList<TicketActionInputField>();
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public List<TicketActionInputField> getInputFields() {
		return inputFields;
	}

	public void addInputField(TicketActionInputField ticketInputField) {
		this.inputFields.add(ticketInputField);
	}


}
