package com.guang.base.daoImpl;

import java.util.LinkedHashMap;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;






import com.guang.base.dao.DaoTools;
import com.guang.base.dao.QueryResult;
@Repository @Transactional
public class DaoToolsImpl implements DaoTools{
	@PersistenceContext private EntityManager em ;
	public void add(Object obj) {
		em.persist(obj);
	}

	public <T> void delete(Class<T> entityclass, Object id) {
		em.remove(find(entityclass,id));
	}
	public void update(Object obj) {
		em.merge(obj);
	}
	public <T> T find(Class<T> entityclass, Object id) {
		return em.find(entityclass, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName,
			String value,int  firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityclass);
		String orderbysq = getOrderBy(orderby);
		String sql = null;
		Query query = null;
		if(columnName!=null && value!=null)
		{
			sql = "select o from "+entityname+" o where o."+columnName+" = '"+value+"' "+orderbysq;
			query = em.createQuery(sql);
		}else{
			sql = "select o from "+entityname+" o "+orderbysq;	
			query = em.createQuery(sql);
		}	
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setList(query.getResultList());
		if(columnName!=null && value!=null)
		{
			query = em.createQuery("select count(o) from "+entityname+" o where o."+columnName+" = '"+value+"' "+orderbysq);
			qr.setNum((Long)query.getSingleResult());
		}else{
			query = em.createQuery("select count(o) from "+entityname+" o");
			qr.setNum((Long)query.getSingleResult());
		}	
		return qr;
	}

	public <T> QueryResult<T> find(Class<T> entityclass,
			LinkedHashMap<String, String> colvals, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityclass);
		String orderbysq = getOrderBy(orderby);
		String colvalssq = getColVals(colvals);
		String sql = null;
		Query query = null;
		if(colvalssq != null && !"".equals(colvalssq))
		{
			sql = "select o from "+entityname+" o where "+colvalssq+orderbysq;
			query = em.createQuery(sql);
		}else{
			sql = "select o from "+entityname+" o "+orderbysq;	
			query = em.createQuery(sql);
		}	
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setList(query.getResultList());
		if(colvalssq != null && !"".equals(colvalssq))
		{
			query = em.createQuery("select count(o) from "+entityname+" o where "+colvalssq+orderbysq);
			qr.setNum((Long)query.getSingleResult());
		}else{
			query = em.createQuery("select count(o) from "+entityname+" o");
			qr.setNum((Long)query.getSingleResult());
		}	
		return qr;
	}
	
	
	public <T> QueryResult<T> find(Class<T> entityclass, String columnName,
			String value, String columnName2, String value2, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityclass);
		String orderbysq = getOrderBy(orderby);
		String sql = null;
		Query query = null;
		if(columnName!=null && value!=null && columnName2!=null && value2!=null)
		{
			sql = "select o from "+entityname+" o where o."+columnName+" = '"+value+"' and o."+columnName2+" = '"+value2+"' " +orderbysq;
			query = em.createQuery(sql);
		}else{
			sql = "select o from "+entityname+" o "+orderbysq;	
			query = em.createQuery(sql);
		}	
		query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setList(query.getResultList());
		if(columnName!=null && value!=null)
		{
			query = em.createQuery("select count(o) from "+entityname+" o where o."+columnName+" = '"+value+"' and o."+columnName2+" = '"+value2+"' "+orderbysq);
			qr.setNum((Long)query.getSingleResult());
		}else{
			query = em.createQuery("select count(o) from "+entityname+" o");
			qr.setNum((Long)query.getSingleResult());
		}	
		return qr;
	}

	public String getColVals(LinkedHashMap<String, String> colvals)
	{
		StringBuffer colvalssq = new StringBuffer("");
		if(colvals!=null && colvals.size()>0){
			for(String key : colvals.keySet()){
				colvalssq.append("o.").append(key+" ").append(colvals.get(key)+" and ");
			}
			colvalssq.delete(colvalssq.length()-4, colvalssq.length()-1);
		}
		return colvalssq.toString();		
	}

	
		protected String getOrderBy(LinkedHashMap<String, String> orderby)
		{
			StringBuffer orderbysq = new StringBuffer("");
			if(orderby!=null && orderby.size()>0){
				orderbysq.append(" order by ");
				for(String key : orderby.keySet()){
					orderbysq.append("o.").append(key+" ").append(orderby.get(key)+",");
				}
				orderbysq.deleteCharAt(orderbysq.length()-1);
			}
			return orderbysq.toString();			
		}
	protected <T> String getEntityName(Class<T> entityclass){
		String entityname = entityclass.getSimpleName();
		Entity entity = entityclass.getAnnotation(Entity.class);
		if(entity.name()!=null&& !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}

	//过滤重复值
		public <T> QueryResult<T> find(Class<T> entityclass,
				List<String> fileds,
				LinkedHashMap<String, String> colvals) {
			QueryResult<T> qr = new QueryResult<T>();
			String entityname = getEntityName(entityclass);
			String colvalssq = getColVals(colvals);
			String filedssq = getFiledVals(fileds);
			String sql = null;
			Query query = null;
			if(colvalssq != null && !"".equals(colvalssq))
			{
				sql = "select distinct " +filedssq+ " from "+entityname+" o where "+colvalssq;
				query = em.createQuery(sql);
			}else{
				sql = "select distinct " +filedssq+ " from "+entityname+" o ";	
				query = em.createQuery(sql);
			}	
			query.setFirstResult(0).setMaxResults(10000000);
			qr.setList(query.getResultList());
			return qr;
		}
		
		public String getFiledVals(List<String> fileds)
		{
			StringBuffer colvalssq = new StringBuffer("");
			if(fileds!=null && fileds.size()>0)
			{
				for(String filed : fileds)
				{
					colvalssq.append("o.").append(filed+",");
				}
				colvalssq.deleteCharAt(colvalssq.length()-1);
			}
			return colvalssq.toString();		
		}
}
