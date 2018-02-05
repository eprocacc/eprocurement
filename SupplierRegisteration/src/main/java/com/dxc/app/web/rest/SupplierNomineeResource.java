package com.dxc.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dxc.app.service.SupplierNomineeService;
import com.dxc.app.web.rest.errors.BadRequestAlertException;
import com.dxc.app.web.rest.util.HeaderUtil;
import com.dxc.app.service.dto.SupplierNomineeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SupplierNominee.
 */
@RestController
@RequestMapping("/api")
public class SupplierNomineeResource {

    private final Logger log = LoggerFactory.getLogger(SupplierNomineeResource.class);

    private static final String ENTITY_NAME = "supplierNominee";

    private final SupplierNomineeService supplierNomineeService;

    public SupplierNomineeResource(SupplierNomineeService supplierNomineeService) {
        this.supplierNomineeService = supplierNomineeService;
    }

    /**
     * POST  /supplier-nominees : Create a new supplierNominee.
     *
     * @param supplierNomineeDTO the supplierNomineeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supplierNomineeDTO, or with status 400 (Bad Request) if the supplierNominee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/supplier-nominees")
    @Timed
    public ResponseEntity<SupplierNomineeDTO> createSupplierNominee(@RequestBody SupplierNomineeDTO supplierNomineeDTO) throws URISyntaxException {
        log.debug("REST request to save SupplierNominee : {}", supplierNomineeDTO);
        if (supplierNomineeDTO.getId() != null) {
            throw new BadRequestAlertException("A new supplierNominee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplierNomineeDTO result = supplierNomineeService.save(supplierNomineeDTO);
        return ResponseEntity.created(new URI("/api/supplier-nominees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /supplier-nominees : Updates an existing supplierNominee.
     *
     * @param supplierNomineeDTO the supplierNomineeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supplierNomineeDTO,
     * or with status 400 (Bad Request) if the supplierNomineeDTO is not valid,
     * or with status 500 (Internal Server Error) if the supplierNomineeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/supplier-nominees")
    @Timed
    public ResponseEntity<SupplierNomineeDTO> updateSupplierNominee(@RequestBody SupplierNomineeDTO supplierNomineeDTO) throws URISyntaxException {
        log.debug("REST request to update SupplierNominee : {}", supplierNomineeDTO);
        if (supplierNomineeDTO.getId() == null) {
            return createSupplierNominee(supplierNomineeDTO);
        }
        SupplierNomineeDTO result = supplierNomineeService.save(supplierNomineeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supplierNomineeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /supplier-nominees : get all the supplierNominees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of supplierNominees in body
     */
    @GetMapping("/supplier-nominees")
    @Timed
    public List<SupplierNomineeDTO> getAllSupplierNominees() {
        log.debug("REST request to get all SupplierNominees");
        return supplierNomineeService.findAll();
        }

    /**
     * GET  /supplier-nominees/:id : get the "id" supplierNominee.
     *
     * @param id the id of the supplierNomineeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supplierNomineeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/supplier-nominees/{id}")
    @Timed
    public ResponseEntity<SupplierNomineeDTO> getSupplierNominee(@PathVariable Long id) {
        log.debug("REST request to get SupplierNominee : {}", id);
        SupplierNomineeDTO supplierNomineeDTO = supplierNomineeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(supplierNomineeDTO));
    }

    /**
     * DELETE  /supplier-nominees/:id : delete the "id" supplierNominee.
     *
     * @param id the id of the supplierNomineeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/supplier-nominees/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupplierNominee(@PathVariable Long id) {
        log.debug("REST request to delete SupplierNominee : {}", id);
        supplierNomineeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
