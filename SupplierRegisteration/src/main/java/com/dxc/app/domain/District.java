package com.dxc.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A District.
 */
@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Taluq> districtTaluqs = new HashSet<>();

    @ManyToOne
    private State districtsState;

    @ManyToOne
    private State state;

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

    public District name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Taluq> getDistrictTaluqs() {
        return districtTaluqs;
    }

    public District districtTaluqs(Set<Taluq> taluqs) {
        this.districtTaluqs = taluqs;
        return this;
    }

    public District addDistrictTaluqs(Taluq taluq) {
        this.districtTaluqs.add(taluq);
        taluq.setDistrict(this);
        return this;
    }

    public District removeDistrictTaluqs(Taluq taluq) {
        this.districtTaluqs.remove(taluq);
        taluq.setDistrict(null);
        return this;
    }

    public void setDistrictTaluqs(Set<Taluq> taluqs) {
        this.districtTaluqs = taluqs;
    }

    public State getDistrictsState() {
        return districtsState;
    }

    public District districtsState(State state) {
        this.districtsState = state;
        return this;
    }

    public void setDistrictsState(State state) {
        this.districtsState = state;
    }

    public State getState() {
        return state;
    }

    public District state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
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
        District district = (District) o;
        if (district.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), district.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "District{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
