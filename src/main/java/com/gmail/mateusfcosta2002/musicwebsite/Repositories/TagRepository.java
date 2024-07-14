package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
