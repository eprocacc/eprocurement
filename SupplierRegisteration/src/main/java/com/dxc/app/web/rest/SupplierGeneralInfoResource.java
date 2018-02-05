package com.dxc.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dxc.app.service.SupplierGeneralInfoService;
import com.dxc.app.web.rest.errors.BadRequestAlertException;
import com.dxc.app.web.rest.util.HeaderUtil;
import com.dxc.app.web.rest.util.PaginationUtil;
import com.dxc.app.service.dto.SupplierGeneralInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SupplierGeneralInfo.
 */
@RestController
@RequestMapping("/api")
public class SupplierGeneralInfoResource {

    private final Logger log = LoggerFactory.getLogger(SupplierGeneralInfoResource.class);

    private static final String ENTITY_NAME = "supplierGeneralInfo";

    private final SupplierGeneralInfoService supplierGeneralInfoService;

    public SupplierGeneralInfoResource(SupplierGeneralInfoService supplierGeneralInfoService) {
        this.supplierGeneralInfoService = supplierGeneralInfoService;
    }

    /**
     * POST  /supplier-general-infos : Create a new supplierGeneralInfo.
     *
     * @param supplierGeneralInfoDTO the supplierGeneralInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supplierGeneralInfoDTO, or with status 400 (Bad Request) if the supplierGeneralInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/supplier-general-infos")
    @Timed
    public ResponseEntity<SupplierGeneralInfoDTO> createSupplierGeneralInfo(@RequestBody SupplierGeneralInfoDTO supplierGeneralInfoDTO) throws URISyntaxException {
        log.debug("REST request to save SupplierGeneralInfo : {}", supplierGeneralInfoDTO);
        if (supplierGeneralInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new supplierGeneralInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplierGeneralInfoDTO result = supplierGeneralInfoService.save(supplierGeneralInfoDTO);
        return ResponseEntity.created(new URI("/api/supplier-general-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /supplier-general-infos : Updates an existing supplierGeneralInfo.
     *
     * @param supplierGeneralInfoDTO the supplierGeneralInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supplierGeneralInfoDTO,
     * or with status 400 (Bad Request) if the supplierGeneralInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the supplierGeneralInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/supplier-general-infos")
    @Timed
    public ResponseEntity<SupplierGeneralInfoDTO> updateSupplierGeneralInfo(@RequestBody SupplierGeneralInfoDTO supplierGeneralInfoDTO) throws URISyntaxException {
        log.debug("REST request to update SupplierGeneralInfo : {}", supplierGeneralInfoDTO);
        if (supplierGeneralInfoDTO.getId() == null) {
            return createSupplierGeneralInfo(supplierGeneralInfoDTO);
        }
        SupplierGeneralInfoDTO result = supplierGeneralInfoService.save(supplierGeneralInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supplierGeneralInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /supplier-general-infos : get all the supplierGeneralInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of supplierGeneralInfos in body
     */
    @GetMapping("/supplier-general-infos")
    @Timed
    public ResponseEntity<List<SupplierGeneralInfoDTO>> getAllSupplierGeneralInfos(Pageable pageable) {
        log.debug("REST request to get a page of SupplierGeneralInfos");
        Page<SupplierGeneralInfoDTO> page = supplierGeneralInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/supplier-general-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /supplier-general-infos/:id : get the "id" supplierGeneralInfo.
     *
     * @param id the id of the supplierGeneralInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supplierGeneralInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/supplier-general-infos/{id}")
    @Timed
    public ResponseEntity<SupplierGeneralInfoDTO> getSupplierGeneralInfo(@PathVariable Long id) {
        log.debug("REST request to get SupplierGeneralInfo : {}", id);
        SupplierGeneralInfoDTO supplierGeneralInfoDTO = supplierGeneralInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(supplierGeneralInfoDTO));
    }

    /**
     * DELETE  /supplier-general-infos/:id : delete the "id" supplierGeneralInfo.
     *
     * @param id the id of the supplierGeneralInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/supplier-general-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupplierGeneralInfo(@PathVariable Long id) {
        log.debug("REST request to delete SupplierGeneralInfo : {}", id);
        supplierGeneralInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
