package com.lovelymonkey.core.start;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Aware object notified when the Spring ApplicationContext is initiated, mainly used to set up some system properties used by
 * other system components. This aware will load the properties defined in the system.properties file, then set them to the
 * system's properties. One typical scenario to use this aware it so set up the base name of locale properties files.
 * 
 * @author guanxwei
 *
 */
@Slf4j
public class SystemPropertiesInitiateAware implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        WebApplicationContext webContext = (WebApplicationContext) applicationContext;
        String systemProperties = (String) webContext.getServletContext().getAttribute("system.properties");
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(systemProperties));
        } catch (IOException e) {
            log.error("System properties definition file not found!");
        }
        System.setProperties(properties);
    }

}
