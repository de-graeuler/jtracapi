package de.graeuler.jtracapi.model.system;

import java.util.HashMap;
import java.util.Map;

public class MethodSignatureBuilder {

	private interface MethodCallEnum {
	}

	public enum TICKET implements MethodSignatureBuilder.MethodCallEnum {
		GET ("ticket.get");
		
		private String methodName;

		TICKET(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public String toString() {
			return this.methodName;
		}
	};

//	public static Ticket TICKET;
	
	@SuppressWarnings("serial")
	public Map<String, Object> build(final Enum<? extends MethodSignatureBuilder.MethodCallEnum> method, final Object ... params ) {
		return new HashMap<String, Object> () {
			{
				put("methodName", method.toString());
				put("params", params);
			}
		};
	}

}
