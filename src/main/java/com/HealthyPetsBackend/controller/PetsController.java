package com.HealthyPetsBackend.controller;

import com.HealthyPetsBackend.model.Pet;
import com.HealthyPetsBackend.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;

@RestController @RequestMapping("/api/pets")
public class PetsController {
  private final PetService service;
  public PetsController(PetService s){ this.service=s; }

  @GetMapping public List<Pet> list(@RequestParam(required=false) String ownerId){ return service.list(ownerId); }
  @GetMapping("/{id}") public Pet get(@PathVariable String id){ return service.get(id); }

  @PostMapping public ResponseEntity<Pet> create(@Valid @RequestBody Pet body){
    var saved = service.create(body);
    return ResponseEntity.created(URI.create("/api/pets/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}") public Pet update(@PathVariable String id, @Valid @RequestBody Pet body){ return service.update(id, body); }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id){ service.delete(id); }
}
