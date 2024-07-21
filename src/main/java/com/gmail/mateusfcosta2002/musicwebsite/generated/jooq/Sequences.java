/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Sequences {

    /**
     * The sequence <code>public.authors_seq</code>
     */
    public static final Sequence<Long> AUTHORS_SEQ = Internal.createSequence("authors_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), null, 50, null, null, false, null);

    /**
     * The sequence <code>public.musics_seq</code>
     */
    public static final Sequence<Long> MUSICS_SEQ = Internal.createSequence("musics_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), null, 50, null, null, false, null);

    /**
     * The sequence <code>public.playlists_seq</code>
     */
    public static final Sequence<Long> PLAYLISTS_SEQ = Internal.createSequence("playlists_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), null, 50, null, null, false, null);

    /**
     * The sequence <code>public.users_seq</code>
     */
    public static final Sequence<Long> USERS_SEQ = Internal.createSequence("users_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), null, 50, null, null, false, null);
}
