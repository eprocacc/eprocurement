package com.dxc.app.service.impl;

import com.dxc.app.service.SupplierNomineeService;
import com.dxc.app.domain.SupplierNominee;
import com.dxc.app.repository.SupplierNomineeRepository;
import com.dxc.app.service.dto.SupplierNomineeDTO;
import com.dxc.app.service.mapper.SupplierNomineeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing SupplierNominee.
 */
@Service
@Transactional
public class SupplierNomineeServiceImpl implements SupplierNomineeService{

    private final Logger log = LoggerFactory.getLogger(SupplierNomineeServiceImpl.class);

    private final SupplierNomineeRepository supplierNomineeRepository;

    private final SupplierNomineeMapper supplierNomineeMapper;

    public SupplierNomineeServiceImpl(SupplierNomineeRepository supplierNomineeRepository, SupplierNomineeMapper supplierNomineeMapper) {
        this.supplierNomineeRepository = supplierNomineeRepository;
        this.supplierNomineeMapper = supplierNomineeMapper;
    }

    /**
     * Save a supplierNominee.
     *
     * @param supplierNomineeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupplierNomineeDTO save(SupplierNomineeDTO supplierNomineeDTO) {
        log.debug("Request to save SupplierNominee : {}", supplierNomineeDTO);
        SupplierNominee supplierNominee = supplierNomineeMapper.toEntity(supplierNomineeDTO);
        supplierNominee = supplierNomineeRepository.save(supplierNominee);
        return supplierNomineeMapper.toDto(supplierNominee);
    }

    /**
     * Get all the supplierNominees.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SupplierNomineeDTO> findAll() {
        log.debug("Request to get all SupplierNominees");
        return supplierNomineeRepository.findAll().stream()
            .map(supplierNomineeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one supplierNominee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SupplierNomineeDTO findOne(Long id) {
        log.debug("Request to get SupplierNominee : {}", id);
        SupplierNominee supplierNominee = supplierNomineeRepository.findOne(id);
        return supplierNomineeMapper.toDto(supplierNominee);
    }

    /**
     * Delete the supplierNominee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupplierNominee : {}", id);
        supplierNomineeRepository.delete(id);
    }
}
