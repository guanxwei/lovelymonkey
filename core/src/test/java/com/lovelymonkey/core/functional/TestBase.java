package com.lovelymonkey.core.functional;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.Getter;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@ContextConfiguration(locations="classpath:application.xml")
@WebAppConfiguration
public abstract class TestBase extends AbstractTestNGSpringContextTests{

    @Autowired
    private WebApplicationContext context;
    
    @Getter
    private MockMvc mockMvc;

    protected abstract Object getController();
    
    @BeforeSuite(alwaysRun=true)
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @BeforeClass
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    protected RequestBuilder postRequestBuilder(String url) throws URISyntaxException {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                new URI(url));new uri
        return requestBuilder;
    }
}
