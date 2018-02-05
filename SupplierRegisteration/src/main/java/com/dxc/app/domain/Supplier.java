package com.dxc.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.dxc.app.domain.enumeration.SocialStatus;

import com.dxc.app.domain.enumeration.SupplierStatus;

/**
 * A Supplier.
 */
@Entity
@Table(name = "supplier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicant_type")
    private String applicantType;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_status")
    private SocialStatus socialStatus;

    @Column(name = "designation")
    private String designation;

    @Column(name = "regn_number")
    private String regnNumber;

    @Column(name = "pan")
    private String pan;

    @Column(name = "pan_name")
    private String panName;

    @Column(name = "gross_turn_over")
    private Double grossTurnOver;

    @Enumerated(EnumType.STRING)
    @Column(name = "supplier_status")
    private SupplierStatus supplierStatus;

    @Column(name = "ssi_yn")
    private Boolean ssiYn;

    @Column(name = "ssi_regn_number")
    private String ssiRegnNumber;

    @Column(name = "pan_status")
    private Boolean panStatus;

    @Column(name = "msme_yn")
    private Boolean msmeYn;

    @Column(name = "msme_regn_number")
    private String msmeRegnNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Address supplierAddress;

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SupplierNominee> supplierToNominees = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SupplierGeneralInfo> supplierToGeneralInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantType() {
        return applicantType;
    }

    public Supplier applicantType(String applicantType) {
        this.applicantType = applicantType;
        return this;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType;
    }

    public String getType() {
        return type;
    }

    public Supplier type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Supplier name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public Supplier firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Supplier middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Supplier lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public Supplier socialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
        return this;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getDesignation() {
        return designation;
    }

    public Supplier designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRegnNumber() {
        return regnNumber;
    }

    public Supplier regnNumber(String regnNumber) {
        this.regnNumber = regnNumber;
        return this;
    }

    public void setRegnNumber(String regnNumber) {
        this.regnNumber = regnNumber;
    }

    public String getPan() {
        return pan;
    }

    public Supplier pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanName() {
        return panName;
    }

    public Supplier panName(String panName) {
        this.panName = panName;
        return this;
    }

    public void setPanName(String panName) {
        this.panName = panName;
    }

    public Double getGrossTurnOver() {
        return grossTurnOver;
    }

    public Supplier grossTurnOver(Double grossTurnOver) {
        this.grossTurnOver = grossTurnOver;
        return this;
    }

    public void setGrossTurnOver(Double grossTurnOver) {
        this.grossTurnOver = grossTurnOver;
    }

    public SupplierStatus getSupplierStatus() {
        return supplierStatus;
    }

    public Supplier supplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
        return this;
    }

    public void setSupplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public Boolean isSsiYn() {
        return ssiYn;
    }

    public Supplier ssiYn(Boolean ssiYn) {
        this.ssiYn = ssiYn;
        return this;
    }

    public void setSsiYn(Boolean ssiYn) {
        this.ssiYn = ssiYn;
    }

    public String getSsiRegnNumber() {
        return ssiRegnNumber;
    }

    public Supplier ssiRegnNumber(String ssiRegnNumber) {
        this.ssiRegnNumber = ssiRegnNumber;
        return this;
    }

    public void setSsiRegnNumber(String ssiRegnNumber) {
        this.ssiRegnNumber = ssiRegnNumber;
    }

    public Boolean isPanStatus() {
        return panStatus;
    }

    public Supplier panStatus(Boolean panStatus) {
        this.panStatus = panStatus;
        return this;
    }

    public void setPanStatus(Boolean panStatus) {
        this.panStatus = panStatus;
    }

    public Boolean isMsmeYn() {
        return msmeYn;
    }

    public Supplier msmeYn(Boolean msmeYn) {
        this.msmeYn = msmeYn;
        return this;
    }

    public void setMsmeYn(Boolean msmeYn) {
        this.msmeYn = msmeYn;
    }

    public String getMsmeRegnNumber() {
        return msmeRegnNumber;
    }

    public Supplier msmeRegnNumber(String msmeRegnNumber) {
        this.msmeRegnNumber = msmeRegnNumber;
        return this;
    }

    public void setMsmeRegnNumber(String msmeRegnNumber) {
        this.msmeRegnNumber = msmeRegnNumber;
    }

    public Address getSupplierAddress() {
        return supplierAddress;
    }

    public Supplier supplierAddress(Address address) {
        this.supplierAddress = address;
        return this;
    }

    public void setSupplierAddress(Address address) {
        this.supplierAddress = address;
    }

    public Set<SupplierNominee> getSupplierToNominees() {
        return supplierToNominees;
    }

    public Supplier supplierToNominees(Set<SupplierNominee> supplierNominees) {
        this.supplierToNominees = supplierNominees;
        return this;
    }

    public Supplier addSupplierToNominees(SupplierNominee supplierNominee) {
        this.supplierToNominees.add(supplierNominee);
        supplierNominee.setSupplier(this);
        return this;
    }

    public Supplier removeSupplierToNominees(SupplierNominee supplierNominee) {
        this.supplierToNominees.remove(supplierNominee);
        supplierNominee.setSupplier(null);
        return this;
    }

    public void setSupplierToNominees(Set<SupplierNominee> supplierNominees) {
        this.supplierToNominees = supplierNominees;
    }

    public Set<SupplierGeneralInfo> getSupplierToGeneralInfos() {
        return supplierToGeneralInfos;
    }

    public Supplier supplierToGeneralInfos(Set<SupplierGeneralInfo> supplierGeneralInfos) {
        this.supplierToGeneralInfos = supplierGeneralInfos;
        return this;
    }

    public Supplier addSupplierToGeneralInfos(SupplierGeneralInfo supplierGeneralInfo) {
        this.supplierToGeneralInfos.add(supplierGeneralInfo);
        supplierGeneralInfo.setSupplier(this);
        return this;
    }

    public Supplier removeSupplierToGeneralInfos(SupplierGeneralInfo supplierGeneralInfo) {
        this.supplierToGeneralInfos.remove(supplierGeneralInfo);
        supplierGeneralInfo.setSupplier(null);
        return this;
    }

    public void setSupplierToGeneralInfos(Set<SupplierGeneralInfo> supplierGeneralInfos) {
        this.supplierToGeneralInfos = supplierGeneralInfos;
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
        Supplier supplier = (Supplier) o;
        if (supplier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Supplier{" +
            "id=" + getId() +
            ", applicantType='" + getApplicantType() + "'" +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", socialStatus='" + getSocialStatus() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", regnNumber='" + getRegnNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", panName='" + getPanName() + "'" +
            ", grossTurnOver=" + getGrossTurnOver() +
            ", supplierStatus='" + getSupplierStatus() + "'" +
            ", ssiYn='" + isSsiYn() + "'" +
            ", ssiRegnNumber='" + getSsiRegnNumber() + "'" +
            ", panStatus='" + isPanStatus() + "'" +
            ", msmeYn='" + isMsmeYn() + "'" +
            ", msmeRegnNumber='" + getMsmeRegnNumber() + "'" +
            "}";
    }
}
