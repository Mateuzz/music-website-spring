package com.gmail.mateusfcosta2002.musicwebsite.Config;

import org.hibernate.reactive.mutiny.Mutiny.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;

import io.smallrye.mutiny.converters.multi.MultiReactorConverters;
import io.smallrye.mutiny.converters.multi.ToMono;
import io.smallrye.mutiny.converters.uni.UniReactorConverters;
import jakarta.persistence.Persistence;

@Configuration
public class DatabaseConfiguration {
    @Bean
    SessionFactory sessionFactory() {
        return Persistence.createEntityManagerFactory("music-website")
            .unwrap(SessionFactory.class);
    }

    void legal() {
        sessionFactory().withSession((s) -> {
            var result = s.find(Music.class, 10);
            return result;
        }).convert().with(UniReactorConverters.toMono());
    }
}
