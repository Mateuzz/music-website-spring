package com.gmail.mateusfcosta2002.musicwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class);
    }

    @Bean
    public LocalValidatorFactoryBean defaultValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean 
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
