/*
 * This file is generated by jOOQ.
 */
package me.fagiolini.cinemapp.db.tables.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Entity
@Table(
    name = "proiezione",
    schema = "cinemapp",
    uniqueConstraints = {
        @UniqueConstraint(name = "proiezione_data_ora_inizio_data_ora_fine_sala_id_key", columnNames = { "data_ora_inizio", "data_ora_fine", "sala_id" })
    }
)
public class Proiezione implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime dataOraFine;
    private LocalDateTime dataOraInizio;
    private Double prezzo;
    private Long filmId;
    private Long salaId;

    public Proiezione() {}

    public Proiezione(Proiezione value) {
        this.id = value.id;
        this.dataOraFine = value.dataOraFine;
        this.dataOraInizio = value.dataOraInizio;
        this.prezzo = value.prezzo;
        this.filmId = value.filmId;
        this.salaId = value.salaId;
    }

    public Proiezione(
        Long id,
        LocalDateTime dataOraFine,
        LocalDateTime dataOraInizio,
        Double prezzo,
        Long filmId,
        Long salaId
    ) {
        this.id = id;
        this.dataOraFine = dataOraFine;
        this.dataOraInizio = dataOraInizio;
        this.prezzo = prezzo;
        this.filmId = filmId;
        this.salaId = salaId;
    }

    /**
     * Getter for <code>cinemapp.proiezione.id</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>cinemapp.proiezione.id</code>.
     */
    public Proiezione setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>cinemapp.proiezione.data_ora_fine</code>.
     */
    @Column(name = "data_ora_fine", nullable = false, precision = 6)
    @NotNull
    public LocalDateTime getDataOraFine() {
        return this.dataOraFine;
    }

    /**
     * Setter for <code>cinemapp.proiezione.data_ora_fine</code>.
     */
    public Proiezione setDataOraFine(LocalDateTime dataOraFine) {
        this.dataOraFine = dataOraFine;
        return this;
    }

    /**
     * Getter for <code>cinemapp.proiezione.data_ora_inizio</code>.
     */
    @Column(name = "data_ora_inizio", nullable = false, precision = 6)
    @NotNull
    public LocalDateTime getDataOraInizio() {
        return this.dataOraInizio;
    }

    /**
     * Setter for <code>cinemapp.proiezione.data_ora_inizio</code>.
     */
    public Proiezione setDataOraInizio(LocalDateTime dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
        return this;
    }

    /**
     * Getter for <code>cinemapp.proiezione.prezzo</code>.
     */
    @Column(name = "prezzo")
    public Double getPrezzo() {
        return this.prezzo;
    }

    /**
     * Setter for <code>cinemapp.proiezione.prezzo</code>.
     */
    public Proiezione setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    /**
     * Getter for <code>cinemapp.proiezione.film_id</code>.
     */
    @Column(name = "film_id", nullable = false)
    @NotNull
    public Long getFilmId() {
        return this.filmId;
    }

    /**
     * Setter for <code>cinemapp.proiezione.film_id</code>.
     */
    public Proiezione setFilmId(Long filmId) {
        this.filmId = filmId;
        return this;
    }

    /**
     * Getter for <code>cinemapp.proiezione.sala_id</code>.
     */
    @Column(name = "sala_id", nullable = false)
    @NotNull
    public Long getSalaId() {
        return this.salaId;
    }

    /**
     * Setter for <code>cinemapp.proiezione.sala_id</code>.
     */
    public Proiezione setSalaId(Long salaId) {
        this.salaId = salaId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Proiezione other = (Proiezione) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.dataOraFine == null) {
            if (other.dataOraFine != null)
                return false;
        }
        else if (!this.dataOraFine.equals(other.dataOraFine))
            return false;
        if (this.dataOraInizio == null) {
            if (other.dataOraInizio != null)
                return false;
        }
        else if (!this.dataOraInizio.equals(other.dataOraInizio))
            return false;
        if (this.prezzo == null) {
            if (other.prezzo != null)
                return false;
        }
        else if (!this.prezzo.equals(other.prezzo))
            return false;
        if (this.filmId == null) {
            if (other.filmId != null)
                return false;
        }
        else if (!this.filmId.equals(other.filmId))
            return false;
        if (this.salaId == null) {
            if (other.salaId != null)
                return false;
        }
        else if (!this.salaId.equals(other.salaId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.dataOraFine == null) ? 0 : this.dataOraFine.hashCode());
        result = prime * result + ((this.dataOraInizio == null) ? 0 : this.dataOraInizio.hashCode());
        result = prime * result + ((this.prezzo == null) ? 0 : this.prezzo.hashCode());
        result = prime * result + ((this.filmId == null) ? 0 : this.filmId.hashCode());
        result = prime * result + ((this.salaId == null) ? 0 : this.salaId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Proiezione (");

        sb.append(id);
        sb.append(", ").append(dataOraFine);
        sb.append(", ").append(dataOraInizio);
        sb.append(", ").append(prezzo);
        sb.append(", ").append(filmId);
        sb.append(", ").append(salaId);

        sb.append(")");
        return sb.toString();
    }
}
