package com.HealthyPetsBackend.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.HealthyPetsBackend.model.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {
  List<Pet> findByOwnerId(String ownerId);
}
