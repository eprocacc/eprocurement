package com.dxc.app.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.dxc.app.domain.enumeration.SupplierStatus;
import com.dxc.app.domain.enumeration.SupplierUserType;
import com.dxc.app.domain.enumeration.SupplierStatus;

/**
 * A DTO for the SupplierGeneralInfo entity.
 */
public class SupplierGeneralInfoDTO implements Serializable {

    private Long id;

    private String name;

    private String firstName;

    private String middleName;

    private String lastName;

    private SupplierStatus supplierStatus;

    private String primaryEmail;

    private String secondaryEmail;

    private String website;

    private ZonedDateTime expiryDate;

    private String applicationRefNo;

    private Boolean typeGoodsYn;

    private Boolean typeServicesYn;

    private Boolean typeWorksYn;

    private SupplierUserType userType;

    private ZonedDateTime renewedDate;

    private Integer regValidity;

    private ZonedDateTime submissionDate;

    private Integer renewalNo;

    private ZonedDateTime approvaldate;

    private Boolean registrationBilledYn;

    private Boolean renewalBilledYn;

    private String comments;

    private String transactionRemarks;

    private Boolean blacklistedYn;

    private Boolean regMailNotificationYn;

    private Boolean applicationVerYn;

    private SupplierStatus approverDecision;

    private Boolean sendPublishedMail;

    private Boolean certificateCheckYn;

    private Boolean mobileVerified;

    private Boolean smsEnabled;

    private Boolean emailVerified;

    private String emailOTP;

    private ZonedDateTime emailOTPDate;

    private Boolean emailOTPExpired;

    private Long supplierId;

    private Long supplierGeneralToAddressId;

    private Long generalInfosToSupplierId;

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

    public SupplierStatus getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getApplicationRefNo() {
        return applicationRefNo;
    }

    public void setApplicationRefNo(String applicationRefNo) {
        this.applicationRefNo = applicationRefNo;
    }

    public Boolean isTypeGoodsYn() {
        return typeGoodsYn;
    }

    public void setTypeGoodsYn(Boolean typeGoodsYn) {
        this.typeGoodsYn = typeGoodsYn;
    }

    public Boolean isTypeServicesYn() {
        return typeServicesYn;
    }

    public void setTypeServicesYn(Boolean typeServicesYn) {
        this.typeServicesYn = typeServicesYn;
    }

    public Boolean isTypeWorksYn() {
        return typeWorksYn;
    }

    public void setTypeWorksYn(Boolean typeWorksYn) {
        this.typeWorksYn = typeWorksYn;
    }

    public SupplierUserType getUserType() {
        return userType;
    }

    public void setUserType(SupplierUserType userType) {
        this.userType = userType;
    }

    public ZonedDateTime getRenewedDate() {
        return renewedDate;
    }

    public void setRenewedDate(ZonedDateTime renewedDate) {
        this.renewedDate = renewedDate;
    }

    public Integer getRegValidity() {
        return regValidity;
    }

    public void setRegValidity(Integer regValidity) {
        this.regValidity = regValidity;
    }

    public ZonedDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(ZonedDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Integer getRenewalNo() {
        return renewalNo;
    }

    public void setRenewalNo(Integer renewalNo) {
        this.renewalNo = renewalNo;
    }

    public ZonedDateTime getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(ZonedDateTime approvaldate) {
        this.approvaldate = approvaldate;
    }

    public Boolean isRegistrationBilledYn() {
        return registrationBilledYn;
    }

    public void setRegistrationBilledYn(Boolean registrationBilledYn) {
        this.registrationBilledYn = registrationBilledYn;
    }

    public Boolean isRenewalBilledYn() {
        return renewalBilledYn;
    }

    public void setRenewalBilledYn(Boolean renewalBilledYn) {
        this.renewalBilledYn = renewalBilledYn;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public Boolean isBlacklistedYn() {
        return blacklistedYn;
    }

    public void setBlacklistedYn(Boolean blacklistedYn) {
        this.blacklistedYn = blacklistedYn;
    }

    public Boolean isRegMailNotificationYn() {
        return regMailNotificationYn;
    }

    public void setRegMailNotificationYn(Boolean regMailNotificationYn) {
        this.regMailNotificationYn = regMailNotificationYn;
    }

    public Boolean isApplicationVerYn() {
        return applicationVerYn;
    }

    public void setApplicationVerYn(Boolean applicationVerYn) {
        this.applicationVerYn = applicationVerYn;
    }

    public SupplierStatus getApproverDecision() {
        return approverDecision;
    }

    public void setApproverDecision(SupplierStatus approverDecision) {
        this.approverDecision = approverDecision;
    }

    public Boolean isSendPublishedMail() {
        return sendPublishedMail;
    }

    public void setSendPublishedMail(Boolean sendPublishedMail) {
        this.sendPublishedMail = sendPublishedMail;
    }

    public Boolean isCertificateCheckYn() {
        return certificateCheckYn;
    }

    public void setCertificateCheckYn(Boolean certificateCheckYn) {
        this.certificateCheckYn = certificateCheckYn;
    }

    public Boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public Boolean isSmsEnabled() {
        return smsEnabled;
    }

    public void setSmsEnabled(Boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public Boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmailOTP() {
        return emailOTP;
    }

    public void setEmailOTP(String emailOTP) {
        this.emailOTP = emailOTP;
    }

    public ZonedDateTime getEmailOTPDate() {
        return emailOTPDate;
    }

    public void setEmailOTPDate(ZonedDateTime emailOTPDate) {
        this.emailOTPDate = emailOTPDate;
    }

    public Boolean isEmailOTPExpired() {
        return emailOTPExpired;
    }

    public void setEmailOTPExpired(Boolean emailOTPExpired) {
        this.emailOTPExpired = emailOTPExpired;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getSupplierGeneralToAddressId() {
        return supplierGeneralToAddressId;
    }

    public void setSupplierGeneralToAddressId(Long addressId) {
        this.supplierGeneralToAddressId = addressId;
    }

    public Long getGeneralInfosToSupplierId() {
        return generalInfosToSupplierId;
    }

    public void setGeneralInfosToSupplierId(Long supplierId) {
        this.generalInfosToSupplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierGeneralInfoDTO supplierGeneralInfoDTO = (SupplierGeneralInfoDTO) o;
        if(supplierGeneralInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierGeneralInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierGeneralInfoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", supplierStatus='" + getSupplierStatus() + "'" +
            ", primaryEmail='" + getPrimaryEmail() + "'" +
            ", secondaryEmail='" + getSecondaryEmail() + "'" +
            ", website='" + getWebsite() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", applicationRefNo='" + getApplicationRefNo() + "'" +
            ", typeGoodsYn='" + isTypeGoodsYn() + "'" +
            ", typeServicesYn='" + isTypeServicesYn() + "'" +
            ", typeWorksYn='" + isTypeWorksYn() + "'" +
            ", userType='" + getUserType() + "'" +
            ", renewedDate='" + getRenewedDate() + "'" +
            ", regValidity=" + getRegValidity() +
            ", submissionDate='" + getSubmissionDate() + "'" +
            ", renewalNo=" + getRenewalNo() +
            ", approvaldate='" + getApprovaldate() + "'" +
            ", registrationBilledYn='" + isRegistrationBilledYn() + "'" +
            ", renewalBilledYn='" + isRenewalBilledYn() + "'" +
            ", comments='" + getComments() + "'" +
            ", transactionRemarks='" + getTransactionRemarks() + "'" +
            ", blacklistedYn='" + isBlacklistedYn() + "'" +
            ", regMailNotificationYn='" + isRegMailNotificationYn() + "'" +
            ", applicationVerYn='" + isApplicationVerYn() + "'" +
            ", approverDecision='" + getApproverDecision() + "'" +
            ", sendPublishedMail='" + isSendPublishedMail() + "'" +
            ", certificateCheckYn='" + isCertificateCheckYn() + "'" +
            ", mobileVerified='" + isMobileVerified() + "'" +
            ", smsEnabled='" + isSmsEnabled() + "'" +
            ", emailVerified='" + isEmailVerified() + "'" +
            ", emailOTP='" + getEmailOTP() + "'" +
            ", emailOTPDate='" + getEmailOTPDate() + "'" +
            ", emailOTPExpired='" + isEmailOTPExpired() + "'" +
            "}";
    }
}
