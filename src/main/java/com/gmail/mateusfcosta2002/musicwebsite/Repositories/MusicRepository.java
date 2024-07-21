package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long>, CustomMusicRepository {
    @EntityGraph(attributePaths = {"tags", "category", "author"})
    Stream<Music> findAllWithAuthorCategoryTagsBy();
}
