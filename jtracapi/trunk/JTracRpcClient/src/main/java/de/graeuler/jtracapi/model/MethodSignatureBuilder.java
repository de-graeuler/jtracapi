package de.graeuler.jtracapi.model;

import java.util.HashMap;
import java.util.Map;

public class MethodSignatureBuilder {

	@SuppressWarnings("serial")
	public Map<String, Object> build(final String string, final Object[] objects) {
		return new HashMap<String, Object> () {
			{
				put("methodName", string);
				put("params", objects);
			}
		};
	}

}
