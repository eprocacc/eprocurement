package com.dxc.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SupplierNominee entity.
 */
public class SupplierNomineeDTO implements Serializable {

    private Long id;

    private String name;

    private Boolean registered;

    private String certChain;

    private Long supplierId;

    private Long nomineesToSupplierId;

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

    public Boolean isRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getCertChain() {
        return certChain;
    }

    public void setCertChain(String certChain) {
        this.certChain = certChain;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getNomineesToSupplierId() {
        return nomineesToSupplierId;
    }

    public void setNomineesToSupplierId(Long supplierId) {
        this.nomineesToSupplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierNomineeDTO supplierNomineeDTO = (SupplierNomineeDTO) o;
        if(supplierNomineeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierNomineeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierNomineeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", registered='" + isRegistered() + "'" +
            ", certChain='" + getCertChain() + "'" +
            "}";
    }
}
