package de.graeuler.jtracapi.converters;

import java.util.Date;
import java.util.Map;

import de.graeuler.jtracapi.model.field.TicketMilestone;

public class TicketMilestoneFieldTypeConverter extends
		TicketDynamicFieldTypeConverter<TicketMilestone> {

	@Override
	public TicketMilestone newDynamicTicketField() {
		return new TicketMilestone();
	}

	@Override
	public void setDynamicFieldAttributes(TicketMilestone field,
			Map<String, Object> attributeMap) {
		field.setName((String)attributeMap.get("name"));
		field.setDescription((String) attributeMap.get("description"));
		field.setDue((Date) attributeMap.get("due"));
	}

	@Override
	public TicketMilestone castToDynamicField(Object object) {
		return (TicketMilestone) object;
	}

	@Override
	public void setStructAttributes(Map<String, Object> attributeMap,
			TicketMilestone field) {
		attributeMap.put("name", field.getName());
		attributeMap.put("description", field.getDescription());
		attributeMap.put("due", field.getDescription());
	}


}
