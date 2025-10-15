package com.HealthyPetsBackend.security;
import java.util.Date; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import io.jsonwebtoken.*;

@Service
public class JwtService {
  @Value("${app.jwt.secret}") private String secret;
  @Value("${app.jwt.expiration}") private long expMs;

  public String generate(String subject){
    var now=new Date();
    return Jwts.builder().setSubject(subject).setIssuedAt(now)
      .setExpiration(new Date(now.getTime()+expMs))
      .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
      .compact();
  }
  public String validateAndGetSubject(String token){
    return Jwts.parserBuilder().setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secret.getBytes()))
      .build().parseClaimsJws(token).getBody().getSubject();
  }
}
