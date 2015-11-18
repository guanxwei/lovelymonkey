package com.lovelymonkey.core.dao;

import java.util.List;

public interface BaseDao<T> {

    /**
     * Common method that is used to update an object of any kind.
     * @param o Object represents any kind of object that need to be updated.
     */
    public void updateOrSaveEntity(final T t);

    /**
     * Common method that is used to delete an object of any kind
     * @param o Object represents any kind of object that need to be updated.
     */
    public void daleteEntity(final T t);
    
    /**
     * Common method that is used to get an object by class and id.
     * @param ID object ID uniquely represents an object entity
     * @return
     */
    public T getEntityByID(final Class<T> clazz, final String ID);

    /**
     * Common method that is used to query a list by condition string.
     * @param hql
     * @return
     */
    public  List<T> list(final String hql);

    /**
     * Common method that is used to return the count fulfill the query string.
     * @param hql Query string
     * @return
     */
    public int count(final String hql);

    /**
     * Common method that is used to get the list of entity, which locates from in range [(pageIndex-1)*pageSize,pageIndex*pageSize-1]
     * @param pageIndex
     * @param pageSize
     * @param clazz
     * @return
     */
    public List<T> getListByPageIndex(final int pageIndex, final int pageSize, Class<T> clazz);
}
