package de.graeuler.jtracapi.converter;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.field.TicketAttributeField;

public abstract class TicketDynamicFieldTypeConverter<T extends TicketAttributeField>
		implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof TicketAttributeField;
	}

	/**
	 * Creates a new object that extends TicketDynamicField. This method creates
	 * the object returned by this.convert
	 * 
	 * @return T
	 */
	public abstract T newDynamicTicketField();

	/**
	 * Casts an Object instance to the generic TicketDynamicField
	 * 
	 * @param object
	 * @return object casted to T
	 */
	public abstract T castToDynamicField(Object object);

	/**
	 * Sets all fields of T field using the values stored in attributeMap. This
	 * method is called by the convert method.
	 * 
	 * @param field
	 *            call the setters for field using the attribute Map
	 * @param attributeMap
	 *            holds the values to initialize the field
	 */
	public abstract void setDynamicFieldAttributes(T field,
			Map<String, Object> attributeMap);

	/**
	 * Puts the field values into the attribute Map for the backConvert Method
	 * 
	 * @param attributeMap
	 * @param field
	 */
	public abstract void setStructAttributes(Map<String, Object> attributeMap,
			T field);

	@Override
	public Object convert(Object pObject) {
		T f = newDynamicTicketField();
		@SuppressWarnings("unchecked")
		Map<String, Object> m = (Map<String, Object>) pObject;
		setDynamicFieldAttributes(f, m);
		return f;
	}

	@Override
	public Object backConvert(Object result) {
		T c = castToDynamicField(result);
		Map<String, Object> m = new HashMap<String,Object>();
		setStructAttributes(m, c);
		return m;
	}

}
