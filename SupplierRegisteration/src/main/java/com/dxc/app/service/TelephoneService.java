package com.dxc.app.service;

import com.dxc.app.service.dto.TelephoneDTO;
import java.util.List;

/**
 * Service Interface for managing Telephone.
 */
public interface TelephoneService {

    /**
     * Save a telephone.
     *
     * @param telephoneDTO the entity to save
     * @return the persisted entity
     */
    TelephoneDTO save(TelephoneDTO telephoneDTO);

    /**
     * Get all the telephones.
     *
     * @return the list of entities
     */
    List<TelephoneDTO> findAll();

    /**
     * Get the "id" telephone.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TelephoneDTO findOne(Long id);

    /**
     * Delete the "id" telephone.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
