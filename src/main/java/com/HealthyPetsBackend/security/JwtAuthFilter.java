package com.HealthyPetsBackend.security;
import com.HealthyPetsBackend.model.User; import com.HealthyPetsBackend.repository.UserRepository;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*; import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Component;
import java.io.IOException; import java.util.List;

@Component
public class JwtAuthFilter extends GenericFilter {
  private final JwtService jwt; private final UserRepository users;
  public JwtAuthFilter(JwtService j, UserRepository u){ jwt=j; users=u; }
  @Override public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    var r=(HttpServletRequest)req; var h=r.getHeader("Authorization");
    if(h!=null && h.startsWith("Bearer ")){
      var t=h.substring(7);
      try{
        var sub=jwt.validateAndGetSubject(t); // username
        User u=users.findByUsername(sub).orElse(null);
        if(u!=null){
          var auths=List.of(new SimpleGrantedAuthority("ROLE_"+(u.getRole()==null?"USER":u.getRole())));
          var a=new UsernamePasswordAuthenticationToken(u.getUsername(),null,auths);
          SecurityContextHolder.getContext().setAuthentication(a);
        }
      }catch(Exception ignore){}
    }
    chain.doFilter(req,res);
  }
}
