package com.sample.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.sample.ejb.SingletonBean;
import com.sample.model.Property;

@ManagedBean(name="manager")
public class PropertyManager
{
	private String key;
	private String value;
//	private List<Property> cacheList;
	
	@EJB
	private SingletonBean ejb;

	public List<Property> getCacheList() {
		return ejb.getCache();
	}
	
	public void save()
	{
		ejb.put(key, value);
	}
	
	public void clear()
	{
		ejb.delete();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
