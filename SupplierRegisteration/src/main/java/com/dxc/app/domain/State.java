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
 * A State.
 */
@Entity
@Table(name = "state")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<District> stateDistricts = new HashSet<>();

    @ManyToOne
    private Country statesCountry;

    @ManyToOne
    private Country country;

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

    public State name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<District> getStateDistricts() {
        return stateDistricts;
    }

    public State stateDistricts(Set<District> districts) {
        this.stateDistricts = districts;
        return this;
    }

    public State addStateDistricts(District district) {
        this.stateDistricts.add(district);
        district.setState(this);
        return this;
    }

    public State removeStateDistricts(District district) {
        this.stateDistricts.remove(district);
        district.setState(null);
        return this;
    }

    public void setStateDistricts(Set<District> districts) {
        this.stateDistricts = districts;
    }

    public Country getStatesCountry() {
        return statesCountry;
    }

    public State statesCountry(Country country) {
        this.statesCountry = country;
        return this;
    }

    public void setStatesCountry(Country country) {
        this.statesCountry = country;
    }

    public Country getCountry() {
        return country;
    }

    public State country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        State state = (State) o;
        if (state.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), state.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
