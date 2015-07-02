package de.graeuler.jtracapi.model.system;

import java.util.HashMap;
import java.util.Map;

public class MethodSignatureBuilder {

	@SuppressWarnings("serial")
	public Map<String, Object> build(final String string, final Object ... params ) {
		return new HashMap<String, Object> () {
			{
				put("methodName", string);
				put("params", params);
			}
		};
	}

}
