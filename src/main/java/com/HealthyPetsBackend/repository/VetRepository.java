package com.HealthyPetsBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.HealthyPetsBackend.model.Vet;

public interface VetRepository extends MongoRepository<Vet, String> {}
