package com.dxc.app.service.impl;

import com.dxc.app.service.SupplierGeneralInfoService;
import com.dxc.app.domain.SupplierGeneralInfo;
import com.dxc.app.repository.SupplierGeneralInfoRepository;
import com.dxc.app.service.dto.SupplierGeneralInfoDTO;
import com.dxc.app.service.mapper.SupplierGeneralInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SupplierGeneralInfo.
 */
@Service
@Transactional
public class SupplierGeneralInfoServiceImpl implements SupplierGeneralInfoService{

    private final Logger log = LoggerFactory.getLogger(SupplierGeneralInfoServiceImpl.class);

    private final SupplierGeneralInfoRepository supplierGeneralInfoRepository;

    private final SupplierGeneralInfoMapper supplierGeneralInfoMapper;

    public SupplierGeneralInfoServiceImpl(SupplierGeneralInfoRepository supplierGeneralInfoRepository, SupplierGeneralInfoMapper supplierGeneralInfoMapper) {
        this.supplierGeneralInfoRepository = supplierGeneralInfoRepository;
        this.supplierGeneralInfoMapper = supplierGeneralInfoMapper;
    }

    /**
     * Save a supplierGeneralInfo.
     *
     * @param supplierGeneralInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupplierGeneralInfoDTO save(SupplierGeneralInfoDTO supplierGeneralInfoDTO) {
        log.debug("Request to save SupplierGeneralInfo : {}", supplierGeneralInfoDTO);
        SupplierGeneralInfo supplierGeneralInfo = supplierGeneralInfoMapper.toEntity(supplierGeneralInfoDTO);
        supplierGeneralInfo = supplierGeneralInfoRepository.save(supplierGeneralInfo);
        return supplierGeneralInfoMapper.toDto(supplierGeneralInfo);
    }

    /**
     * Get all the supplierGeneralInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupplierGeneralInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SupplierGeneralInfos");
        return supplierGeneralInfoRepository.findAll(pageable)
            .map(supplierGeneralInfoMapper::toDto);
    }

    /**
     * Get one supplierGeneralInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SupplierGeneralInfoDTO findOne(Long id) {
        log.debug("Request to get SupplierGeneralInfo : {}", id);
        SupplierGeneralInfo supplierGeneralInfo = supplierGeneralInfoRepository.findOne(id);
        return supplierGeneralInfoMapper.toDto(supplierGeneralInfo);
    }

    /**
     * Delete the supplierGeneralInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupplierGeneralInfo : {}", id);
        supplierGeneralInfoRepository.delete(id);
    }
}
