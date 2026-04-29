package se.twowall.notes.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// https://www.geeksforgeeks.org/springboot/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/
public class JwtAuthFilter extends OncePerRequestFilter {

     private final String SECRET_KEY = "SupermegahemligsupernyckelSomArMinst32TeckenLAng!!!"; // oh no leak

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FILTER HIT: " + request.getRequestURI());
        System.out.println("AUTH HEADER: " + request.getHeader("Authorization"));

        String path = request.getRequestURI();

        if (path.startsWith("/api/login") || path.startsWith("/api/register")) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

}
