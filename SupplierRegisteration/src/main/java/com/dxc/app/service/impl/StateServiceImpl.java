package com.dxc.app.service.impl;

import com.dxc.app.service.StateService;
import com.dxc.app.domain.State;
import com.dxc.app.repository.StateRepository;
import com.dxc.app.service.dto.StateDTO;
import com.dxc.app.service.mapper.StateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing State.
 */
@Service
@Transactional
public class StateServiceImpl implements StateService{

    private final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);

    private final StateRepository stateRepository;

    private final StateMapper stateMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    /**
     * Save a state.
     *
     * @param stateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = stateMapper.toEntity(stateDTO);
        state = stateRepository.save(state);
        return stateMapper.toDto(state);
    }

    /**
     * Get all the states.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StateDTO> findAll() {
        log.debug("Request to get all States");
        return stateRepository.findAll().stream()
            .map(stateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one state by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StateDTO findOne(Long id) {
        log.debug("Request to get State : {}", id);
        State state = stateRepository.findOne(id);
        return stateMapper.toDto(state);
    }

    /**
     * Delete the state by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.delete(id);
    }
}
