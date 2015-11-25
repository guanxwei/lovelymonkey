package com.lovelymonkey.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lovelymonkey.core.dao.BaseDao;

public class BaseDaoImp<T> implements BaseDao<T>{
   
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getCurrentThreadSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * Common method realization that is used to update an object of any kind.
     * @param <T>
     * @param o Object represents any kind of object that need to be updated.
     */
    public void updateOrSaveEntity(final T t) {
        getCurrentThreadSession().saveOrUpdate(t);
    }

    /**
     * Common method realization that is used to delete an object of any kind
     * @param o Object represents any kind of object that need to be updated.
     */
    public void daleteEntity(final T t) {
        getCurrentThreadSession().delete(t);
    }
    
    /**
     * Common method realization that is used to get an object by class and id.
     * @param ID object ID uniquely represents an object entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getEntityByID(final Class<T> clazz, final String ID) {
        return (T) getCurrentThreadSession().get(clazz, ID);
    }

    /**
     * Common method realization that is used to query a list by condition string.
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
    public  List<T> list(final String hql, final String ...params) {
        Query query = getCurrentThreadSession().createQuery(hql);
        int count = 0;
        for (String param : params) {
            query.setString(count++, param);
        }
        return query.list();
    }

    /**
     * Common method realization that is used to query the number of objects, which match the request.
     */
    public int count(final String hql, final String ...params) {
        // TODO Auto-generated method stub
        Query query = getCurrentThreadSession().createQuery(hql);
        int count = 0;
        for (String param : params) {
            query.setString(count++, param);
        }
        return query.uniqueResult() == null ? 0:1;
    }

    /**
     * Common method realization that is used to get paging data,  which locates from in range [(pageIndex-1)*pageSize,pageIndex*pageSize-1].
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByPageIndex(int pageIndex, int pageSize, final String hql, final String ...params) {
        // TODO Auto-generated method stub
        Query query = getCurrentThreadSession().createQuery(hql);
        int count = 0;
        for (String param : params) {
            query.setString(count++, param);
        }
        query.setFirstResult((pageSize-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }
}
