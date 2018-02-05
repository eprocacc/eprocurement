package com.dxc.app.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.dxc.app.domain.enumeration.PhoneType;

/**
 * A DTO for the Telephone entity.
 */
public class TelephoneDTO implements Serializable {

    private Long id;

    private Integer countryCode;

    private Integer areaCode;

    private String number;

    private PhoneType type;

    private String mobileOTP;

    private ZonedDateTime mobileOTPDate;

    private Boolean mobileOTPExpired;

    private Long supplierGeneralInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getMobileOTP() {
        return mobileOTP;
    }

    public void setMobileOTP(String mobileOTP) {
        this.mobileOTP = mobileOTP;
    }

    public ZonedDateTime getMobileOTPDate() {
        return mobileOTPDate;
    }

    public void setMobileOTPDate(ZonedDateTime mobileOTPDate) {
        this.mobileOTPDate = mobileOTPDate;
    }

    public Boolean isMobileOTPExpired() {
        return mobileOTPExpired;
    }

    public void setMobileOTPExpired(Boolean mobileOTPExpired) {
        this.mobileOTPExpired = mobileOTPExpired;
    }

    public Long getSupplierGeneralInfoId() {
        return supplierGeneralInfoId;
    }

    public void setSupplierGeneralInfoId(Long supplierGeneralInfoId) {
        this.supplierGeneralInfoId = supplierGeneralInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TelephoneDTO telephoneDTO = (TelephoneDTO) o;
        if(telephoneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), telephoneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TelephoneDTO{" +
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
