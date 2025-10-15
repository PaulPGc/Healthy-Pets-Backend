package com.HealthyPetsBackend.service;

import com.HealthyPetsBackend.model.Schedule;
import com.HealthyPetsBackend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ScheduleService {
  private final ScheduleRepository repo;
  public ScheduleService(ScheduleRepository r){ this.repo = r; }

  public Schedule getOrCreateByVet(String vetId){
    return repo.findByVetId(vetId).orElseGet(() -> {
      var s = Schedule.builder().vetId(vetId).createdAt(Instant.now()).updatedAt(Instant.now()).build();
      return repo.save(s);
    });
  }

  public List<Schedule.Slot> listSlots(String vetId, Instant from, Instant to){
    var sch = getOrCreateByVet(vetId);
    return sch.getSlots().stream()
      .filter(s -> (from==null || !s.getTo().isBefore(from)) && (to==null || !s.getFrom().isAfter(to)))
      .toList();
  }

  public Schedule addSlot(String vetId, Schedule.Slot slot){
    var sch = getOrCreateByVet(vetId);
    sch.getSlots().add(slot);
    sch.setUpdatedAt(Instant.now());
    return repo.save(sch);
  }

  public Schedule removeSlot(String vetId, int index){
    var sch = getOrCreateByVet(vetId);
    if (index >= 0 && index < sch.getSlots().size()){
      sch.getSlots().remove(index);
      sch.setUpdatedAt(Instant.now());
      return repo.save(sch);
    }
    throw new IndexOutOfBoundsException("slot no encontrado");
  }
}
