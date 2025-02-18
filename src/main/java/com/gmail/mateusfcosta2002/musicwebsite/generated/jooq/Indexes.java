/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.MusicTags;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index IDXGU7JS9MH96HPEQM6EH7SP2K56 = Internal.createIndex(DSL.name("idxgu7js9mh96hpeqm6eh7sp2k56"), MusicTags.MUSIC_TAGS, new OrderField[] { MusicTags.MUSIC_TAGS.NAME }, false);
    public static final Index MUSICS_SEARCH_IDX = Internal.createIndex(DSL.name("musics_search_idx"), Musics.MUSICS, new OrderField[] { Musics.MUSICS.SEARCH }, false);
}
