package com.HealthyPetsBackend.service;
import com.HealthyPetsBackend.model.User;
import com.HealthyPetsBackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
  private final UserRepository repo; private final PasswordEncoder enc;
  public UserService(UserRepository r, PasswordEncoder e){ repo=r; enc=e; }

  public User register(String username,String email,String raw){
    if (repo.existsByUsername(username)) throw new IllegalArgumentException("Username ya existe");
    if (repo.existsByEmail(email)) throw new IllegalArgumentException("Email ya existe");
    return repo.save(User.builder().username(username).email(email).passwordHash(enc.encode(raw)).role("USER").build());
  }
  public Optional<User> findByUsernameOrEmail(String v){ return repo.findByUsername(v).or(()->repo.findByEmail(v)); }
  public boolean checkPassword(User u,String raw){ return enc.matches(raw, u.getPasswordHash()); }
}
