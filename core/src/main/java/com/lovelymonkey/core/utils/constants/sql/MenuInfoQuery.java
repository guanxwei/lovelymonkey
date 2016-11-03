package com.lovelymonkey.core.utils.constants.sql;

/**
 * Class that is used to store the query string about menu info.
 * @author guanxwei
 *
 */
public final class MenuInfoQuery {

    private MenuInfoQuery() { }

    /**
     * Query string that is used to query the menu list by user level.
     */
    public static final String QUERY_MENE_BY_USER_LEVEL = "from Menu m where u.level <= ?";
}
