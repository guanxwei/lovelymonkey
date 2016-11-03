package com.lovelymonkey.core.dao;

import java.util.List;

/**
 * Interface define the method that is used to query and fetch entities used commonly.
 * @author guanxwei
 *
 * @param <T>
 */
 public interface BaseDao<T> {

    /**
     * Common method that is used to update an object of any kind.
     * @param t Object represents any kind of object that need to be updated.
     */
     void updateOrSaveEntity(final T t);

    /**
     * Common method that is used to delete an object of any kind.
     * @param t Object represents any kind of object that need to be updated.
     */
     void deleteEntity(final T t);

    /**
     * Common method that is used to get an object by class and id.
     * @param entityID object ID uniquely represents an object entity
     * @param clazz Model class which is mapped to a datadase table.
     * @return The queried entity.
     */
     T getEntityByID(final Class<T> clazz, final String entityID);

    /**
     * Common method that is used to query a list by condition string.
     * @param hql SQL query string.
     * @param params Parameters which will represents the ? in hql.
     * @return The list of objects fulfill the query string.
     */
      List<T> list(final String hql, final String ...params);

    /**
     * Common method that is used to return the count fulfill the query string.
     * @param sql Orignal SQL Query string.
     * @param params Parameters which will represents the ? in hql.
     * @return The number of objects fuifill the query string.
     */
     int count(final String sql, final String ...params);

    /**
     * Common method that is used to get the paging data, which locates from in range [(pageIndex-1)*pageSize,pageIndex*pageSize-1].
     * @param pageIndex The index currently need to be queried.
     * @param pageSize Page size.
     * @param hql SQL query string.
     * @param params Parameters which will represents the ? in hql.
     * @return he list of objects fulfill the query string.
     */
     List<T> getListByPageIndex(final int pageIndex, final int pageSize, final String hql, final String ...params);
}
