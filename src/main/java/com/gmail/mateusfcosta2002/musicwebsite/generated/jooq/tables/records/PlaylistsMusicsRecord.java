/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.PlaylistsMusics;

import java.time.OffsetDateTime;

import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PlaylistsMusicsRecord extends UpdatableRecordImpl<PlaylistsMusicsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.playlists_musics.musics_id</code>.
     */
    public void setMusicsId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.playlists_musics.musics_id</code>.
     */
    public Long getMusicsId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.playlists_musics.playlist_id</code>.
     */
    public void setPlaylistId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.playlists_musics.playlist_id</code>.
     */
    public Long getPlaylistId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.playlists_musics.added_date</code>.
     */
    public void setAddedDate(OffsetDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.playlists_musics.added_date</code>.
     */
    public OffsetDateTime getAddedDate() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlaylistsMusicsRecord
     */
    public PlaylistsMusicsRecord() {
        super(PlaylistsMusics.PLAYLISTS_MUSICS);
    }

    /**
     * Create a detached, initialised PlaylistsMusicsRecord
     */
    public PlaylistsMusicsRecord(Long musicsId, Long playlistId, OffsetDateTime addedDate) {
        super(PlaylistsMusics.PLAYLISTS_MUSICS);

        setMusicsId(musicsId);
        setPlaylistId(playlistId);
        setAddedDate(addedDate);
        resetChangedOnNotNull();
    }
}
