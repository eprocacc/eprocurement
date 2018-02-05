package com.dxc.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dxc.app.service.TaluqService;
import com.dxc.app.web.rest.errors.BadRequestAlertException;
import com.dxc.app.web.rest.util.HeaderUtil;
import com.dxc.app.service.dto.TaluqDTO;
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
 * REST controller for managing Taluq.
 */
@RestController
@RequestMapping("/api")
public class TaluqResource {

    private final Logger log = LoggerFactory.getLogger(TaluqResource.class);

    private static final String ENTITY_NAME = "taluq";

    private final TaluqService taluqService;

    public TaluqResource(TaluqService taluqService) {
        this.taluqService = taluqService;
    }

    /**
     * POST  /taluqs : Create a new taluq.
     *
     * @param taluqDTO the taluqDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taluqDTO, or with status 400 (Bad Request) if the taluq has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/taluqs")
    @Timed
    public ResponseEntity<TaluqDTO> createTaluq(@RequestBody TaluqDTO taluqDTO) throws URISyntaxException {
        log.debug("REST request to save Taluq : {}", taluqDTO);
        if (taluqDTO.getId() != null) {
            throw new BadRequestAlertException("A new taluq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaluqDTO result = taluqService.save(taluqDTO);
        return ResponseEntity.created(new URI("/api/taluqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /taluqs : Updates an existing taluq.
     *
     * @param taluqDTO the taluqDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taluqDTO,
     * or with status 400 (Bad Request) if the taluqDTO is not valid,
     * or with status 500 (Internal Server Error) if the taluqDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/taluqs")
    @Timed
    public ResponseEntity<TaluqDTO> updateTaluq(@RequestBody TaluqDTO taluqDTO) throws URISyntaxException {
        log.debug("REST request to update Taluq : {}", taluqDTO);
        if (taluqDTO.getId() == null) {
            return createTaluq(taluqDTO);
        }
        TaluqDTO result = taluqService.save(taluqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taluqDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /taluqs : get all the taluqs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of taluqs in body
     */
    @GetMapping("/taluqs")
    @Timed
    public List<TaluqDTO> getAllTaluqs() {
        log.debug("REST request to get all Taluqs");
        return taluqService.findAll();
        }

    /**
     * GET  /taluqs/:id : get the "id" taluq.
     *
     * @param id the id of the taluqDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taluqDTO, or with status 404 (Not Found)
     */
    @GetMapping("/taluqs/{id}")
    @Timed
    public ResponseEntity<TaluqDTO> getTaluq(@PathVariable Long id) {
        log.debug("REST request to get Taluq : {}", id);
        TaluqDTO taluqDTO = taluqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(taluqDTO));
    }

    /**
     * DELETE  /taluqs/:id : delete the "id" taluq.
     *
     * @param id the id of the taluqDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/taluqs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTaluq(@PathVariable Long id) {
        log.debug("REST request to delete Taluq : {}", id);
        taluqService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
