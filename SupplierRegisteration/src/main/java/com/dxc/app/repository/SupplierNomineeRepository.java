package com.dxc.app.repository;

import com.dxc.app.domain.SupplierNominee;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SupplierNominee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierNomineeRepository extends JpaRepository<SupplierNominee, Long> {

}
