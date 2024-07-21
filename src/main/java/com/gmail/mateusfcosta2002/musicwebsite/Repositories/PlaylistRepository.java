package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @EntityGraph(attributePaths = "createdBy")
    List<Playlist> findAllWithUserBy();

    @EntityGraph(attributePaths = "createdBy")
    Optional<Playlist> findWithUserById(long id);

    @Query(
        value = "delete from playlists_musics pm where pm.playlist_id = :id and pm.musics_id = :music_id",
        nativeQuery = true)
    @Modifying @Transactional
    void removeMusicById(long id, long music_id);

    @Query(
        value = "insert into playlists_musics(musics_id, playlist_id) values (:music_id, :id)",
        nativeQuery = true
    )
    @Modifying @Transactional
    void addMusicById(long id, long music_id);
}
