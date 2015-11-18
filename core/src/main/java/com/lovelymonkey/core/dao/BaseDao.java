package com.lovelymonkey.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getCurrentThreadSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 
     * @param queryString query string used to query the entity we want
     * @param ID object ID uniquely represents an object entity
     * @return
     */
    protected abstract Object getObjectByID(String ID) ;

    /**
     * Common method that is used to update an object of any kind.
     * @param o Object represents any kind of object that need to be updated.
     */
    public void updateOrSaveEntity(Object o) {
        getCurrentThreadSession().saveOrUpdate(o);
    }

    /**
     * Common method that is used to delete an object of any kind
     * @param o Object represents any kind of object that need to be updated.
     */
    public void daleteEntity(Object o) {
        getCurrentThreadSession().delete(o);
    }
}
