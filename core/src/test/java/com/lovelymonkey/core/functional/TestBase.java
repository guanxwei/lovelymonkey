package com.lovelymonkey.core.functional;

import lombok.Getter;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@ContextHierarchy({  
        @ContextConfiguration(name = "parent", locations = "classpath:applicationContext-test.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})  
@WebAppConfiguration(value = "src/main/webapp")
public abstract class TestBase extends AbstractTestNGSpringContextTests{

    @Autowired
    private WebApplicationContext context;

    @Getter
    private MockMvc mockMvc;

    @BeforeSuite(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @BeforeClass
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public RequestBuilder postRequestBuilder(final String url) {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);

        return requestBuilder;
    }

    public RequestBuilder getRequestBuilder(final String url) {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);

        return requestBuilder;
    }
}
