package com.gmail.mateusfcosta2002.musicwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music_;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        var app = new SpringApplication(App.class);

        System.out.println(Music_.class + Music_.AUTHOR);
        app.run();
    }
}
