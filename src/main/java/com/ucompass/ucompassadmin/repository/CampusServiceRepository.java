package com.ucompass.ucompassadmin.repository;

import com.ucompass.ucompassadmin.domain.CampusService;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CampusService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampusServiceRepository extends JpaRepository<CampusService, Long> {

}
