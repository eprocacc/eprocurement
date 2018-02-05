package com.dxc.app.repository;

import com.dxc.app.domain.DigitalCertificate;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DigitalCertificate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DigitalCertificateRepository extends JpaRepository<DigitalCertificate, Long> {

}
