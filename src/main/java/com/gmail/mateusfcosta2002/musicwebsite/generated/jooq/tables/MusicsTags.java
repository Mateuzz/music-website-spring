/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Keys;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Public;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MusicsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Tags.TagsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records.MusicsTagsRecord;

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
public class MusicsTags extends TableImpl<MusicsTagsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.musics_tags</code>
     */
    public static final MusicsTags MUSICS_TAGS = new MusicsTags();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MusicsTagsRecord> getRecordType() {
        return MusicsTagsRecord.class;
    }

    /**
     * The column <code>public.musics_tags.musics_id</code>.
     */
    public final TableField<MusicsTagsRecord, Long> MUSICS_ID = createField(DSL.name("musics_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.musics_tags.tags_name</code>.
     */
    public final TableField<MusicsTagsRecord, String> TAGS_NAME = createField(DSL.name("tags_name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private MusicsTags(Name alias, Table<MusicsTagsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private MusicsTags(Name alias, Table<MusicsTagsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.musics_tags</code> table reference
     */
    public MusicsTags(String alias) {
        this(DSL.name(alias), MUSICS_TAGS);
    }

    /**
     * Create an aliased <code>public.musics_tags</code> table reference
     */
    public MusicsTags(Name alias) {
        this(alias, MUSICS_TAGS);
    }

    /**
     * Create a <code>public.musics_tags</code> table reference
     */
    public MusicsTags() {
        this(DSL.name("musics_tags"), null);
    }

    public <O extends Record> MusicsTags(Table<O> path, ForeignKey<O, MusicsTagsRecord> childPath, InverseForeignKey<O, MusicsTagsRecord> parentPath) {
        super(path, childPath, parentPath, MUSICS_TAGS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class MusicsTagsPath extends MusicsTags implements Path<MusicsTagsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> MusicsTagsPath(Table<O> path, ForeignKey<O, MusicsTagsRecord> childPath, InverseForeignKey<O, MusicsTagsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private MusicsTagsPath(Name alias, Table<MusicsTagsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public MusicsTagsPath as(String alias) {
            return new MusicsTagsPath(DSL.name(alias), this);
        }

        @Override
        public MusicsTagsPath as(Name alias) {
            return new MusicsTagsPath(alias, this);
        }

        @Override
        public MusicsTagsPath as(Table<?> alias) {
            return new MusicsTagsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<MusicsTagsRecord> getPrimaryKey() {
        return Keys.MUSICS_TAGS_PKEY;
    }

    @Override
    public List<ForeignKey<MusicsTagsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MUSICS_TAGS__FKQ50LFT5MGEJQTJ0MTH63TALPP, Keys.MUSICS_TAGS__FKODMHVTTRIX1AS8030G35KT6LG);
    }

    private transient MusicsPath _musics;

    /**
     * Get the implicit join path to the <code>public.musics</code> table.
     */
    public MusicsPath musics() {
        if (_musics == null)
            _musics = new MusicsPath(this, Keys.MUSICS_TAGS__FKQ50LFT5MGEJQTJ0MTH63TALPP, null);

        return _musics;
    }

    private transient TagsPath _tags;

    /**
     * Get the implicit join path to the <code>public.tags</code> table.
     */
    public TagsPath tags() {
        if (_tags == null)
            _tags = new TagsPath(this, Keys.MUSICS_TAGS__FKODMHVTTRIX1AS8030G35KT6LG, null);

        return _tags;
    }

    @Override
    public MusicsTags as(String alias) {
        return new MusicsTags(DSL.name(alias), this);
    }

    @Override
    public MusicsTags as(Name alias) {
        return new MusicsTags(alias, this);
    }

    @Override
    public MusicsTags as(Table<?> alias) {
        return new MusicsTags(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public MusicsTags rename(String name) {
        return new MusicsTags(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MusicsTags rename(Name name) {
        return new MusicsTags(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public MusicsTags rename(Table<?> name) {
        return new MusicsTags(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags where(Condition condition) {
        return new MusicsTags(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public MusicsTags where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public MusicsTags where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public MusicsTags where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public MusicsTags where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public MusicsTags whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
