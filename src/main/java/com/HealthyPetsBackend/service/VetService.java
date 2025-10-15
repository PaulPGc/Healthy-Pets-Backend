package com.HealthyPetsBackend.service;

import com.HealthyPetsBackend.model.Vet;
import com.HealthyPetsBackend.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VetService {
  private final VetRepository repo;
  public VetService(VetRepository r){ this.repo = r; }

  public List<Vet> list(){ return repo.findAll(); }
  public Vet get(String id){ return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Vet no encontrado")); }
  public Vet create(Vet v){
    v.setId(null); v.setCreatedAt(Instant.now()); v.setUpdatedAt(Instant.now());
    return repo.save(v);
  }
  public Vet update(String id, Vet v){
    var db = get(id);
    db.setNombre(v.getNombre()); db.setEspecialidades(v.getEspecialidades()); db.setUpdatedAt(Instant.now());
    return repo.save(db);
  }
  public void delete(String id){ repo.deleteById(id); }
}
