package com.lovelymonkey.core.service;

import java.util.List;

import org.hibernate.annotations.NotFound;

import com.lovelymonkey.core.dao.BaseDao;
import com.lovelymonkey.core.model.Menu;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * LoadUserSpaceMenuService
 * @author guanxwei
 *
 */
@Slf4j
public class LoadUserSpaceMenuService {

    @Setter @Getter
    private BaseDao<Menu> baseDao;

    public List<Menu> getMenuListByUserLevel(@NonNull final User user) {
        log.info("Fetch menu list for user [{}], user's level is [{}]",user.getUserName(), user.getLevel());

        try {
            return baseDao.list(SQLQueryConstant.MenuInfoQuery.QUERY_MENE_BY_USER_LEVEL,
                    String.valueOf(user.getLevel()));
        } catch(Exception e) {
            log.error("Failed to fetch menu list for user [{}]", user.getUserName());
            throw new RuntimeException(e.getMessage());
        }
    }
}
