package de.graeuler.jtracapi;

import java.util.Date;
import java.util.Map;

import de.graeuler.jtracapi.converter.TicketDynamicFieldTypeConverter;
import de.graeuler.jtracapi.model.field.TicketVersionField;

public class TicketVersionFieldTypeConverter extends
		TicketDynamicFieldTypeConverter<TicketVersionField> {

	@Override
	public TicketVersionField newDynamicTicketField() {
		return new TicketVersionField();
	}

	@Override
	public TicketVersionField castToDynamicField(Object object) {
		return (TicketVersionField) object;
	}

	@Override
	public void setDynamicFieldAttributes(TicketVersionField field,
			Map<String, Object> attributeMap) {
		field.setName((String) attributeMap.get("name"));
		field.setDescription((String) attributeMap.get("description"));

		if (attributeMap.get("time") instanceof Date) {
			field.setTime((Date) attributeMap.get("time"));
		}
	}

	@Override
	public void setStructAttributes(Map<String, Object> attributeMap,
			TicketVersionField field) {
		attributeMap.put("name", field.getName());
		attributeMap.put("description", field.getDescription());
		attributeMap.put("time", field.getTime());
	}


}
