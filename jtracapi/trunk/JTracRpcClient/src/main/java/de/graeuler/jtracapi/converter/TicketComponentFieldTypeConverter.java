package de.graeuler.jtracapi.converter;

import java.util.Map;

import de.graeuler.jtracapi.model.field.TicketComponentField;


public class TicketComponentFieldTypeConverter extends
		TicketDynamicFieldTypeConverter<TicketComponentField> {

	@Override
	public TicketComponentField newDynamicTicketField() {
		return new TicketComponentField();
	}

	@Override
	public void setDynamicFieldAttributes(TicketComponentField field,
			Map<String, Object> attributeMap) {
		field.setName((String)attributeMap.get("name"));
		field.setOwner((String)attributeMap.get("owner"));
		field.setDescription((String)attributeMap.get("description"));		
	}

	@Override
	public TicketComponentField castToDynamicField(Object object) {
		return (TicketComponentField) object;
	}

	@Override
	public void setStructAttributes(Map<String, Object> attributeMap,
			TicketComponentField field) {
		attributeMap.put("name", field.getName());
		attributeMap.put("description", field.getDescription());
		attributeMap.put("owner", field.getOwner());
	}

}
