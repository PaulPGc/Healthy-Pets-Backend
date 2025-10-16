package com.HealthyPetsBackend.controller;

import com.HealthyPetsBackend.model.Vet;
import com.HealthyPetsBackend.service.VetService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/api/vets")
public class VetsController {
  private final VetService service;
  public VetsController(VetService s){ this.service = s; }

  @GetMapping public List<Vet> list(){ return service.list(); }
  @GetMapping("/{id}") public Vet get(@PathVariable String id){ return service.get(id); }

  @PostMapping public ResponseEntity<Vet> create(@Valid @RequestBody Vet body){
    var saved = service.create(body);
    return ResponseEntity.created(URI.create("/api/vets/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}") public Vet update(@PathVariable String id, @Valid @RequestBody Vet body){ return service.update(id, body); }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id){ service.delete(id); }
}
