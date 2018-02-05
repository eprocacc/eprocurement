package com.dxc.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.dxc.app.domain.enumeration.PhoneType;

/**
 * A Telephone.
 */
@Entity
@Table(name = "telephone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Telephone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "area_code")
    private Integer areaCode;

    @Column(name = "jhi_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private PhoneType type;

    @Column(name = "mobile_otp")
    private String mobileOTP;

    @Column(name = "mobile_otp_date")
    private ZonedDateTime mobileOTPDate;

    @Column(name = "mobile_otp_expired")
    private Boolean mobileOTPExpired;

    @ManyToOne
    private SupplierGeneralInfo supplierGeneralInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public Telephone countryCode(Integer countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public Telephone areaCode(Integer areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public Telephone number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public Telephone type(PhoneType type) {
        this.type = type;
        return this;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getMobileOTP() {
        return mobileOTP;
    }

    public Telephone mobileOTP(String mobileOTP) {
        this.mobileOTP = mobileOTP;
        return this;
    }

    public void setMobileOTP(String mobileOTP) {
        this.mobileOTP = mobileOTP;
    }

    public ZonedDateTime getMobileOTPDate() {
        return mobileOTPDate;
    }

    public Telephone mobileOTPDate(ZonedDateTime mobileOTPDate) {
        this.mobileOTPDate = mobileOTPDate;
        return this;
    }

    public void setMobileOTPDate(ZonedDateTime mobileOTPDate) {
        this.mobileOTPDate = mobileOTPDate;
    }

    public Boolean isMobileOTPExpired() {
        return mobileOTPExpired;
    }

    public Telephone mobileOTPExpired(Boolean mobileOTPExpired) {
        this.mobileOTPExpired = mobileOTPExpired;
        return this;
    }

    public void setMobileOTPExpired(Boolean mobileOTPExpired) {
        this.mobileOTPExpired = mobileOTPExpired;
    }

    public SupplierGeneralInfo getSupplierGeneralInfo() {
        return supplierGeneralInfo;
    }

    public Telephone supplierGeneralInfo(SupplierGeneralInfo supplierGeneralInfo) {
        this.supplierGeneralInfo = supplierGeneralInfo;
        return this;
    }

    public void setSupplierGeneralInfo(SupplierGeneralInfo supplierGeneralInfo) {
        this.supplierGeneralInfo = supplierGeneralInfo;
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
        Telephone telephone = (Telephone) o;
        if (telephone.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), telephone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Telephone{" +
            "id=" + getId() +
            ", countryCode=" + getCountryCode() +
            ", areaCode=" + getAreaCode() +
            ", number='" + getNumber() + "'" +
            ", type='" + getType() + "'" +
            ", mobileOTP='" + getMobileOTP() + "'" +
            ", mobileOTPDate='" + getMobileOTPDate() + "'" +
            ", mobileOTPExpired='" + isMobileOTPExpired() + "'" +
            "}";
    }
}
