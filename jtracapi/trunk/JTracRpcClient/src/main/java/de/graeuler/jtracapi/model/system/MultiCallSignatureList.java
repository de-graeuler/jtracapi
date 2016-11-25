package de.graeuler.jtracapi.model.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiCallSignatureList extends ArrayList<Map<String, Object>> implements List<Map<String, Object>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632937769771176946L;

	private interface MethodCallEnum {
		public String getMethodName();
	}

	public enum TICKET implements MultiCallSignatureList.MethodCallEnum {
		CREATE ("ticket.create"),
		UPDATE ("ticket.update"),
		QUERY  ("ticket.query"),
		GET    ("ticket.get");
		
		private String methodName;

		TICKET(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public String getMethodName() {
			return this.methodName;
		}
	};
	
	public enum WIKI implements MultiCallSignatureList.MethodCallEnum {
		GETPAGE     ("wiki.getPage"),
		GETPAGEINFO ("wiki.getPageInfo"),
		GETPAGEHTML ("wiki.getPageHTML"),
		PUTPAGE     ("wiki.putPage"),
		WIKITOHTML  ("wiki.wikiToHtml")
		;

		private String methodName;

		WIKI(String methodName) {
			this.methodName = methodName;
		}
		
		@Override
		public String getMethodName() {
			return this.methodName;
		}
	}
	
	public enum MILESTONE implements MethodCallEnum {
		GET ("ticket.milestone.get"),
		UPDATE ("ticket.milestone.update"),
		CREATE ("ticket.milestone.create");

		private String methodName;
		
		private MILESTONE(String methodName) {
			this.methodName = methodName;
		}
		
		@Override
		public String getMethodName() {
			return this.methodName;
		}
		
	}

	@SuppressWarnings("serial")
	public void add(final MethodCallEnum method, final Object ... params ) {
		add(new HashMap<String, Object> () {
			{
				put("methodName", method.getMethodName());
				put("params", params);
			}
		});
	}

	
}
