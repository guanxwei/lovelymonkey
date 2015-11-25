package com.lovelymonkey.core.functional;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.JasonHelper;

public class LoginAndRegisterControllerTest extends TestBase{
    
    @Autowired
    private LoginAndRegisterService service;

    @SuppressWarnings("unchecked")
    @Test(dataProvider="userNameProvider")
    public void testIsUserNameUsed(String userName) throws URISyntaxException, Exception {
        if (userName.equals("used")) {
            //If the useName is "used", then we need to prepare a user info in DB to make sure this user info can be queried.
            User u = new User();
            u.setUserName("used");
            u.setPassWord("pass");
            service.updateOrSaveUser(u);
            u = (User) service.getUserDaoImp().getUserByUserNameAndPSD("used", "pass");
            MvcResult result = getMockMvc().perform(getRequestBuilder("/user/judge.htm?userName=used"))
                    .andDo(MockMvcResultHandlers.print())  
                    .andReturn();

            System.out.println(result.toString());
            Assert.assertEquals(result.getResponse().getContentAsString(), "true");
            service.getUserDaoImp().daleteEntity(u);
        } else{
            MvcResult result = getMockMvc().perform(getRequestBuilder("/user/judge.htm?userName=unused"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            
            Assert.assertEquals(result.getResponse().getContentAsString(), "false");
        }
    }
    
    @DataProvider(name="userInfoProvider")
    private Object[][] userInfoProvider() {
        return new Object[][]{
                {"",""},
                {"",""},
                {},
                {}
        };
    }
    
    @DataProvider(name="userNameProvider")
    private Object[][] userNameProvider() {
        return new Object[][]{
                {"unused"}
        };
    }
}
