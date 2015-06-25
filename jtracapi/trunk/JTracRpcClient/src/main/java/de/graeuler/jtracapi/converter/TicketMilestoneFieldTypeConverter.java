package de.graeuler.jtracapi.converter;

import java.util.Date;
import java.util.Map;

import de.graeuler.jtracapi.model.field.TicketMilestoneField;

public class TicketMilestoneFieldTypeConverter extends
		TicketDynamicFieldTypeConverter<TicketMilestoneField> {

	@Override
	public TicketMilestoneField newDynamicTicketField() {
		return new TicketMilestoneField();
	}

	@Override
	public void setDynamicFieldAttributes(TicketMilestoneField field,
			Map<String, Object> attributeMap) {
		field.setName((String) attributeMap.get("name"));
		field.setDescription((String) attributeMap.get("description"));

		if (attributeMap.get("due") instanceof Date) {
			field.setDue((Date) attributeMap.get("due"));
		}
		if (attributeMap.get("completed") instanceof Date) {
			field.setCompleted((Date) attributeMap.get("completed"));
		}
	}

	@Override
	public TicketMilestoneField castToDynamicField(Object object) {
		return (TicketMilestoneField) object;
	}

	@Override
	public void setStructAttributes(Map<String, Object> attributeMap,
			TicketMilestoneField field) {
		attributeMap.put("name", field.getName());
		attributeMap.put("description", field.getDescription());
		attributeMap.put("due", field.getDescription());
	}

}
