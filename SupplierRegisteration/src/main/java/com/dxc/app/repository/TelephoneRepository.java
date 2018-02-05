package com.dxc.app.repository;

import com.dxc.app.domain.Telephone;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Telephone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

}
