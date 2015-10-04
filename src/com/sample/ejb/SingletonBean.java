package com.sample.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sample.model.Property;

@Singleton
@Startup
public class SingletonBean
{
	@PersistenceContext(unitName="persistenceUnit")
	private EntityManager em;
	private List<Property> cache;
	
	@PostConstruct
	public void initCache()
	{
		this.cache = this.queyCache();
		if(this.cache == null)
		{
			this.cache= new ArrayList<Property>();
		}
	}
	
	public void delete()
	{
		this.cache.clear();
	}
	
	public void put(String key, String value)
	{
		Property p = new Property();
		p.setKey(key);
		p.setValue(value);
		
		this.em.persist(p);
		
		this.cache.add(p);
	}
	
	public List<Property> queyCache()
	{
		Query query = this.em.createQuery("FROM Property");
		
		List<Property> list = query.getResultList();
		
		return list;
	}

	public List<Property> getCache() {
		return cache;
	}
}
