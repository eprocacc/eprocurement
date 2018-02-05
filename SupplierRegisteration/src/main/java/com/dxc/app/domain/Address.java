package com.dxc.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block_number")
    private String blockNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "other_state")
    private String otherState;

    @Column(name = "pin")
    private String pin;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "state_id")
    private Integer stateId;

    @ManyToOne
    private State addressState;

    @ManyToOne
    private Country addressCountry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public Address blockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
        return this;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOtherState() {
        return otherState;
    }

    public Address otherState(String otherState) {
        this.otherState = otherState;
        return this;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public String getPin() {
        return pin;
    }

    public Address pin(String pin) {
        this.pin = pin;
        return this;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public Address countryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public Address stateId(Integer stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public State getAddressState() {
        return addressState;
    }

    public Address addressState(State state) {
        this.addressState = state;
        return this;
    }

    public void setAddressState(State state) {
        this.addressState = state;
    }

    public Country getAddressCountry() {
        return addressCountry;
    }

    public Address addressCountry(Country country) {
        this.addressCountry = country;
        return this;
    }

    public void setAddressCountry(Country country) {
        this.addressCountry = country;
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
        Address address = (Address) o;
        if (address.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", blockNumber='" + getBlockNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", otherState='" + getOtherState() + "'" +
            ", pin='" + getPin() + "'" +
            ", countryId=" + getCountryId() +
            ", stateId=" + getStateId() +
            "}";
    }
}
