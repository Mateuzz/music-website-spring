package com.gmail.mateusfcosta2002.musicwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class);
    }

    //@Bean
    //public MessageSource messageSource(Environment env) {
    //    var msg = new ResourceBundleMessageSource();
    //
    //    msg.setBasenames(env
    //        .getProperty(
    //            "app.web.base-message-source", 
    //            String[].class, 
    //            new String[]{"classpath:messages"}));
    //
    //    return msg;
    //}
}
