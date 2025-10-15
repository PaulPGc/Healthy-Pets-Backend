package com.HealthyPetsBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document("schedules")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Schedule {
  @Id private String id;

  @Indexed @NotBlank
  private String vetId;

  @Builder.Default
  private List<Slot> slots = new ArrayList<>();

  private Instant createdAt;
  private Instant updatedAt;

  @Data @NoArgsConstructor @AllArgsConstructor @Builder
  public static class Slot {
    private Instant from;
    private Instant to;
  }
}
