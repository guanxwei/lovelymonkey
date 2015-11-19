package com.lovelymonkey.core.service.test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.dao.impl.UserDaoImp;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;

public class LoginAndRegisterServiceTest {

    private UserDao<User> dao;
    private LoginAndRegisterService service;
    
    @BeforeClass
    public void before() {
        this.dao = Mockito.mock(UserDaoImp.class);
        this.service = new LoginAndRegisterService();
        service.setUserDaoImp(dao);
    }

    @Test
    public void testLoginWithDifferentInputUser(User input) {
        
    }

    @Test
    public void testRegisterWithDifferentInputUser(User input) {
            
        
    }
    
    
    @DataProvider
    private Object[][] loginUserProvider() {
        return new Object[][]{
                
        };
    }
}
