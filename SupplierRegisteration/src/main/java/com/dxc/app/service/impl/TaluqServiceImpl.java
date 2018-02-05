package com.dxc.app.service.impl;

import com.dxc.app.service.TaluqService;
import com.dxc.app.domain.Taluq;
import com.dxc.app.repository.TaluqRepository;
import com.dxc.app.service.dto.TaluqDTO;
import com.dxc.app.service.mapper.TaluqMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Taluq.
 */
@Service
@Transactional
public class TaluqServiceImpl implements TaluqService{

    private final Logger log = LoggerFactory.getLogger(TaluqServiceImpl.class);

    private final TaluqRepository taluqRepository;

    private final TaluqMapper taluqMapper;

    public TaluqServiceImpl(TaluqRepository taluqRepository, TaluqMapper taluqMapper) {
        this.taluqRepository = taluqRepository;
        this.taluqMapper = taluqMapper;
    }

    /**
     * Save a taluq.
     *
     * @param taluqDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaluqDTO save(TaluqDTO taluqDTO) {
        log.debug("Request to save Taluq : {}", taluqDTO);
        Taluq taluq = taluqMapper.toEntity(taluqDTO);
        taluq = taluqRepository.save(taluq);
        return taluqMapper.toDto(taluq);
    }

    /**
     * Get all the taluqs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaluqDTO> findAll() {
        log.debug("Request to get all Taluqs");
        return taluqRepository.findAll().stream()
            .map(taluqMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one taluq by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaluqDTO findOne(Long id) {
        log.debug("Request to get Taluq : {}", id);
        Taluq taluq = taluqRepository.findOne(id);
        return taluqMapper.toDto(taluq);
    }

    /**
     * Delete the taluq by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Taluq : {}", id);
        taluqRepository.delete(id);
    }
}
