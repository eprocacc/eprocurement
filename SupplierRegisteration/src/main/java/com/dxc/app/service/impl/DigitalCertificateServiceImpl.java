package com.dxc.app.service.impl;

import com.dxc.app.service.DigitalCertificateService;
import com.dxc.app.domain.DigitalCertificate;
import com.dxc.app.repository.DigitalCertificateRepository;
import com.dxc.app.service.dto.DigitalCertificateDTO;
import com.dxc.app.service.mapper.DigitalCertificateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DigitalCertificate.
 */
@Service
@Transactional
public class DigitalCertificateServiceImpl implements DigitalCertificateService{

    private final Logger log = LoggerFactory.getLogger(DigitalCertificateServiceImpl.class);

    private final DigitalCertificateRepository digitalCertificateRepository;

    private final DigitalCertificateMapper digitalCertificateMapper;

    public DigitalCertificateServiceImpl(DigitalCertificateRepository digitalCertificateRepository, DigitalCertificateMapper digitalCertificateMapper) {
        this.digitalCertificateRepository = digitalCertificateRepository;
        this.digitalCertificateMapper = digitalCertificateMapper;
    }

    /**
     * Save a digitalCertificate.
     *
     * @param digitalCertificateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DigitalCertificateDTO save(DigitalCertificateDTO digitalCertificateDTO) {
        log.debug("Request to save DigitalCertificate : {}", digitalCertificateDTO);
        DigitalCertificate digitalCertificate = digitalCertificateMapper.toEntity(digitalCertificateDTO);
        digitalCertificate = digitalCertificateRepository.save(digitalCertificate);
        return digitalCertificateMapper.toDto(digitalCertificate);
    }

    /**
     * Get all the digitalCertificates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DigitalCertificateDTO> findAll() {
        log.debug("Request to get all DigitalCertificates");
        return digitalCertificateRepository.findAll().stream()
            .map(digitalCertificateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one digitalCertificate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DigitalCertificateDTO findOne(Long id) {
        log.debug("Request to get DigitalCertificate : {}", id);
        DigitalCertificate digitalCertificate = digitalCertificateRepository.findOne(id);
        return digitalCertificateMapper.toDto(digitalCertificate);
    }

    /**
     * Delete the digitalCertificate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DigitalCertificate : {}", id);
        digitalCertificateRepository.delete(id);
    }
}
