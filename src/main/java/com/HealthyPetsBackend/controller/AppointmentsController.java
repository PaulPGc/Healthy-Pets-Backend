package com.HealthyPetsBackend.controller;

import com.HealthyPetsBackend.model.Appointment;
import com.HealthyPetsBackend.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController @RequestMapping("/api/appointments")
public class AppointmentsController {
  private final AppointmentService service;
  public AppointmentsController(AppointmentService s){ this.service = s; }

  @GetMapping
  public List<Appointment> list(@RequestParam(required=false) String vetId,
                                @RequestParam(required=false) String petId,
                                @RequestParam(required=false) Instant from,
                                @RequestParam(required=false) Instant to){
    return service.list(vetId, petId, from, to);
  }

  @GetMapping("/{id}") public Appointment get(@PathVariable String id){ return service.get(id); }

  @PostMapping
  public ResponseEntity<Appointment> create(@Valid @RequestBody Appointment body){
    var saved = service.create(body);
    return ResponseEntity.created(URI.create("/api/appointments/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}") public Appointment update(@PathVariable String id, @Valid @RequestBody Appointment body){
    return service.update(id, body);
  }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id){ service.delete(id); }
}
