package com.HealthyPetsBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Document("appointments")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Appointment {
  @Id private String id;

  @Indexed @NotBlank private String petId;
  @Indexed @NotBlank private String vetId;

  @NotNull private Instant start;
  @NotNull private Instant end;

  private String estado; // programada | cancelada | atendida
  private String notas;

  private Instant createdAt;
  private Instant updatedAt;
}

