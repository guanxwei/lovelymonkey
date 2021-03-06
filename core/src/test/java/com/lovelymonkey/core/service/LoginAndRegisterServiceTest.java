package com.lovelymonkey.core.service;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lovelymonkey.core.dao.impl.UserDaoImp;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.constants.sql.UserInfoQuery;

public class LoginAndRegisterServiceTest {

    @Mock private UserDaoImp<User> dao;
    private LoginAndRegisterService service;

    @BeforeMethod
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.service = new LoginAndRegisterService();
        service.setUserDaoImp(dao);
    }

    @Test(dataProvider="userInfoDataProvider")
    public void testIsUserExist(final String userName, final String PSD) {
        User u = new User();
        u.setUserName(userName);
        u.setPassWord(PSD);
        if (userName.equals("notexist")) {
            Mockito.when(dao.getUserByUserNameAndPSD(userName, PSD)).thenReturn(null);
        } else {
            Mockito.when(dao.getUserByUserNameAndPSD(userName, PSD)).thenReturn(u);
        }
        boolean result = service.isUserExist(u);
        if (userName.equals("notexist"))  {
            Assert.assertFalse(result);
        } else {
            Assert.assertTrue(result);
        }
    }

    @Test(dataProvider="userNameDataProvider")
    public void testIsUserNameUsed(String userName) {
        if (userName.equals("used")) {
            Mockito.when(dao.count(UserInfoQuery.QUERY_USER_BY_USERNAME, userName)).thenReturn(1);
        } else {
            Mockito.when(dao.count(UserInfoQuery.QUERY_USER_BY_USERNAME, userName)).thenReturn(0);
        }
        boolean result = service.isUserNameUsed(userName);
        if (userName.equals("notused")) {
            Assert.assertFalse(result);
        } else {
            Assert.assertTrue(result);
        }
    }

    @DataProvider(name="userInfoDataProvider")
    private Object[][] loginUserProvider() {
        return new Object[][]{
                {"notexist","password"},
                {"exist","password"}
        };
    }

    @DataProvider(name="userNameDataProvider")
    private Object[][] userNameProvider() {
        return new Object[][] {
                {"notused"},
                {"used"}
        };
    }
}
