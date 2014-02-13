package com.instantChat.daoI;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {

	public Serializable save(T o);
	public T get(String hql);
	public T get(String hql, Object[] params);
	public T get(String hql, Map<String, Object> params);
	public void delete(T o);
	public void delete(String hql, Map<String, Object> params);
	public void update(T o);
	public void update(String hql, Map<String, Object>params);
	public void saveOrUpdate(T o);
	public List<T> find(String hql);
	public List<T> find(String hql, Map<String, Object> params);
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);
	public List<T> find(String hql, int page, int rows);
	public Long count(String hql);
	public Long count(String hql, Map<String, Object> params);
	public int countInt(String hql, Map<String, Object> params);
	public int countInt(String hql);
}
