package com.ucompass.ucompassadmin.repository;

import com.ucompass.ucompassadmin.domain.Evenement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Evenement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
