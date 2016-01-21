package com.lovelymonkey.core.widget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Widget configuration.
 * @author guanxwei
 *
 */
@Configuration
public class WidgetConfiguration {

    /**
     * Return WidgetRegister.
     * @return WidgetRegister.
     */
    @Bean
    public WidgetRegister widgetRegister() {
        WidgetRegister register = new WidgetRegister();
        return register;
    }
}
