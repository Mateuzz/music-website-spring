/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics;

import java.time.OffsetDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class MusicsRecord extends UpdatableRecordImpl<MusicsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.musics.views_count</code>.
     */
    public void setViewsCount(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.musics.views_count</code>.
     */
    public Integer getViewsCount() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.musics.author_id</code>.
     */
    public void setAuthorId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.musics.author_id</code>.
     */
    public Long getAuthorId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.musics.category_id</code>.
     */
    public void setCategoryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.musics.category_id</code>.
     */
    public Long getCategoryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.musics.create_date</code>.
     */
    public void setCreateDate(OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.musics.create_date</code>.
     */
    public OffsetDateTime getCreateDate() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for <code>public.musics.id</code>.
     */
    public void setId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.musics.id</code>.
     */
    public Long getId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.musics.update_date</code>.
     */
    public void setUpdateDate(OffsetDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.musics.update_date</code>.
     */
    public OffsetDateTime getUpdateDate() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>public.musics.name</code>.
     */
    public void setName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.musics.name</code>.
     */
    public String getName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.musics.filepath</code>.
     */
    public void setFilepath(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.musics.filepath</code>.
     */
    public String getFilepath() {
        return (String) get(7);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public void setSearch(Object value) {
        set(8, value);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getSearch() {
        return get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MusicsRecord
     */
    public MusicsRecord() {
        super(Musics.MUSICS);
    }

    /**
     * Create a detached, initialised MusicsRecord
     */
    public MusicsRecord(Integer viewsCount, Long authorId, Long categoryId, OffsetDateTime createDate, Long id, OffsetDateTime updateDate, String name, String filepath, Object search) {
        super(Musics.MUSICS);

        setViewsCount(viewsCount);
        setAuthorId(authorId);
        setCategoryId(categoryId);
        setCreateDate(createDate);
        setId(id);
        setUpdateDate(updateDate);
        setName(name);
        setFilepath(filepath);
        setSearch(search);
        resetChangedOnNotNull();
    }
}
