package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.UserAuthority;

@Repository
public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
}
