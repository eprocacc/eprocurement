package com.dxc.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.dxc.app.domain.enumeration.SupplierStatus;

import com.dxc.app.domain.enumeration.SupplierUserType;

/**
 * A SupplierGeneralInfo.
 */
@Entity
@Table(name = "supplier_general_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SupplierGeneralInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "supplier_status")
    private SupplierStatus supplierStatus;

    @Column(name = "primary_email")
    private String primaryEmail;

    @Column(name = "secondary_email")
    private String secondaryEmail;

    @Column(name = "website")
    private String website;

    @Column(name = "expiry_date")
    private ZonedDateTime expiryDate;

    @Column(name = "application_ref_no")
    private String applicationRefNo;

    @Column(name = "type_goods_yn")
    private Boolean typeGoodsYn;

    @Column(name = "type_services_yn")
    private Boolean typeServicesYn;

    @Column(name = "type_works_yn")
    private Boolean typeWorksYn;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private SupplierUserType userType;

    @Column(name = "renewed_date")
    private ZonedDateTime renewedDate;

    @Column(name = "reg_validity")
    private Integer regValidity;

    @Column(name = "submission_date")
    private ZonedDateTime submissionDate;

    @Column(name = "renewal_no")
    private Integer renewalNo;

    @Column(name = "approvaldate")
    private ZonedDateTime approvaldate;

    @Column(name = "registration_billed_yn")
    private Boolean registrationBilledYn;

    @Column(name = "renewal_billed_yn")
    private Boolean renewalBilledYn;

    @Column(name = "comments")
    private String comments;

    @Column(name = "transaction_remarks")
    private String transactionRemarks;

    @Column(name = "blacklisted_yn")
    private Boolean blacklistedYn;

    @Column(name = "reg_mail_notification_yn")
    private Boolean regMailNotificationYn;

    @Column(name = "application_ver_yn")
    private Boolean applicationVerYn;

    @Enumerated(EnumType.STRING)
    @Column(name = "approver_decision")
    private SupplierStatus approverDecision;

    @Column(name = "send_published_mail")
    private Boolean sendPublishedMail;

    @Column(name = "certificate_check_yn")
    private Boolean certificateCheckYn;

    @Column(name = "mobile_verified")
    private Boolean mobileVerified;

    @Column(name = "sms_enabled")
    private Boolean smsEnabled;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "email_otp")
    private String emailOTP;

    @Column(name = "email_otp_date")
    private ZonedDateTime emailOTPDate;

    @Column(name = "email_otp_expired")
    private Boolean emailOTPExpired;

    @ManyToOne
    private Supplier supplier;

    @OneToOne
    @JoinColumn(unique = true)
    private Address supplierGeneralToAddress;

    @OneToMany(mappedBy = "supplierGeneralInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Telephone> suppGeneralInfoTelephones = new HashSet<>();

    @ManyToOne
    private Supplier generalInfosToSupplier;

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

    public SupplierGeneralInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public SupplierGeneralInfo firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public SupplierGeneralInfo middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public SupplierGeneralInfo lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SupplierStatus getSupplierStatus() {
        return supplierStatus;
    }

    public SupplierGeneralInfo supplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
        return this;
    }

    public void setSupplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public SupplierGeneralInfo primaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
        return this;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public SupplierGeneralInfo secondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
        return this;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getWebsite() {
        return website;
    }

    public SupplierGeneralInfo website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public SupplierGeneralInfo expiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getApplicationRefNo() {
        return applicationRefNo;
    }

    public SupplierGeneralInfo applicationRefNo(String applicationRefNo) {
        this.applicationRefNo = applicationRefNo;
        return this;
    }

    public void setApplicationRefNo(String applicationRefNo) {
        this.applicationRefNo = applicationRefNo;
    }

    public Boolean isTypeGoodsYn() {
        return typeGoodsYn;
    }

    public SupplierGeneralInfo typeGoodsYn(Boolean typeGoodsYn) {
        this.typeGoodsYn = typeGoodsYn;
        return this;
    }

    public void setTypeGoodsYn(Boolean typeGoodsYn) {
        this.typeGoodsYn = typeGoodsYn;
    }

    public Boolean isTypeServicesYn() {
        return typeServicesYn;
    }

    public SupplierGeneralInfo typeServicesYn(Boolean typeServicesYn) {
        this.typeServicesYn = typeServicesYn;
        return this;
    }

    public void setTypeServicesYn(Boolean typeServicesYn) {
        this.typeServicesYn = typeServicesYn;
    }

    public Boolean isTypeWorksYn() {
        return typeWorksYn;
    }

    public SupplierGeneralInfo typeWorksYn(Boolean typeWorksYn) {
        this.typeWorksYn = typeWorksYn;
        return this;
    }

    public void setTypeWorksYn(Boolean typeWorksYn) {
        this.typeWorksYn = typeWorksYn;
    }

    public SupplierUserType getUserType() {
        return userType;
    }

    public SupplierGeneralInfo userType(SupplierUserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(SupplierUserType userType) {
        this.userType = userType;
    }

    public ZonedDateTime getRenewedDate() {
        return renewedDate;
    }

    public SupplierGeneralInfo renewedDate(ZonedDateTime renewedDate) {
        this.renewedDate = renewedDate;
        return this;
    }

    public void setRenewedDate(ZonedDateTime renewedDate) {
        this.renewedDate = renewedDate;
    }

    public Integer getRegValidity() {
        return regValidity;
    }

    public SupplierGeneralInfo regValidity(Integer regValidity) {
        this.regValidity = regValidity;
        return this;
    }

    public void setRegValidity(Integer regValidity) {
        this.regValidity = regValidity;
    }

    public ZonedDateTime getSubmissionDate() {
        return submissionDate;
    }

    public SupplierGeneralInfo submissionDate(ZonedDateTime submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    public void setSubmissionDate(ZonedDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Integer getRenewalNo() {
        return renewalNo;
    }

    public SupplierGeneralInfo renewalNo(Integer renewalNo) {
        this.renewalNo = renewalNo;
        return this;
    }

    public void setRenewalNo(Integer renewalNo) {
        this.renewalNo = renewalNo;
    }

    public ZonedDateTime getApprovaldate() {
        return approvaldate;
    }

    public SupplierGeneralInfo approvaldate(ZonedDateTime approvaldate) {
        this.approvaldate = approvaldate;
        return this;
    }

    public void setApprovaldate(ZonedDateTime approvaldate) {
        this.approvaldate = approvaldate;
    }

    public Boolean isRegistrationBilledYn() {
        return registrationBilledYn;
    }

    public SupplierGeneralInfo registrationBilledYn(Boolean registrationBilledYn) {
        this.registrationBilledYn = registrationBilledYn;
        return this;
    }

    public void setRegistrationBilledYn(Boolean registrationBilledYn) {
        this.registrationBilledYn = registrationBilledYn;
    }

    public Boolean isRenewalBilledYn() {
        return renewalBilledYn;
    }

    public SupplierGeneralInfo renewalBilledYn(Boolean renewalBilledYn) {
        this.renewalBilledYn = renewalBilledYn;
        return this;
    }

    public void setRenewalBilledYn(Boolean renewalBilledYn) {
        this.renewalBilledYn = renewalBilledYn;
    }

    public String getComments() {
        return comments;
    }

    public SupplierGeneralInfo comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public SupplierGeneralInfo transactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
        return this;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public Boolean isBlacklistedYn() {
        return blacklistedYn;
    }

    public SupplierGeneralInfo blacklistedYn(Boolean blacklistedYn) {
        this.blacklistedYn = blacklistedYn;
        return this;
    }

    public void setBlacklistedYn(Boolean blacklistedYn) {
        this.blacklistedYn = blacklistedYn;
    }

    public Boolean isRegMailNotificationYn() {
        return regMailNotificationYn;
    }

    public SupplierGeneralInfo regMailNotificationYn(Boolean regMailNotificationYn) {
        this.regMailNotificationYn = regMailNotificationYn;
        return this;
    }

    public void setRegMailNotificationYn(Boolean regMailNotificationYn) {
        this.regMailNotificationYn = regMailNotificationYn;
    }

    public Boolean isApplicationVerYn() {
        return applicationVerYn;
    }

    public SupplierGeneralInfo applicationVerYn(Boolean applicationVerYn) {
        this.applicationVerYn = applicationVerYn;
        return this;
    }

    public void setApplicationVerYn(Boolean applicationVerYn) {
        this.applicationVerYn = applicationVerYn;
    }

    public SupplierStatus getApproverDecision() {
        return approverDecision;
    }

    public SupplierGeneralInfo approverDecision(SupplierStatus approverDecision) {
        this.approverDecision = approverDecision;
        return this;
    }

    public void setApproverDecision(SupplierStatus approverDecision) {
        this.approverDecision = approverDecision;
    }

    public Boolean isSendPublishedMail() {
        return sendPublishedMail;
    }

    public SupplierGeneralInfo sendPublishedMail(Boolean sendPublishedMail) {
        this.sendPublishedMail = sendPublishedMail;
        return this;
    }

    public void setSendPublishedMail(Boolean sendPublishedMail) {
        this.sendPublishedMail = sendPublishedMail;
    }

    public Boolean isCertificateCheckYn() {
        return certificateCheckYn;
    }

    public SupplierGeneralInfo certificateCheckYn(Boolean certificateCheckYn) {
        this.certificateCheckYn = certificateCheckYn;
        return this;
    }

    public void setCertificateCheckYn(Boolean certificateCheckYn) {
        this.certificateCheckYn = certificateCheckYn;
    }

    public Boolean isMobileVerified() {
        return mobileVerified;
    }

    public SupplierGeneralInfo mobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
        return this;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public Boolean isSmsEnabled() {
        return smsEnabled;
    }

    public SupplierGeneralInfo smsEnabled(Boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
        return this;
    }

    public void setSmsEnabled(Boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public Boolean isEmailVerified() {
        return emailVerified;
    }

    public SupplierGeneralInfo emailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmailOTP() {
        return emailOTP;
    }

    public SupplierGeneralInfo emailOTP(String emailOTP) {
        this.emailOTP = emailOTP;
        return this;
    }

    public void setEmailOTP(String emailOTP) {
        this.emailOTP = emailOTP;
    }

    public ZonedDateTime getEmailOTPDate() {
        return emailOTPDate;
    }

    public SupplierGeneralInfo emailOTPDate(ZonedDateTime emailOTPDate) {
        this.emailOTPDate = emailOTPDate;
        return this;
    }

    public void setEmailOTPDate(ZonedDateTime emailOTPDate) {
        this.emailOTPDate = emailOTPDate;
    }

    public Boolean isEmailOTPExpired() {
        return emailOTPExpired;
    }

    public SupplierGeneralInfo emailOTPExpired(Boolean emailOTPExpired) {
        this.emailOTPExpired = emailOTPExpired;
        return this;
    }

    public void setEmailOTPExpired(Boolean emailOTPExpired) {
        this.emailOTPExpired = emailOTPExpired;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public SupplierGeneralInfo supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Address getSupplierGeneralToAddress() {
        return supplierGeneralToAddress;
    }

    public SupplierGeneralInfo supplierGeneralToAddress(Address address) {
        this.supplierGeneralToAddress = address;
        return this;
    }

    public void setSupplierGeneralToAddress(Address address) {
        this.supplierGeneralToAddress = address;
    }

    public Set<Telephone> getSuppGeneralInfoTelephones() {
        return suppGeneralInfoTelephones;
    }

    public SupplierGeneralInfo suppGeneralInfoTelephones(Set<Telephone> telephones) {
        this.suppGeneralInfoTelephones = telephones;
        return this;
    }

    public SupplierGeneralInfo addSuppGeneralInfoTelephones(Telephone telephone) {
        this.suppGeneralInfoTelephones.add(telephone);
        telephone.setSupplierGeneralInfo(this);
        return this;
    }

    public SupplierGeneralInfo removeSuppGeneralInfoTelephones(Telephone telephone) {
        this.suppGeneralInfoTelephones.remove(telephone);
        telephone.setSupplierGeneralInfo(null);
        return this;
    }

    public void setSuppGeneralInfoTelephones(Set<Telephone> telephones) {
        this.suppGeneralInfoTelephones = telephones;
    }

    public Supplier getGeneralInfosToSupplier() {
        return generalInfosToSupplier;
    }

    public SupplierGeneralInfo generalInfosToSupplier(Supplier supplier) {
        this.generalInfosToSupplier = supplier;
        return this;
    }

    public void setGeneralInfosToSupplier(Supplier supplier) {
        this.generalInfosToSupplier = supplier;
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
        SupplierGeneralInfo supplierGeneralInfo = (SupplierGeneralInfo) o;
        if (supplierGeneralInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierGeneralInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierGeneralInfo{" +
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
