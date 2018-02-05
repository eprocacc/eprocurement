package com.dxc.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Address entity.
 */
public class AddressDTO implements Serializable {

    private Long id;

    private String blockNumber;

    private String street;

    private String city;

    private String otherState;

    private String pin;

    private Integer countryId;

    private Integer stateId;

    private Long addressStateId;

    private Long addressCountryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOtherState() {
        return otherState;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Long getAddressStateId() {
        return addressStateId;
    }

    public void setAddressStateId(Long stateId) {
        this.addressStateId = stateId;
    }

    public Long getAddressCountryId() {
        return addressCountryId;
    }

    public void setAddressCountryId(Long countryId) {
        this.addressCountryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDTO addressDTO = (AddressDTO) o;
        if(addressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
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
