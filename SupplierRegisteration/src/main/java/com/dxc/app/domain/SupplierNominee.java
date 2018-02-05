package com.dxc.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SupplierNominee.
 */
@Entity
@Table(name = "supplier_nominee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SupplierNominee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "registered")
    private Boolean registered;

    @Column(name = "cert_chain")
    private String certChain;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Supplier nomineesToSupplier;

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

    public SupplierNominee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRegistered() {
        return registered;
    }

    public SupplierNominee registered(Boolean registered) {
        this.registered = registered;
        return this;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getCertChain() {
        return certChain;
    }

    public SupplierNominee certChain(String certChain) {
        this.certChain = certChain;
        return this;
    }

    public void setCertChain(String certChain) {
        this.certChain = certChain;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public SupplierNominee supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getNomineesToSupplier() {
        return nomineesToSupplier;
    }

    public SupplierNominee nomineesToSupplier(Supplier supplier) {
        this.nomineesToSupplier = supplier;
        return this;
    }

    public void setNomineesToSupplier(Supplier supplier) {
        this.nomineesToSupplier = supplier;
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
        SupplierNominee supplierNominee = (SupplierNominee) o;
        if (supplierNominee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierNominee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierNominee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", registered='" + isRegistered() + "'" +
            ", certChain='" + getCertChain() + "'" +
            "}";
    }
}
