package com.dxc.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DigitalCertificate.
 */
@Entity
@Table(name = "digital_certificate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DigitalCertificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cert_chain")
    private String certChain;

    @Column(name = "certificate_serial")
    private String certificateSerial;

    @Column(name = "term_start_date")
    private ZonedDateTime termStartDate;

    @Column(name = "term_end_date")
    private ZonedDateTime termEndDate;

    @Column(name = "signature")
    private Boolean signature;

    @Column(name = "encryption")
    private Boolean encryption;

    @Column(name = "active_yn")
    private Boolean activeYn;

    @ManyToOne
    private Supplier digitalCertificateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertChain() {
        return certChain;
    }

    public DigitalCertificate certChain(String certChain) {
        this.certChain = certChain;
        return this;
    }

    public void setCertChain(String certChain) {
        this.certChain = certChain;
    }

    public String getCertificateSerial() {
        return certificateSerial;
    }

    public DigitalCertificate certificateSerial(String certificateSerial) {
        this.certificateSerial = certificateSerial;
        return this;
    }

    public void setCertificateSerial(String certificateSerial) {
        this.certificateSerial = certificateSerial;
    }

    public ZonedDateTime getTermStartDate() {
        return termStartDate;
    }

    public DigitalCertificate termStartDate(ZonedDateTime termStartDate) {
        this.termStartDate = termStartDate;
        return this;
    }

    public void setTermStartDate(ZonedDateTime termStartDate) {
        this.termStartDate = termStartDate;
    }

    public ZonedDateTime getTermEndDate() {
        return termEndDate;
    }

    public DigitalCertificate termEndDate(ZonedDateTime termEndDate) {
        this.termEndDate = termEndDate;
        return this;
    }

    public void setTermEndDate(ZonedDateTime termEndDate) {
        this.termEndDate = termEndDate;
    }

    public Boolean isSignature() {
        return signature;
    }

    public DigitalCertificate signature(Boolean signature) {
        this.signature = signature;
        return this;
    }

    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    public Boolean isEncryption() {
        return encryption;
    }

    public DigitalCertificate encryption(Boolean encryption) {
        this.encryption = encryption;
        return this;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public Boolean isActiveYn() {
        return activeYn;
    }

    public DigitalCertificate activeYn(Boolean activeYn) {
        this.activeYn = activeYn;
        return this;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    public Supplier getDigitalCertificateUser() {
        return digitalCertificateUser;
    }

    public DigitalCertificate digitalCertificateUser(Supplier supplier) {
        this.digitalCertificateUser = supplier;
        return this;
    }

    public void setDigitalCertificateUser(Supplier supplier) {
        this.digitalCertificateUser = supplier;
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
        DigitalCertificate digitalCertificate = (DigitalCertificate) o;
        if (digitalCertificate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), digitalCertificate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DigitalCertificate{" +
            "id=" + getId() +
            ", certChain='" + getCertChain() + "'" +
            ", certificateSerial='" + getCertificateSerial() + "'" +
            ", termStartDate='" + getTermStartDate() + "'" +
            ", termEndDate='" + getTermEndDate() + "'" +
            ", signature='" + isSignature() + "'" +
            ", encryption='" + isEncryption() + "'" +
            ", activeYn='" + isActiveYn() + "'" +
            "}";
    }
}
