package com.HealthyPetsBackend.repository;

import com.HealthyPetsBackend.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
  List<Appointment> findByVetId(String vetId);
  List<Appointment> findByPetId(String petId);

  // citas que se solapan con [from, to) para un vet
  @Query("{ 'vetId': ?0, 'start': { $lt: ?2 }, 'end': { $gt: ?1 } }")
  List<Appointment> overlaps(String vetId, Instant from, Instant to);
}
