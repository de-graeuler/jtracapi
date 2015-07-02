package de.graeuler.jtracapi.converter;

import org.apache.xmlrpc.common.TypeConverter;

import de.graeuler.jtracapi.model.search.SearchFilter;
import de.graeuler.jtracapi.model.search.SearchFilterList;

public class SearchFilterListTypeConverter implements TypeConverter {

	@Override
	public boolean isConvertable(Object pObject) {
		return pObject instanceof SearchFilterList;
	}

	@Override
	public Object convert(Object pObject) {
		SearchFilterList lsf = new SearchFilterList();
		Object[] olsf = (Object[]) pObject;
		for(Object osf : olsf)
		{
			Object[] o = (Object[]) osf;
			SearchFilter sf = new SearchFilter();
			sf.setName((String) o[0]);
			sf.setDescription((String) o[1]);
			lsf.add(sf);
		}
		return lsf;
	}

	@Override
	public Object backConvert(Object result) {
		// TODO Auto-generated method stub
		return null;
	}

}
