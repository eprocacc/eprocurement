package com.dxc.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dxc.app.service.DigitalCertificateService;
import com.dxc.app.web.rest.errors.BadRequestAlertException;
import com.dxc.app.web.rest.util.HeaderUtil;
import com.dxc.app.service.dto.DigitalCertificateDTO;
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
 * REST controller for managing DigitalCertificate.
 */
@RestController
@RequestMapping("/api")
public class DigitalCertificateResource {

    private final Logger log = LoggerFactory.getLogger(DigitalCertificateResource.class);

    private static final String ENTITY_NAME = "digitalCertificate";

    private final DigitalCertificateService digitalCertificateService;

    public DigitalCertificateResource(DigitalCertificateService digitalCertificateService) {
        this.digitalCertificateService = digitalCertificateService;
    }

    /**
     * POST  /digital-certificates : Create a new digitalCertificate.
     *
     * @param digitalCertificateDTO the digitalCertificateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new digitalCertificateDTO, or with status 400 (Bad Request) if the digitalCertificate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/digital-certificates")
    @Timed
    public ResponseEntity<DigitalCertificateDTO> createDigitalCertificate(@RequestBody DigitalCertificateDTO digitalCertificateDTO) throws URISyntaxException {
        log.debug("REST request to save DigitalCertificate : {}", digitalCertificateDTO);
        if (digitalCertificateDTO.getId() != null) {
            throw new BadRequestAlertException("A new digitalCertificate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DigitalCertificateDTO result = digitalCertificateService.save(digitalCertificateDTO);
        return ResponseEntity.created(new URI("/api/digital-certificates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /digital-certificates : Updates an existing digitalCertificate.
     *
     * @param digitalCertificateDTO the digitalCertificateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated digitalCertificateDTO,
     * or with status 400 (Bad Request) if the digitalCertificateDTO is not valid,
     * or with status 500 (Internal Server Error) if the digitalCertificateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/digital-certificates")
    @Timed
    public ResponseEntity<DigitalCertificateDTO> updateDigitalCertificate(@RequestBody DigitalCertificateDTO digitalCertificateDTO) throws URISyntaxException {
        log.debug("REST request to update DigitalCertificate : {}", digitalCertificateDTO);
        if (digitalCertificateDTO.getId() == null) {
            return createDigitalCertificate(digitalCertificateDTO);
        }
        DigitalCertificateDTO result = digitalCertificateService.save(digitalCertificateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, digitalCertificateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /digital-certificates : get all the digitalCertificates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of digitalCertificates in body
     */
    @GetMapping("/digital-certificates")
    @Timed
    public List<DigitalCertificateDTO> getAllDigitalCertificates() {
        log.debug("REST request to get all DigitalCertificates");
        return digitalCertificateService.findAll();
        }

    /**
     * GET  /digital-certificates/:id : get the "id" digitalCertificate.
     *
     * @param id the id of the digitalCertificateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the digitalCertificateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/digital-certificates/{id}")
    @Timed
    public ResponseEntity<DigitalCertificateDTO> getDigitalCertificate(@PathVariable Long id) {
        log.debug("REST request to get DigitalCertificate : {}", id);
        DigitalCertificateDTO digitalCertificateDTO = digitalCertificateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(digitalCertificateDTO));
    }

    /**
     * DELETE  /digital-certificates/:id : delete the "id" digitalCertificate.
     *
     * @param id the id of the digitalCertificateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/digital-certificates/{id}")
    @Timed
    public ResponseEntity<Void> deleteDigitalCertificate(@PathVariable Long id) {
        log.debug("REST request to delete DigitalCertificate : {}", id);
        digitalCertificateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
