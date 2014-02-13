package com.instantChat.daoI.daoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.instantChat.daoI.BaseDaoI;

public class BaseDaoImpl<T> implements BaseDaoI<T>{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Serializable save(T o) {
		return sessionFactory.getCurrentSession().save(o);
	}
	
	public T get(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if(l != null && l.size() > 0){
			return l.get(0);
		}
		return null;
	}
	public T get(String hql, Object[] params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && params.length > 0){
			for(int i = 0; i< params.length; i++){
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if(l != null && l.size() > 0){
			return l.get(0);
		}
		return null;
	}
	
	public T get(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	public void delete(T o) {
		sessionFactory.getCurrentSession().delete(o);
	}
	//带有参数的删除
	public void delete(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for(String key : params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		q.executeUpdate();
	}
	
	public void update(T o) {
		sessionFactory.getCurrentSession().update(o);	
	}
	
	public void update(String hql, Map<String, Object>params){
		//Session session = sessionFactory.getCurrentSession();	
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}	
		q.executeUpdate();
	}
	
	public void saveOrUpdate(T o) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
	}
	
	public List<T> find(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//传递参数并且查询
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1)*rows).setMaxResults(rows).list();
	}
	
	public List<T> find(String hql, int page, int rows) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1)*rows).setMaxResults(rows).list();
	}
	
	public Long count(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return (Long)q.uniqueResult();
	}
	
	public Long count(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long)q.uniqueResult();
	}

	public int countInt(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return ((Number) q.uniqueResult()).intValue();
	}
	
	@Override
	public int countInt(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return ((Number) q.uniqueResult()).intValue();
	}

}









