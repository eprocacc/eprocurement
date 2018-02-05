package com.dxc.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.dxc.app.domain.enumeration.SocialStatus;
import com.dxc.app.domain.enumeration.SupplierStatus;

/**
 * A DTO for the Supplier entity.
 */
public class SupplierDTO implements Serializable {

    private Long id;

    private String applicantType;

    private String type;

    private String name;

    private String firstName;

    private String middleName;

    private String lastName;

    private SocialStatus socialStatus;

    private String designation;

    private String regnNumber;

    private String pan;

    private String panName;

    private Double grossTurnOver;

    private SupplierStatus supplierStatus;

    private Boolean ssiYn;

    private String ssiRegnNumber;

    private Boolean panStatus;

    private Boolean msmeYn;

    private String msmeRegnNumber;

    private Long supplierAddressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRegnNumber() {
        return regnNumber;
    }

    public void setRegnNumber(String regnNumber) {
        this.regnNumber = regnNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanName() {
        return panName;
    }

    public void setPanName(String panName) {
        this.panName = panName;
    }

    public Double getGrossTurnOver() {
        return grossTurnOver;
    }

    public void setGrossTurnOver(Double grossTurnOver) {
        this.grossTurnOver = grossTurnOver;
    }

    public SupplierStatus getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public Boolean isSsiYn() {
        return ssiYn;
    }

    public void setSsiYn(Boolean ssiYn) {
        this.ssiYn = ssiYn;
    }

    public String getSsiRegnNumber() {
        return ssiRegnNumber;
    }

    public void setSsiRegnNumber(String ssiRegnNumber) {
        this.ssiRegnNumber = ssiRegnNumber;
    }

    public Boolean isPanStatus() {
        return panStatus;
    }

    public void setPanStatus(Boolean panStatus) {
        this.panStatus = panStatus;
    }

    public Boolean isMsmeYn() {
        return msmeYn;
    }

    public void setMsmeYn(Boolean msmeYn) {
        this.msmeYn = msmeYn;
    }

    public String getMsmeRegnNumber() {
        return msmeRegnNumber;
    }

    public void setMsmeRegnNumber(String msmeRegnNumber) {
        this.msmeRegnNumber = msmeRegnNumber;
    }

    public Long getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(Long addressId) {
        this.supplierAddressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierDTO supplierDTO = (SupplierDTO) o;
        if(supplierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
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
