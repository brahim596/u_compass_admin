package com.ucompass.ucompassadmin.service;

import com.ucompass.ucompassadmin.domain.CampusService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link CampusService}.
 */
public interface CampusServiceService {

    /**
     * Save a campusService.
     *
     * @param campusService the entity to save.
     * @return the persisted entity.
     */
    CampusService save(CampusService campusService);

    /**
     * Get all the campusServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CampusService> findAll(Pageable pageable);

    /**
     * Get the "id" campusService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampusService> findOne(Long id);

    /**
     * Delete the "id" campusService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
