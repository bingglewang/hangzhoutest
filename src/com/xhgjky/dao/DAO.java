package com.xhgjky.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DAO extends HibernateDaoSupport{
	/**
	 * Add
	 * @param obj
	 */
	public void add(Object obj){
		this.getHibernateTemplate().save(obj);
	}
	/**
	 * Del
	 * @param obj
	 */
	public void del(Object obj){
		this.getHibernateTemplate().delete(obj);
	}
	/**
	 * Del
	 * @param cla
	 * @param OID
	 */
	public void del(Class cla,String OID){
		this.getHibernateTemplate().delete(this.getObj(cla, OID));
	}
	/**
	 * GetObj
	 * @param cla
	 * @param OID
	 * @return
	 */
	public Object getObj(Class cla,Serializable OID){
		return this.getHibernateTemplate().get(cla, OID);
	}
	/**
	 * BatchDel
	 * @param hql
	 * @param objects
	 */
	public void batchDel(final String hql, final Object... objects){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
//		String hqlDelete = "delete DeviceHistory dev_h where dev_h.device_id = :oldName";
		int deletedEntities = session.createQuery( hql )
		.setString( "oldName", objects[0].toString() )
		.executeUpdate();
		tx.commit();
		session.close();
	}
	/**
	 * BatchDel
	 * @param cla
	 * @param delece_id
	 */
	public void batchDel(Class cla,String delece_id){
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction ts = null;
		try {
			ts = session.beginTransaction();
			String[] del_id_array = delece_id.split(";");
			for (int i = 0; i < del_id_array.length; i++) {
				session.delete(this.getObj(cla, del_id_array[i]));
				if(i % 20 == 0){
					session.flush();
					session.clear();
				}
			}
			ts.commit();
		} catch (Exception e) {
			ts.rollback();
		}finally{
			ts = null;
			session.close();
		}
	}
	/**
	 * Upd
	 * @param obj
	 */
	public void upd(Object obj){
		this.getHibernateTemplate().update(obj);
	} 
	/**
	 * FindObjs
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List findObjs(String hql,Object... objects){
		return this.getHibernateTemplate().find(hql, objects);
	}
	/**
	 * FindObj
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object findObj(String hql,Object... objects){
		if(this.getHibernateTemplate().find(hql,objects).size() == 0 )
			return null;
		return this.getHibernateTemplate().find(hql,objects).get(0);
	}
	/**
	 * GetTotalRecord
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object getTotalRecord(String hql,Object... objects){
		return this.getHibernateTemplate().find(hql, objects).iterator().next();
	}
	/**
	 * QueryByPage
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @param objects
	 * @return
	 */
	public List queryByPage(final String hql,final int currentPage,final int pageSize,final Object... objects){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				int startRecord = (currentPage-1)*pageSize+1;
				int endRecord = pageSize;
				query.setFirstResult(startRecord-1);
				query.setMaxResults(endRecord);
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i, objects[i]);
				}
				return query.list();
			}
		});
	}
	/**
	 * 采用原生sql查询
	 * @param sql
	 * @param cla
	 * @return 
	 */
	public Object executeSQL(final String sql,final Class cla){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				session.createSQLQuery(sql).executeUpdate();;
				return null;
			}
		});
	}
}
