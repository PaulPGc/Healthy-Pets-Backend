package com.HealthyPetsBackend.controller;
import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController @RequestMapping("/api/health")
public class HealthController {
  @GetMapping public Map<String,Object> ping(){ return Map.of("ok", true, "ts", new Date()); }
}
