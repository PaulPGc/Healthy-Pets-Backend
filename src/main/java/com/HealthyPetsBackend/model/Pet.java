package com.HealthyPetsBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.Instant;

@Document("pets")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Pet {
  @Id private String id;
  @NotBlank private String nombre;
  @Pattern(regexp="perro|gato|otro") private String especie;
  @Min(0) private Integer edad;
  @Indexed private String ownerId; // opcional: dueño del paciente
  private Instant createdAt; private Instant updatedAt;
}
