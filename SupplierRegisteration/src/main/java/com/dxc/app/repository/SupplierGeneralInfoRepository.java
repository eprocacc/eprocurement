package com.dxc.app.repository;

import com.dxc.app.domain.SupplierGeneralInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SupplierGeneralInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierGeneralInfoRepository extends JpaRepository<SupplierGeneralInfo, Long> {

}
