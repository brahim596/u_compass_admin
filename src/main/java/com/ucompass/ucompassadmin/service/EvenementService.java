package com.ucompass.ucompassadmin.service;

import com.ucompass.ucompassadmin.domain.Evenement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Evenement}.
 */
public interface EvenementService {

    /**
     * Save a evenement.
     *
     * @param evenement the entity to save.
     * @return the persisted entity.
     */
    Evenement save(Evenement evenement);

    /**
     * Get all the evenements.
     *
     * @return the list of entities.
     */
    List<Evenement> findAll();

    /**
     * Get the "id" evenement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Evenement> findOne(Long id);

    /**
     * Delete the "id" evenement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
