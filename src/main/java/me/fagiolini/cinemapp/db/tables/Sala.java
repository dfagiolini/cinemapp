/*
 * This file is generated by jOOQ.
 */
package me.fagiolini.cinemapp.db.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import me.fagiolini.cinemapp.db.Cinemapp;
import me.fagiolini.cinemapp.db.Keys;
import me.fagiolini.cinemapp.db.tables.Cinema.CinemaPath;
import me.fagiolini.cinemapp.db.tables.Proiezione.ProiezionePath;
import me.fagiolini.cinemapp.db.tables.records.SalaRecord;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Sala extends TableImpl<SalaRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cinemapp.sala</code>
     */
    public static final Sala SALA = new Sala();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SalaRecord> getRecordType() {
        return SalaRecord.class;
    }

    /**
     * The column <code>cinemapp.sala.id</code>.
     */
    public final TableField<SalaRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>cinemapp.sala.cinema_id</code>.
     */
    public final TableField<SalaRecord, Long> CINEMA_ID = createField(DSL.name("cinema_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>cinemapp.sala.capacita</code>.
     */
    public final TableField<SalaRecord, Integer> CAPACITA = createField(DSL.name("capacita"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>cinemapp.sala.numero</code>.
     */
    public final TableField<SalaRecord, String> NUMERO = createField(DSL.name("numero"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Sala(Name alias, Table<SalaRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Sala(Name alias, Table<SalaRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>cinemapp.sala</code> table reference
     */
    public Sala(String alias) {
        this(DSL.name(alias), SALA);
    }

    /**
     * Create an aliased <code>cinemapp.sala</code> table reference
     */
    public Sala(Name alias) {
        this(alias, SALA);
    }

    /**
     * Create a <code>cinemapp.sala</code> table reference
     */
    public Sala() {
        this(DSL.name("sala"), null);
    }

    public <O extends Record> Sala(Table<O> path, ForeignKey<O, SalaRecord> childPath, InverseForeignKey<O, SalaRecord> parentPath) {
        super(path, childPath, parentPath, SALA);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SalaPath extends Sala implements Path<SalaRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SalaPath(Table<O> path, ForeignKey<O, SalaRecord> childPath, InverseForeignKey<O, SalaRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SalaPath(Name alias, Table<SalaRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SalaPath as(String alias) {
            return new SalaPath(DSL.name(alias), this);
        }

        @Override
        public SalaPath as(Name alias) {
            return new SalaPath(alias, this);
        }

        @Override
        public SalaPath as(Table<?> alias) {
            return new SalaPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Cinemapp.CINEMAPP;
    }

    @Override
    public Identity<SalaRecord, Long> getIdentity() {
        return (Identity<SalaRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SalaRecord> getPrimaryKey() {
        return Keys.SALA_PKEY;
    }

    @Override
    public List<UniqueKey<SalaRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.SALA_CINEMA_ID_NUMERO_KEY);
    }

    @Override
    public List<ForeignKey<SalaRecord, ?>> getReferences() {
        return Arrays.asList(Keys.SALA__SALA_CINEMA_ID_FKEY);
    }

    private transient CinemaPath _cinema;

    /**
     * Get the implicit join path to the <code>cinemapp.cinema</code> table.
     */
    public CinemaPath cinema() {
        if (_cinema == null)
            _cinema = new CinemaPath(this, Keys.SALA__SALA_CINEMA_ID_FKEY, null);

        return _cinema;
    }

    private transient ProiezionePath _proiezione;

    /**
     * Get the implicit to-many join path to the
     * <code>cinemapp.proiezione</code> table
     */
    public ProiezionePath proiezione() {
        if (_proiezione == null)
            _proiezione = new ProiezionePath(this, null, Keys.PROIEZIONE__PROIEZIONE_SALA_ID_FKEY.getInverseKey());

        return _proiezione;
    }

    @Override
    public Sala as(String alias) {
        return new Sala(DSL.name(alias), this);
    }

    @Override
    public Sala as(Name alias) {
        return new Sala(alias, this);
    }

    @Override
    public Sala as(Table<?> alias) {
        return new Sala(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Sala rename(String name) {
        return new Sala(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sala rename(Name name) {
        return new Sala(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sala rename(Table<?> name) {
        return new Sala(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala where(Condition condition) {
        return new Sala(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Sala where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Sala where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Sala where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Sala where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Sala whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
