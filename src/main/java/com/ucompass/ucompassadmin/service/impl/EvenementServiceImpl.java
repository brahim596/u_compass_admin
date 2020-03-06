package com.ucompass.ucompassadmin.service.impl;

import com.ucompass.ucompassadmin.service.EvenementService;
import com.ucompass.ucompassadmin.domain.Evenement;
import com.ucompass.ucompassadmin.repository.EvenementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Evenement}.
 */
@Service
@Transactional
public class EvenementServiceImpl implements EvenementService {

    private final Logger log = LoggerFactory.getLogger(EvenementServiceImpl.class);

    private final EvenementRepository evenementRepository;

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    /**
     * Save a evenement.
     *
     * @param evenement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Evenement save(Evenement evenement) {
        log.debug("Request to save Evenement : {}", evenement);
        return evenementRepository.save(evenement);
    }

    /**
     * Get all the evenements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Evenement> findAll() {
        log.debug("Request to get all Evenements");
        return evenementRepository.findAll();
    }

    /**
     * Get one evenement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Evenement> findOne(Long id) {
        log.debug("Request to get Evenement : {}", id);
        return evenementRepository.findById(id);
    }

    /**
     * Delete the evenement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Evenement : {}", id);
        evenementRepository.deleteById(id);
    }
}
