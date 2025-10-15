package com.HealthyPetsBackend.service;

import com.HealthyPetsBackend.model.Pet;
import com.HealthyPetsBackend.repository.PetRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
public class PetService {
  private final PetRepository repo;
  public PetService(PetRepository r){ this.repo=r; }

  public List<Pet> list(String ownerId){
    return ownerId!=null ? repo.findByOwnerId(ownerId) : repo.findAll();
  }
  public Pet get(String id){ return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Pet no encontrado")); }
  public Pet create(Pet p){
    p.setId(null); p.setCreatedAt(Instant.now()); p.setUpdatedAt(Instant.now());
    return repo.save(p);
  }
  public Pet update(String id, Pet p){
    var db = get(id);
    db.setNombre(p.getNombre()); db.setEspecie(p.getEspecie()); db.setEdad(p.getEdad());
    db.setOwnerId(p.getOwnerId()); db.setUpdatedAt(Instant.now());
    return repo.save(db);
  }
  public void delete(String id){ repo.deleteById(id); }
}
