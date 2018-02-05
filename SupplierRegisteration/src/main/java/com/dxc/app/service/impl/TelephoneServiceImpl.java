package com.dxc.app.service.impl;

import com.dxc.app.service.TelephoneService;
import com.dxc.app.domain.Telephone;
import com.dxc.app.repository.TelephoneRepository;
import com.dxc.app.service.dto.TelephoneDTO;
import com.dxc.app.service.mapper.TelephoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Telephone.
 */
@Service
@Transactional
public class TelephoneServiceImpl implements TelephoneService{

    private final Logger log = LoggerFactory.getLogger(TelephoneServiceImpl.class);

    private final TelephoneRepository telephoneRepository;

    private final TelephoneMapper telephoneMapper;

    public TelephoneServiceImpl(TelephoneRepository telephoneRepository, TelephoneMapper telephoneMapper) {
        this.telephoneRepository = telephoneRepository;
        this.telephoneMapper = telephoneMapper;
    }

    /**
     * Save a telephone.
     *
     * @param telephoneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TelephoneDTO save(TelephoneDTO telephoneDTO) {
        log.debug("Request to save Telephone : {}", telephoneDTO);
        Telephone telephone = telephoneMapper.toEntity(telephoneDTO);
        telephone = telephoneRepository.save(telephone);
        return telephoneMapper.toDto(telephone);
    }

    /**
     * Get all the telephones.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TelephoneDTO> findAll() {
        log.debug("Request to get all Telephones");
        return telephoneRepository.findAll().stream()
            .map(telephoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one telephone by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TelephoneDTO findOne(Long id) {
        log.debug("Request to get Telephone : {}", id);
        Telephone telephone = telephoneRepository.findOne(id);
        return telephoneMapper.toDto(telephone);
    }

    /**
     * Delete the telephone by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Telephone : {}", id);
        telephoneRepository.delete(id);
    }
}
