package com.HealthyPetsBackend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*; import lombok.*;

@Document("users") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id private String id;
  @NotBlank @Indexed(unique = true) private String username;
  @Email @NotBlank @Indexed(unique = true) private String email;
  @NotBlank private String passwordHash;
  private String role; // USER | ADMIN
}