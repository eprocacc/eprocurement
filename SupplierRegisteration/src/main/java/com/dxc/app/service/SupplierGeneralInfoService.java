package com.dxc.app.service;

import com.dxc.app.service.dto.SupplierGeneralInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing SupplierGeneralInfo.
 */
public interface SupplierGeneralInfoService {

    /**
     * Save a supplierGeneralInfo.
     *
     * @param supplierGeneralInfoDTO the entity to save
     * @return the persisted entity
     */
    SupplierGeneralInfoDTO save(SupplierGeneralInfoDTO supplierGeneralInfoDTO);

    /**
     * Get all the supplierGeneralInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SupplierGeneralInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" supplierGeneralInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SupplierGeneralInfoDTO findOne(Long id);

    /**
     * Delete the "id" supplierGeneralInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
