package com.dxc.app.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DigitalCertificate entity.
 */
public class DigitalCertificateDTO implements Serializable {

    private Long id;

    private String certChain;

    private String certificateSerial;

    private ZonedDateTime termStartDate;

    private ZonedDateTime termEndDate;

    private Boolean signature;

    private Boolean encryption;

    private Boolean activeYn;

    private Long digitalCertificateUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertChain() {
        return certChain;
    }

    public void setCertChain(String certChain) {
        this.certChain = certChain;
    }

    public String getCertificateSerial() {
        return certificateSerial;
    }

    public void setCertificateSerial(String certificateSerial) {
        this.certificateSerial = certificateSerial;
    }

    public ZonedDateTime getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(ZonedDateTime termStartDate) {
        this.termStartDate = termStartDate;
    }

    public ZonedDateTime getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(ZonedDateTime termEndDate) {
        this.termEndDate = termEndDate;
    }

    public Boolean isSignature() {
        return signature;
    }

    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    public Boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public Boolean isActiveYn() {
        return activeYn;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    public Long getDigitalCertificateUserId() {
        return digitalCertificateUserId;
    }

    public void setDigitalCertificateUserId(Long supplierId) {
        this.digitalCertificateUserId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DigitalCertificateDTO digitalCertificateDTO = (DigitalCertificateDTO) o;
        if(digitalCertificateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), digitalCertificateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DigitalCertificateDTO{" +
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
