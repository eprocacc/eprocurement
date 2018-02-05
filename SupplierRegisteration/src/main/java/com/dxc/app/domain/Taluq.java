package com.dxc.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Taluq.
 */
@Entity
@Table(name = "taluq")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Taluq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private District taluqsDistrict;

    @ManyToOne
    private District district;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Taluq name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getTaluqsDistrict() {
        return taluqsDistrict;
    }

    public Taluq taluqsDistrict(District district) {
        this.taluqsDistrict = district;
        return this;
    }

    public void setTaluqsDistrict(District district) {
        this.taluqsDistrict = district;
    }

    public District getDistrict() {
        return district;
    }

    public Taluq district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Taluq taluq = (Taluq) o;
        if (taluq.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taluq.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Taluq{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
