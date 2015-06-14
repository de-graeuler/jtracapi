package de.graeuler.jtracapi.converters;

import java.util.Date;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.search.SearchResult;
import de.graeuler.jtracapi.model.search.SearchResultList;

public class SearchResultListTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof SearchResultList;
	}

	@Override
	public Object convert(Object pObject) {
		SearchResultList lsr = new SearchResultList();
		Object[] o = (Object[]) pObject;
		for(Object os : o)
		{
			Object[] s = (Object[]) os;
			SearchResult sr  = new SearchResult();
			sr.setHref((String) s[0]);
			sr.setTitle((String) s[1]);
			sr.setDate((Date) s[2]);
			sr.setAuthor((String) s[3]);
			sr.setExcerpt((String) s[4]);
			lsr.add(sr);
		}
		return lsr;
	}

	@Override
	public Object backConvert(Object result) {
		// TODO Auto-generated method stub
		return null;
	}

}
