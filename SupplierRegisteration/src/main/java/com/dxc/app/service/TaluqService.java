package com.dxc.app.service;

import com.dxc.app.service.dto.TaluqDTO;
import java.util.List;

/**
 * Service Interface for managing Taluq.
 */
public interface TaluqService {

    /**
     * Save a taluq.
     *
     * @param taluqDTO the entity to save
     * @return the persisted entity
     */
    TaluqDTO save(TaluqDTO taluqDTO);

    /**
     * Get all the taluqs.
     *
     * @return the list of entities
     */
    List<TaluqDTO> findAll();

    /**
     * Get the "id" taluq.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaluqDTO findOne(Long id);

    /**
     * Delete the "id" taluq.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
