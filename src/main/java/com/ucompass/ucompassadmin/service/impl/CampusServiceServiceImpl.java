package com.ucompass.ucompassadmin.service.impl;

import com.ucompass.ucompassadmin.service.CampusServiceService;
import com.ucompass.ucompassadmin.domain.CampusService;
import com.ucompass.ucompassadmin.repository.CampusServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CampusService}.
 */
@Service
@Transactional
public class CampusServiceServiceImpl implements CampusServiceService {

    private final Logger log = LoggerFactory.getLogger(CampusServiceServiceImpl.class);

    private final CampusServiceRepository campusServiceRepository;

    public CampusServiceServiceImpl(CampusServiceRepository campusServiceRepository) {
        this.campusServiceRepository = campusServiceRepository;
    }

    /**
     * Save a campusService.
     *
     * @param campusService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampusService save(CampusService campusService) {
        log.debug("Request to save CampusService : {}", campusService);
        return campusServiceRepository.save(campusService);
    }

    /**
     * Get all the campusServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CampusService> findAll(Pageable pageable) {
        log.debug("Request to get all CampusServices");
        return campusServiceRepository.findAll(pageable);
    }

    /**
     * Get one campusService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampusService> findOne(Long id) {
        log.debug("Request to get CampusService : {}", id);
        return campusServiceRepository.findById(id);
    }

    /**
     * Delete the campusService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampusService : {}", id);
        campusServiceRepository.deleteById(id);
    }
}
