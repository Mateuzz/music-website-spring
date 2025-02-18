/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Keys;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Public;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MusicsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Playlists.PlaylistsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records.PlaylistsMusicsRecord;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PlaylistsMusics extends TableImpl<PlaylistsMusicsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.playlists_musics</code>
     */
    public static final PlaylistsMusics PLAYLISTS_MUSICS = new PlaylistsMusics();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlaylistsMusicsRecord> getRecordType() {
        return PlaylistsMusicsRecord.class;
    }

    /**
     * The column <code>public.playlists_musics.musics_id</code>.
     */
    public final TableField<PlaylistsMusicsRecord, Long> MUSICS_ID = createField(DSL.name("musics_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.playlists_musics.playlist_id</code>.
     */
    public final TableField<PlaylistsMusicsRecord, Long> PLAYLIST_ID = createField(DSL.name("playlist_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.playlists_musics.added_date</code>.
     */
    public final TableField<PlaylistsMusicsRecord, OffsetDateTime> ADDED_DATE = createField(DSL.name("added_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    private PlaylistsMusics(Name alias, Table<PlaylistsMusicsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PlaylistsMusics(Name alias, Table<PlaylistsMusicsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.playlists_musics</code> table reference
     */
    public PlaylistsMusics(String alias) {
        this(DSL.name(alias), PLAYLISTS_MUSICS);
    }

    /**
     * Create an aliased <code>public.playlists_musics</code> table reference
     */
    public PlaylistsMusics(Name alias) {
        this(alias, PLAYLISTS_MUSICS);
    }

    /**
     * Create a <code>public.playlists_musics</code> table reference
     */
    public PlaylistsMusics() {
        this(DSL.name("playlists_musics"), null);
    }

    public <O extends Record> PlaylistsMusics(Table<O> path, ForeignKey<O, PlaylistsMusicsRecord> childPath, InverseForeignKey<O, PlaylistsMusicsRecord> parentPath) {
        super(path, childPath, parentPath, PLAYLISTS_MUSICS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PlaylistsMusicsPath extends PlaylistsMusics implements Path<PlaylistsMusicsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> PlaylistsMusicsPath(Table<O> path, ForeignKey<O, PlaylistsMusicsRecord> childPath, InverseForeignKey<O, PlaylistsMusicsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private PlaylistsMusicsPath(Name alias, Table<PlaylistsMusicsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PlaylistsMusicsPath as(String alias) {
            return new PlaylistsMusicsPath(DSL.name(alias), this);
        }

        @Override
        public PlaylistsMusicsPath as(Name alias) {
            return new PlaylistsMusicsPath(alias, this);
        }

        @Override
        public PlaylistsMusicsPath as(Table<?> alias) {
            return new PlaylistsMusicsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<PlaylistsMusicsRecord> getPrimaryKey() {
        return Keys.PLAYLISTS_MUSICS_PKEY;
    }

    @Override
    public List<ForeignKey<PlaylistsMusicsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PLAYLISTS_MUSICS__FKCKS57CNN25VMIOLLWS9LUORYO, Keys.PLAYLISTS_MUSICS__FK1558UIAUM4VTXIKOM9IRUBGC8);
    }

    private transient MusicsPath _musics;

    /**
     * Get the implicit join path to the <code>public.musics</code> table.
     */
    public MusicsPath musics() {
        if (_musics == null)
            _musics = new MusicsPath(this, Keys.PLAYLISTS_MUSICS__FKCKS57CNN25VMIOLLWS9LUORYO, null);

        return _musics;
    }

    private transient PlaylistsPath _playlists;

    /**
     * Get the implicit join path to the <code>public.playlists</code> table.
     */
    public PlaylistsPath playlists() {
        if (_playlists == null)
            _playlists = new PlaylistsPath(this, Keys.PLAYLISTS_MUSICS__FK1558UIAUM4VTXIKOM9IRUBGC8, null);

        return _playlists;
    }

    @Override
    public PlaylistsMusics as(String alias) {
        return new PlaylistsMusics(DSL.name(alias), this);
    }

    @Override
    public PlaylistsMusics as(Name alias) {
        return new PlaylistsMusics(alias, this);
    }

    @Override
    public PlaylistsMusics as(Table<?> alias) {
        return new PlaylistsMusics(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PlaylistsMusics rename(String name) {
        return new PlaylistsMusics(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlaylistsMusics rename(Name name) {
        return new PlaylistsMusics(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlaylistsMusics rename(Table<?> name) {
        return new PlaylistsMusics(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics where(Condition condition) {
        return new PlaylistsMusics(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PlaylistsMusics where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PlaylistsMusics where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PlaylistsMusics where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PlaylistsMusics where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PlaylistsMusics whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
