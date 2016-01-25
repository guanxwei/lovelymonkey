package com.lovelymonkey.core.plugin;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.lovelymonkey.core.plugin.emailnotificationplugin.EmailNotificationConstants;
import com.lovelymonkey.core.plugin.emailnotificationplugin.EmailNotificationPlugin;
import com.lovelymonkey.core.plugin.emailnotificationplugin.EmailNotificationWorker;

/**
 * Plug-in framework configuration.
 * @author guanxwei
 *
 */
@Configuration
@PropertySource(value = "classpath:com/lovelymonkey/core/plugin/emailnotificationplugin/account.properties", name = "account")
public class PluginConfiguration {

    @Autowired
    private Environment env;

    /**
     * Plug-in manager responsible for managing the plug-ins.
     * @return The plug-in manager.
     */
    @Bean
    public PluginManager getPluginManager() {
        PluginManager manager = new PluginManager();
        manager.registerPlugin(getEmailNotificationPlugin());
        return manager;
    }

    /**
     * Email notification plug-in responsible for sending email to customers.
     * @return The EmailNotificationPlugin.
     */
    @Bean
    public EmailNotificationPlugin getEmailNotificationPlugin() {
        return new EmailNotificationPlugin();
    }

    /**
     * Email notification real worker responsible for sending mails, EmailNotificationPlugin will delegate all the
     * request to the worker.
     * @return The EmailNotificationWorker.
     */
    @Bean
    public EmailNotificationWorker getEmailNotificationWorker() {
        return new EmailNotificationWorker();
    }

    /**
     * JavaMailSender configuration.
     * @return JavaMailSender.
     */
    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol(env.getProperty("default_protocol"));
        sender.setDefaultEncoding(EmailNotificationConstants.DEFAULT_ENCODE);
        sender.setPort(Integer.valueOf(env.getProperty("port")));
        sender.setHost(env.getProperty("default_host"));
        sender.setUsername(env.getProperty("username"));
        sender.setPassword(env.getProperty("password"));
        Properties property = new Properties();
        property.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        property.put("mail.smtp.timeout", env.getProperty("mail.smtp.timeout"));
        property.put("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
        sender.setJavaMailProperties(property);
        return sender;
    }
}
