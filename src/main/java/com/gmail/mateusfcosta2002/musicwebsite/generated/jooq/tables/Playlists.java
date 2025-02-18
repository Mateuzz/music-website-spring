/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Keys;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Public;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MusicsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.PlaylistsMusics.PlaylistsMusicsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Users.UsersPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records.PlaylistsRecord;

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
public class Playlists extends TableImpl<PlaylistsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.playlists</code>
     */
    public static final Playlists PLAYLISTS = new Playlists();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlaylistsRecord> getRecordType() {
        return PlaylistsRecord.class;
    }

    /**
     * The column <code>public.playlists.create_date</code>.
     */
    public final TableField<PlaylistsRecord, OffsetDateTime> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.playlists.created_by_id</code>.
     */
    public final TableField<PlaylistsRecord, Long> CREATED_BY_ID = createField(DSL.name("created_by_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.playlists.id</code>.
     */
    public final TableField<PlaylistsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.playlists.update_date</code>.
     */
    public final TableField<PlaylistsRecord, OffsetDateTime> UPDATE_DATE = createField(DSL.name("update_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.playlists.name</code>.
     */
    public final TableField<PlaylistsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(150).nullable(false), this, "");

    /**
     * The column <code>public.playlists.uri_image</code>.
     */
    public final TableField<PlaylistsRecord, String> URI_IMAGE = createField(DSL.name("uri_image"), SQLDataType.VARCHAR(255), this, "");

    private Playlists(Name alias, Table<PlaylistsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Playlists(Name alias, Table<PlaylistsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.playlists</code> table reference
     */
    public Playlists(String alias) {
        this(DSL.name(alias), PLAYLISTS);
    }

    /**
     * Create an aliased <code>public.playlists</code> table reference
     */
    public Playlists(Name alias) {
        this(alias, PLAYLISTS);
    }

    /**
     * Create a <code>public.playlists</code> table reference
     */
    public Playlists() {
        this(DSL.name("playlists"), null);
    }

    public <O extends Record> Playlists(Table<O> path, ForeignKey<O, PlaylistsRecord> childPath, InverseForeignKey<O, PlaylistsRecord> parentPath) {
        super(path, childPath, parentPath, PLAYLISTS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PlaylistsPath extends Playlists implements Path<PlaylistsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> PlaylistsPath(Table<O> path, ForeignKey<O, PlaylistsRecord> childPath, InverseForeignKey<O, PlaylistsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private PlaylistsPath(Name alias, Table<PlaylistsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PlaylistsPath as(String alias) {
            return new PlaylistsPath(DSL.name(alias), this);
        }

        @Override
        public PlaylistsPath as(Name alias) {
            return new PlaylistsPath(alias, this);
        }

        @Override
        public PlaylistsPath as(Table<?> alias) {
            return new PlaylistsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<PlaylistsRecord> getPrimaryKey() {
        return Keys.PLAYLISTS_PKEY;
    }

    @Override
    public List<ForeignKey<PlaylistsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PLAYLISTS__FKKW37PIXQVAP2UC094O95J8LU7);
    }

    private transient UsersPath _users;

    /**
     * Get the implicit join path to the <code>public.users</code> table.
     */
    public UsersPath users() {
        if (_users == null)
            _users = new UsersPath(this, Keys.PLAYLISTS__FKKW37PIXQVAP2UC094O95J8LU7, null);

        return _users;
    }

    private transient PlaylistsMusicsPath _playlistsMusics;

    /**
     * Get the implicit to-many join path to the
     * <code>public.playlists_musics</code> table
     */
    public PlaylistsMusicsPath playlistsMusics() {
        if (_playlistsMusics == null)
            _playlistsMusics = new PlaylistsMusicsPath(this, null, Keys.PLAYLISTS_MUSICS__FK1558UIAUM4VTXIKOM9IRUBGC8.getInverseKey());

        return _playlistsMusics;
    }

    /**
     * Get the implicit many-to-many join path to the <code>public.musics</code>
     * table
     */
    public MusicsPath musics() {
        return playlistsMusics().musics();
    }

    @Override
    public Playlists as(String alias) {
        return new Playlists(DSL.name(alias), this);
    }

    @Override
    public Playlists as(Name alias) {
        return new Playlists(alias, this);
    }

    @Override
    public Playlists as(Table<?> alias) {
        return new Playlists(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Playlists rename(String name) {
        return new Playlists(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Playlists rename(Name name) {
        return new Playlists(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Playlists rename(Table<?> name) {
        return new Playlists(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists where(Condition condition) {
        return new Playlists(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Playlists where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Playlists where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Playlists where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Playlists where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Playlists whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
