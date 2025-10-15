package com.HealthyPetsBackend.dto;
import jakarta.validation.constraints.*;
public class LoginRequest { @NotBlank public String usernameOrEmail; @NotBlank public String password; }
