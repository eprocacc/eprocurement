package com.dxc.app.service;

import com.dxc.app.service.dto.SupplierNomineeDTO;
import java.util.List;

/**
 * Service Interface for managing SupplierNominee.
 */
public interface SupplierNomineeService {

    /**
     * Save a supplierNominee.
     *
     * @param supplierNomineeDTO the entity to save
     * @return the persisted entity
     */
    SupplierNomineeDTO save(SupplierNomineeDTO supplierNomineeDTO);

    /**
     * Get all the supplierNominees.
     *
     * @return the list of entities
     */
    List<SupplierNomineeDTO> findAll();

    /**
     * Get the "id" supplierNominee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SupplierNomineeDTO findOne(Long id);

    /**
     * Delete the "id" supplierNominee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
