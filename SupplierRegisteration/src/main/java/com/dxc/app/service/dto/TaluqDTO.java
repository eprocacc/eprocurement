package com.dxc.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Taluq entity.
 */
public class TaluqDTO implements Serializable {

    private Long id;

    private String name;

    private Long taluqsDistrictId;

    private Long districtId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTaluqsDistrictId() {
        return taluqsDistrictId;
    }

    public void setTaluqsDistrictId(Long districtId) {
        this.taluqsDistrictId = districtId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaluqDTO taluqDTO = (TaluqDTO) o;
        if(taluqDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taluqDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaluqDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
