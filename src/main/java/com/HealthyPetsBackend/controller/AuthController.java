package com.HealthyPetsBackend.controller;
import com.HealthyPetsBackend.dto.*; import com.HealthyPetsBackend.security.JwtService; import com.HealthyPetsBackend.service.UserService;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
  private final UserService users; private final JwtService jwt;
  public AuthController(UserService u, JwtService j){ users=u; jwt=j; }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest dto){
    var created=users.register(dto.username,dto.email,dto.password);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(new AuthResponse(jwt.generate(created.getUsername()), created.getUsername(), created.getRole()));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest dto){
    var opt=users.findByUsernameOrEmail(dto.usernameOrEmail);
    if (opt.isEmpty() || !users.checkPassword(opt.get(), dto.password))
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error("Credenciales inválidas"));
    var u=opt.get();
    return ResponseEntity.ok(new AuthResponse(jwt.generate(u.getUsername()), u.getUsername(), u.getRole()));
  }

  record Error(String error){}
}
