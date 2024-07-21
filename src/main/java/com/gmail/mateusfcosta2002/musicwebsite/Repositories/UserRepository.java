package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findWithAuthoritiesByUsername(String username);

    Stream<User> findAllStreamBy();
}
