package com.lovelymonkey.core.widget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WidgetConfiguration {

    @Bean
    public WidgetRegister widgetRegister() {
        WidgetRegister register = new WidgetRegister();
        return register;
    }
}
