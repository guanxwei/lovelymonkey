package com.lovelymonkey.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lovelymonkey.core.dao.BaseDao;

/**
 * Implements the method defined in class BaseDao<T>.
 * @author guanxwei
 *
 * @param <T>
 */
public class BaseDaoImp<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentThreadSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * Common method realization that is used to update an object of any kind.
     * @param t Object represents any kind of object that need to be updated.
     */
    public void updateOrSaveEntity(final T t) {
        getCurrentThreadSession().saveOrUpdate(t);
    }

    /**
     * Common method realization that is used to delete an object of any kind.
     * @param t Object represents any kind of object that need to be updated.
     */
    public void deleteEntity(final T t) {
        getCurrentThreadSession().delete(t);
    }

    /**
     * Common method realization that is used to get an object by class and id.
     * @param clazz Model class which is mapped to a datadase table.
     * @param entityID object ID uniquely represents an object entity.
     * @return The queried entity.
     */
    @SuppressWarnings("unchecked")
    public T getEntityByID(final Class<T> clazz, final String entityID) {
        return (T) getCurrentThreadSession().get(clazz, entityID);
    }

    /**
     * Common method realization that is used to query a list by condition string.
     * @param hql SQL query string.
     * @param params Parameters which will represents the ? in hql.
     * @return The list of objects fulfill the query string.
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
     * @param hql Query string.
     * @param params Parameters which will represents the ? in hql.
     * @return The number of objects fuifill the query string.
     */
    public int count(final String hql, final String ...params) {
        // TODO Auto-generated method stub
        Query query = getCurrentThreadSession().createQuery(hql);
        int count = 0;
        for (String param : params) {
            query.setString(count++, param);
        }
        return query.uniqueResult() == null ? 0 : 1;
    }

    /**
     * Common method realization that is used to get paging data,  which locates from in range [(pageIndex-1)*pageSize,pageIndex*pageSize-1].
     * @param pageIndex The index currently need to be queried.
     * @param pageSize Page size.
     * @param hql SQL query string.
     * @param params Parameters which will represents the ? in hql.
     * @return he list of objects fulfill the query string.
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByPageIndex(final int pageIndex, final int pageSize, final String hql, final String ...params) {
        // TODO Auto-generated method stub
        Query query = getCurrentThreadSession().createQuery(hql);
        int count = 0;
        for (String param : params) {
            query.setString(count++, param);
        }

        query.setFirstResult((pageSize - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }
}
