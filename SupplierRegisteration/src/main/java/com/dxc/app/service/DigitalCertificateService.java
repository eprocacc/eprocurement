package com.dxc.app.service;

import com.dxc.app.service.dto.DigitalCertificateDTO;
import java.util.List;

/**
 * Service Interface for managing DigitalCertificate.
 */
public interface DigitalCertificateService {

    /**
     * Save a digitalCertificate.
     *
     * @param digitalCertificateDTO the entity to save
     * @return the persisted entity
     */
    DigitalCertificateDTO save(DigitalCertificateDTO digitalCertificateDTO);

    /**
     * Get all the digitalCertificates.
     *
     * @return the list of entities
     */
    List<DigitalCertificateDTO> findAll();

    /**
     * Get the "id" digitalCertificate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DigitalCertificateDTO findOne(Long id);

    /**
     * Delete the "id" digitalCertificate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
