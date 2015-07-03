package de.graeuler.jtracapi.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.wiki.WikiPageInfo;

public class WikiPageInfoConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof WikiPageInfo;
	}

	@Override
	public Object convert(Object pObject) {
		WikiPageInfo wpi = new WikiPageInfo();
		@SuppressWarnings("unchecked")
		Map<String, Object> m = (Map<String, Object>) pObject;
		wpi.setAuthor((String) m.get("author"));
		wpi.setName((String) m.get("name"));
		wpi.setComment((String) m.get("comment"));
		wpi.setLastModified((Date) m.get("lastModified"));
		wpi.setVersion((Integer) m.get("version"));
		return wpi;
	}

	@Override
	public Object backConvert(Object result) {
		WikiPageInfo wpi = (WikiPageInfo) result;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("author", wpi.getAuthor());
		map.put("name", wpi.getName());
		map.put("comment",  wpi.getComment());
		map.put("lastModified", wpi.getLastModified());
		map.put("version", wpi.getVersion());
		return map;
	}

}
