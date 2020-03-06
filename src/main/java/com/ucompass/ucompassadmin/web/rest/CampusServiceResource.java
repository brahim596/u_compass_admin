package com.ucompass.ucompassadmin.web.rest;

import com.ucompass.ucompassadmin.domain.CampusService;
import com.ucompass.ucompassadmin.service.CampusServiceService;
import com.ucompass.ucompassadmin.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ucompass.ucompassadmin.domain.CampusService}.
 */
@RestController
@RequestMapping("/api")
public class CampusServiceResource {

    private final Logger log = LoggerFactory.getLogger(CampusServiceResource.class);

    private static final String ENTITY_NAME = "campusService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampusServiceService campusServiceService;

    public CampusServiceResource(CampusServiceService campusServiceService) {
        this.campusServiceService = campusServiceService;
    }

    /**
     * {@code POST  /campus-services} : Create a new campusService.
     *
     * @param campusService the campusService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campusService, or with status {@code 400 (Bad Request)} if the campusService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campus-services")
    public ResponseEntity<CampusService> createCampusService(@RequestBody CampusService campusService) throws URISyntaxException {
        log.debug("REST request to save CampusService : {}", campusService);
        if (campusService.getId() != null) {
            throw new BadRequestAlertException("A new campusService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampusService result = campusServiceService.save(campusService);
        return ResponseEntity.created(new URI("/api/campus-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campus-services} : Updates an existing campusService.
     *
     * @param campusService the campusService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campusService,
     * or with status {@code 400 (Bad Request)} if the campusService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campusService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campus-services")
    public ResponseEntity<CampusService> updateCampusService(@RequestBody CampusService campusService) throws URISyntaxException {
        log.debug("REST request to update CampusService : {}", campusService);
        if (campusService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampusService result = campusServiceService.save(campusService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campusService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campus-services} : get all the campusServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campusServices in body.
     */
    @GetMapping("/campus-services")
    public ResponseEntity<List<CampusService>> getAllCampusServices(Pageable pageable) {
        log.debug("REST request to get a page of CampusServices");
        Page<CampusService> page = campusServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /campus-services/:id} : get the "id" campusService.
     *
     * @param id the id of the campusService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campusService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campus-services/{id}")
    public ResponseEntity<CampusService> getCampusService(@PathVariable Long id) {
        log.debug("REST request to get CampusService : {}", id);
        Optional<CampusService> campusService = campusServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campusService);
    }

    /**
     * {@code DELETE  /campus-services/:id} : delete the "id" campusService.
     *
     * @param id the id of the campusService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campus-services/{id}")
    public ResponseEntity<Void> deleteCampusService(@PathVariable Long id) {
        log.debug("REST request to delete CampusService : {}", id);
        campusServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
