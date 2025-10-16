package com.HealthyPetsBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.HealthyPetsBackend.model.Schedule;

import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
  Optional<Schedule> findByVetId(String vetId);
}
