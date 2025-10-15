package com.HealthyPetsBackend.dto;
import jakarta.validation.constraints.*;
public class RegisterRequest {
  @NotBlank public String username;
  @Email @NotBlank public String email;
  @NotBlank @Size(min=6) public String password;
}
