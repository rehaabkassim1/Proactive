package com.etisalat.cea.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etisalat.cea.model.ProactiveAssurance;

@Repository
public interface ProactiveAssuranceRepository extends JpaRepository<ProactiveAssurance, BigDecimal> {

}
