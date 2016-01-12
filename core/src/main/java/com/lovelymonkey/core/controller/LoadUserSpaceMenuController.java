package com.lovelymonkey.core.controller;

import java.util.List;

import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lovelymonkey.core.builder.UserBuilder;
import com.lovelymonkey.core.model.Menu;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoadUserSpaceMenuService;
import com.lovelymonkey.core.utils.JasonHelper;

@Controller
@RequestMapping(value = "/menu")
@Slf4j
public class LoadUserSpaceMenuController {

    @Autowired @Setter
    private LoadUserSpaceMenuService loadUserSpaceMenuService;

    @RequestMapping(value = "/loadMenuByUserLevel.htm")
    public String loadMenuByUserLevel(@RequestParam final int userLevel, 
            @RequestParam @NonNull final String userName) {

        log.info("Incoming request for user [{}]", userName);

        User u = UserBuilder.builder()
                .level(userLevel)
                .userName(userName)
                .build();

        log.info("Direct reqeust to service [{}]", LoadUserSpaceMenuService.class.getName());

        List<Menu> menuList = loadUserSpaceMenuService.getMenuListByUserLevel(u);
        String jasonString = JasonHelper.translateObjectToJasonString(menuList);
        return jasonString;
    }
}
