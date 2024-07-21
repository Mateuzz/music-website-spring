package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import org.jooq.Record;
import org.jooq.Result;

import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;

public interface CustomMusicRepository {
    public enum MusicOrder { latest, oldest, popular };

    PageResult<Result<? extends Record>, Record> 
    search(String search, int pageSize, int pageNumber, MusicOrder order, Long authorIdFilter);
}
