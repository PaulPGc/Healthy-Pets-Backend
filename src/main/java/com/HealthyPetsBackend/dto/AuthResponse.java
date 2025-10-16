package com.HealthyPetsBackend.dto;
public class AuthResponse {
  public String token, username, role;
  public AuthResponse(String t,String u,String r){ token=t; username=u; role=r; }
}

