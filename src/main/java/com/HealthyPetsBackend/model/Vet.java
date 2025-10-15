package com.HealthyPetsBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Document("vets")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Vet {
  @Id private String id;
  @NotBlank private String nombre;
  private List<String> especialidades;
  private Instant createdAt;
  private Instant updatedAt;
}
