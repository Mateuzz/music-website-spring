package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import org.jooq.Record;
import org.jooq.Result;

import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;

public interface CustomMusicRepository {
    public enum MusicOrder { latest, oldest, popular };
    public enum MusicPlaylistOrder { latest, oldest };

    PageResult<Result<? extends Record>, Record> 
    search(String search, int pageSize, int pageNumber, MusicOrder order, Long authorID, Long categoryID, String tags[]);

    PageResult<Result<? extends Record>, Record>  
    searchByCategory(String search, int pageSize, int pageNumber, MusicPlaylistOrder order, long playlistID);
}
