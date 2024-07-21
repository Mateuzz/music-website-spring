/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Keys;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Public;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MusicsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.MusicsTags.MusicsTagsPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records.TagsRecord;

import java.util.Collection;

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
public class Tags extends TableImpl<TagsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.tags</code>
     */
    public static final Tags TAGS = new Tags();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TagsRecord> getRecordType() {
        return TagsRecord.class;
    }

    /**
     * The column <code>public.tags.name</code>.
     */
    public final TableField<TagsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private Tags(Name alias, Table<TagsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Tags(Name alias, Table<TagsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.tags</code> table reference
     */
    public Tags(String alias) {
        this(DSL.name(alias), TAGS);
    }

    /**
     * Create an aliased <code>public.tags</code> table reference
     */
    public Tags(Name alias) {
        this(alias, TAGS);
    }

    /**
     * Create a <code>public.tags</code> table reference
     */
    public Tags() {
        this(DSL.name("tags"), null);
    }

    public <O extends Record> Tags(Table<O> path, ForeignKey<O, TagsRecord> childPath, InverseForeignKey<O, TagsRecord> parentPath) {
        super(path, childPath, parentPath, TAGS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TagsPath extends Tags implements Path<TagsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TagsPath(Table<O> path, ForeignKey<O, TagsRecord> childPath, InverseForeignKey<O, TagsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TagsPath(Name alias, Table<TagsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TagsPath as(String alias) {
            return new TagsPath(DSL.name(alias), this);
        }

        @Override
        public TagsPath as(Name alias) {
            return new TagsPath(alias, this);
        }

        @Override
        public TagsPath as(Table<?> alias) {
            return new TagsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<TagsRecord> getPrimaryKey() {
        return Keys.TAGS_PKEY;
    }

    private transient MusicsTagsPath _musicsTags;

    /**
     * Get the implicit to-many join path to the <code>public.musics_tags</code>
     * table
     */
    public MusicsTagsPath musicsTags() {
        if (_musicsTags == null)
            _musicsTags = new MusicsTagsPath(this, null, Keys.MUSICS_TAGS__FKODMHVTTRIX1AS8030G35KT6LG.getInverseKey());

        return _musicsTags;
    }

    /**
     * Get the implicit many-to-many join path to the <code>public.musics</code>
     * table
     */
    public MusicsPath musics() {
        return musicsTags().musics();
    }

    @Override
    public Tags as(String alias) {
        return new Tags(DSL.name(alias), this);
    }

    @Override
    public Tags as(Name alias) {
        return new Tags(alias, this);
    }

    @Override
    public Tags as(Table<?> alias) {
        return new Tags(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Tags rename(String name) {
        return new Tags(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tags rename(Name name) {
        return new Tags(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tags rename(Table<?> name) {
        return new Tags(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags where(Condition condition) {
        return new Tags(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tags where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tags where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tags where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tags where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tags whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
