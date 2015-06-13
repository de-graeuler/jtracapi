package de.graeuler.jtracapi.converters;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.ticket.TicketAction;
import de.graeuler.jtracapi.model.ticket.TicketActionInputField;
import de.graeuler.jtracapi.model.ticket.TicketActionList;

public class TicketActionListTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof TicketActionList;
	}

	@Override
	public Object convert(Object pObject) {
		Object[] arrActions = (Object[]) pObject;
		TicketActionList<TicketAction> r = buildTicketActionList(arrActions);
		return r;
	}

	protected TicketActionList<TicketAction> buildTicketActionList(
			Object[] arrActions) {
		TicketActionList<TicketAction> r = new TicketActionList<TicketAction>();
		for (int ia = 0; ia < arrActions.length; ia++) {
			Object[] action = (Object[]) arrActions[ia];
			TicketAction ticketAction = buildTicketAction(action);
			Object[] arrInputs = (Object[]) action[3];
			for (int ii = 0; ii < arrInputs.length; ii++) {
				Object[] arrCurrentInput = (Object[]) arrInputs[ii];
				TicketActionInputField tif = buildTicketInputField(arrCurrentInput);
				ticketAction.addInputField(tif);
			}
			r.add(ticketAction);
		}
		return r;
	}

	protected TicketAction buildTicketAction(Object[] action) {
		TicketAction ticketAction = new TicketAction();
		ticketAction.setAction((String) action[0]);
		ticketAction.setLabel((String) action[1]);
		ticketAction.setHint((String) action[2]);
		return ticketAction;
	}

	protected TicketActionInputField buildTicketInputField(Object[] arrCurrentInput) {
		TicketActionInputField taif = new TicketActionInputField();
		taif.setName((String) arrCurrentInput[0]);
		taif.setValue((String) arrCurrentInput[1]);
		Object[] arrInputOptions = (Object[]) arrCurrentInput[2];
		for(Object option : arrInputOptions)
		{
			taif.addOption((String) option);
		}
		return taif;
	}

	@Override
	public Object backConvert(Object result) {
		assert false; // In a client scenario, this should never be reached. 
		return null;
	}

}
