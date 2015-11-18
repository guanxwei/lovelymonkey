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
     * Common method that is used to update an object of any kind.
     * @param <T>
     * @param o Object represents any kind of object that need to be updated.
     */
    public void updateOrSaveEntity(final T t) {
        getCurrentThreadSession().saveOrUpdate(t);
    }

    /**
     * Common method that is used to delete an object of any kind
     * @param o Object represents any kind of object that need to be updated.
     */
    public void daleteEntity(final T t) {
        getCurrentThreadSession().delete(t);
    }
    
    /**
     * Common method that is used to get an object by class and id.
     * @param ID object ID uniquely represents an object entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getEntityByID(final Class<T> clazz, final String ID) {
        return (T) getCurrentThreadSession().get(clazz, ID);
    }

    /**
     * Common method that is used to query a list by condition string.
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
    public  List<T> list(String hql) {
        Query query = getCurrentThreadSession().createQuery(hql);
        return query.list();
    }

    public int count(String hql) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<T> getListByPageIndex(int pageIndex, int pageSize,
            Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }
}
