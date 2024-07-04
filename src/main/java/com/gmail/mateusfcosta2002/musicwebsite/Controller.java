package com.gmail.mateusfcosta2002.musicwebsite;

import java.io.Serializable;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import reactor.core.publisher.Mono;

record User(String name, int age) implements Serializable {}

@RestController
public class Controller {
    ObjectMapper mapper;

    public Controller(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler
    public ResponseEntity<String> onError(Exception e) {
        return ResponseEntity.status(500).body("Error");
    }

    @GetMapping("/t4")
    public ResponseEntity<Mono<User>> get() {
        return ResponseEntity.ok()
            .body(Mono.just(new User("ResponseEntity<Mono<User>>", 10)));
    }

    @GetMapping("/t3")
    public Mono<ServerResponse> get2() {

        return ServerResponse.ok() .bodyValue(BodyInserters.fromValue(new User("Mono<ServerResponse>", 10))); } @GetMapping(path = "/t2", produces = "application/json") public Mono<User> testeNode() {
        return Mono.just(new User("Mono<Serializable>", 10));
    }

    @GetMapping("/t1")
    public Mono<ResponseEntity<Object>> teste() {
        var json = mapper.createObjectNode();
        json.set("firstName", JsonNodeFactory.instance.textNode("jackson"));

        return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(json));
    }
}
