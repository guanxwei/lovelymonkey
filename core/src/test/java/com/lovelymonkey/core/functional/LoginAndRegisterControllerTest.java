package com.lovelymonkey.core.functional;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.ControllerConstant;

public class LoginAndRegisterControllerTest extends TestBase{

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    private String USER_NAME_USED = "used";
    private String USER_NAME_UNUSED = "unused";

    @Test(dataProvider="userNameProvider")
    public void testIsUserNameUsed(String userName) throws URISyntaxException, Exception {
        if (userName.equals(USER_NAME_USED)) {
            //If the useName is "used", then we need to prepare a user info in DB to make sure this user info can be queried.
            User u = populateUserInDB(userName);

            MvcResult result = getMockMvc().perform(getRequestBuilder("/user/judge.htm?userName=" + USER_NAME_USED))
                    .andDo(MockMvcResultHandlers.print())  
                    .andReturn();

            /** Release the new created user info to make the DB can be reused. **/
            loginAndRegisterService.deleteUser(u);

            Assert.assertEquals(result.getResponse().getContentAsString(), "true");
        } else{
            MvcResult result = getMockMvc().perform(getRequestBuilder("/user/judge.htm?userName=" + USER_NAME_UNUSED))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

            Assert.assertEquals(result.getResponse().getContentAsString(), "false");
        }
    }

    @Test
    public void testRegisterUser() throws Exception {
        String url = "/user/doRegister.htm";
        MockHttpServletRequestBuilder builder = (MockHttpServletRequestBuilder) postRequestBuilder(url);
        builder.param("userName", "guanxwei").param("passWord", "123456");
        MvcResult result = getMockMvc().perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        User currentUser = (User) result.getRequest().getSession().getAttribute(ControllerConstant.LoginAndRegisterControlerConstants.CURRENT_USER);
        Assert.assertNotNull(currentUser);
        Assert.assertEquals(currentUser.getUserName(), "guanxwei");
        Assert.assertEquals(currentUser.getPassWord(), "123456");
        User u = loginAndRegisterService.getUserByUserNameAndPSD("guanxwei", "123456");
        Assert.assertNotNull(u);
        loginAndRegisterService.deleteUser(u);
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
                {"used"}
        };
    }

    /**
     * 
     * @param userName The UserName of a specific user.
     * @return The new created user entity.
     */
    private User populateUserInDB(final String userName) {
        User u = new User();
        u.setUserName(userName);
        u.setPassWord("pass");
        loginAndRegisterService.updateOrSaveUser(u);
        u = (User) loginAndRegisterService.getUserByUserNameAndPSD(userName, "pass");

        return u;
    }
}