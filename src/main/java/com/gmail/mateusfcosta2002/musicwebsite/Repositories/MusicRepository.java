package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    @EntityGraph(attributePaths = {"tags", "category", "author"})
    Stream<Music> findAllWithAuthorCategoryTagsBy();
}
