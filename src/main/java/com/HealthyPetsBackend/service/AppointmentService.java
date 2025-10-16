package com.HealthyPetsBackend.service;

import com.HealthyPetsBackend.model.Appointment;
import com.HealthyPetsBackend.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentService {
  private final AppointmentRepository repo;
  public AppointmentService(AppointmentRepository r){ this.repo = r; }

  public List<Appointment> list(String vetId, String petId, Instant from, Instant to){
    var all = repo.findAll();
    return all.stream().filter(a ->
        (vetId==null || vetId.equals(a.getVetId())) &&
        (petId==null || petId.equals(a.getPetId())) &&
        (from==null || a.getEnd().isAfter(from)) &&
        (to==null || a.getStart().isBefore(to))
    ).toList();
  }

  public Appointment get(String id){
    return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Appointment no encontrado"));
  }

  public Appointment create(Appointment a){
    if (!repo.overlaps(a.getVetId(), a.getStart(), a.getEnd()).isEmpty())
      throw new IllegalArgumentException("El veterinario ya tiene una cita en ese horario");
    a.setEstado(a.getEstado()==null ? "programada" : a.getEstado());
    a.setCreatedAt(Instant.now()); a.setUpdatedAt(Instant.now());
    return repo.save(a);
  }

  public Appointment update(String id, Appointment a){
    var db = get(id);
    db.setStart(a.getStart()); db.setEnd(a.getEnd());
    db.setNotas(a.getNotas());
    db.setEstado(a.getEstado()==null? db.getEstado(): a.getEstado());
    db.setUpdatedAt(Instant.now());
    return repo.save(db);
  }

  public void delete(String id){ repo.deleteById(id); }
}
