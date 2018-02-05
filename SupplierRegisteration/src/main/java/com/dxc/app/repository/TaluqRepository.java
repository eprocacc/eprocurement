package com.dxc.app.repository;

import com.dxc.app.domain.Taluq;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Taluq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaluqRepository extends JpaRepository<Taluq, Long> {

}
