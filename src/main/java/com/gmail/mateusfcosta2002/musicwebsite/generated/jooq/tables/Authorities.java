/*
 * This file is generated by jOOQ.
 */
package com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables;


import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Keys;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.Public;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Users.UsersPath;
import com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.records.AuthoritiesRecord;

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
public class Authorities extends TableImpl<AuthoritiesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.authorities</code>
     */
    public static final Authorities AUTHORITIES = new Authorities();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthoritiesRecord> getRecordType() {
        return AuthoritiesRecord.class;
    }

    /**
     * The column <code>public.authorities.user_id</code>.
     */
    public final TableField<AuthoritiesRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.authorities.authority</code>.
     */
    public final TableField<AuthoritiesRecord, String> AUTHORITY = createField(DSL.name("authority"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private Authorities(Name alias, Table<AuthoritiesRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Authorities(Name alias, Table<AuthoritiesRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.authorities</code> table reference
     */
    public Authorities(String alias) {
        this(DSL.name(alias), AUTHORITIES);
    }

    /**
     * Create an aliased <code>public.authorities</code> table reference
     */
    public Authorities(Name alias) {
        this(alias, AUTHORITIES);
    }

    /**
     * Create a <code>public.authorities</code> table reference
     */
    public Authorities() {
        this(DSL.name("authorities"), null);
    }

    public <O extends Record> Authorities(Table<O> path, ForeignKey<O, AuthoritiesRecord> childPath, InverseForeignKey<O, AuthoritiesRecord> parentPath) {
        super(path, childPath, parentPath, AUTHORITIES);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class AuthoritiesPath extends Authorities implements Path<AuthoritiesRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> AuthoritiesPath(Table<O> path, ForeignKey<O, AuthoritiesRecord> childPath, InverseForeignKey<O, AuthoritiesRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private AuthoritiesPath(Name alias, Table<AuthoritiesRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public AuthoritiesPath as(String alias) {
            return new AuthoritiesPath(DSL.name(alias), this);
        }

        @Override
        public AuthoritiesPath as(Name alias) {
            return new AuthoritiesPath(alias, this);
        }

        @Override
        public AuthoritiesPath as(Table<?> alias) {
            return new AuthoritiesPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<AuthoritiesRecord> getPrimaryKey() {
        return Keys.AUTHORITIES_PKEY;
    }

    @Override
    public List<ForeignKey<AuthoritiesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.AUTHORITIES__FKK91UPMBUEYIM93V469WJ7B2QH);
    }

    private transient UsersPath _users;

    /**
     * Get the implicit join path to the <code>public.users</code> table.
     */
    public UsersPath users() {
        if (_users == null)
            _users = new UsersPath(this, Keys.AUTHORITIES__FKK91UPMBUEYIM93V469WJ7B2QH, null);

        return _users;
    }

    @Override
    public Authorities as(String alias) {
        return new Authorities(DSL.name(alias), this);
    }

    @Override
    public Authorities as(Name alias) {
        return new Authorities(alias, this);
    }

    @Override
    public Authorities as(Table<?> alias) {
        return new Authorities(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Authorities rename(String name) {
        return new Authorities(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authorities rename(Name name) {
        return new Authorities(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authorities rename(Table<?> name) {
        return new Authorities(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities where(Condition condition) {
        return new Authorities(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authorities where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authorities where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authorities where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authorities where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authorities whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
