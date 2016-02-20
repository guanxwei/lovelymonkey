package com.lovelymonkey.core.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lovelymonkey.core.builder.UserBuilder;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.ControllerConstant;
import com.lovelymonkey.core.utils.RequestHandleConstant;

public class LoginAndRegisterControllerTest extends TestBase{

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    private String USER_NAME_USED = "used";
    private String USER_NAME_UNUSED = "unused";

    /**
     * Test if the specific user name has been used.
     */
    @Test(dataProvider="userNameProvider")
    public void testIsUserNameUsed(String userName) throws Exception {
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

            loginAndRegisterService.deleteUserByUserName(USER_NAME_UNUSED);

            Assert.assertEquals(result.getResponse().getContentAsString(), "false");
        }
    }

    /**
     * Test user account register happy case.
     */
    @Test
    public void testRegisterUser() throws Exception {
        String url = "/user/doRegister.htm";
        MockHttpServletRequestBuilder builder = (MockHttpServletRequestBuilder) postRequestBuilder(url);
        builder.param("userName", USER_NAME_UNUSED).param("passWord", "123456");
        MvcResult result = getMockMvc().perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        User currentUser = (User) result.getRequest().getSession().getAttribute(ControllerConstant.LoginAndRegisterControlerConstants.CURRENT_USER);
        Assert.assertNotNull(currentUser);
        Assert.assertEquals(currentUser.getUserName(), USER_NAME_UNUSED);
        Assert.assertEquals(currentUser.getPassWord(), "123456");
        User u = loginAndRegisterService.getUserByUserNameAndPSD(USER_NAME_UNUSED, "123456");
        Assert.assertNotNull(u);
        loginAndRegisterService.deleteUser(u);
    }

    /**
     * Test user account register unhappy case.
     */
    @Test
    public void testRegisterUserUnHappyCase() throws Exception {

        User u = populateUserInDB(USER_NAME_USED);
        Assert.assertNotNull(u);

        String url = "/user/doRegister.htm";
        MockHttpServletRequestBuilder builder = (MockHttpServletRequestBuilder) postRequestBuilder(url);
        builder.param("userName", USER_NAME_USED).param("passWord", "123456");
        MvcResult result = getMockMvc().perform(builder)
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(), RequestHandleConstant.UserManageStatus.REGISTER_SYSTEM_FAILED);
        loginAndRegisterService.deleteUser(u);
    }

    /**
     * Test email is used happy case.
     */
    @Test(dataProvider="emailStatusProvider")
    public void testEmailIsUsed(boolean used) throws Exception {
        if (used) {
            populateUserInDB("random");

            String url = "/user/verifymail.htm?email=mail";
            MockHttpServletRequestBuilder builder = (MockHttpServletRequestBuilder) getRequestBuilder(url);
            MvcResult result = getMockMvc().perform(builder)
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
            Assert.assertEquals(result.getResponse().getContentAsString(), "true");
            loginAndRegisterService.deleteUserByUserName("random");
        } else {
            String url = "/user/verifymail.htm?email=mail";
            MockHttpServletRequestBuilder builder = (MockHttpServletRequestBuilder) getRequestBuilder(url);
            MvcResult result = getMockMvc().perform(builder)
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertNotNull(result.getResponse().getContentAsString());
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
                {"used"},
                {"notused"}
        };
    }

    @DataProvider(name="emailStatusProvider")
    private Object[][] emailUsedStatusProvider() {
        return new Object[][]{
                {true},
                {false}
        };
    }
    /**
     * 
     * @param userName The UserName of a specific user.
     * @return The new created user entity.
     */
    private User populateUserInDB(final String userName) {
        User u = UserBuilder.builder()
                .userName(userName)
                .passWord("pass")
                .email("mail")
                .build();

        loginAndRegisterService.updateOrSaveUser(u);
        u = (User) loginAndRegisterService.getUserByUserNameAndPSD(userName, "pass");

        return u;
    }

}
