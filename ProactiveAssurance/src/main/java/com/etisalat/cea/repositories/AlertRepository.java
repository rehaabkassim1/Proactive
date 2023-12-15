package com.etisalat.cea.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.etisalat.cea.model.Alert;

public interface AlertRepository extends MongoRepository<Alert, String> {

}
