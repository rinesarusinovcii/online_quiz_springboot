
package com.rinesarusinovci.online_quizzes_vue_back.services.impls;



import com.rinesarusinovci.online_quizzes_vue_back.dto.RegisterUserDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.UserRepository;
import com.rinesarusinovci.online_quizzes_vue_back.security.AppUserDetails;
import com.rinesarusinovci.online_quizzes_vue_back.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long expireTimeInMs = 86400000L; // 1 day

    @Override
    public UserDetails login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        AppUserDetails appUserDetails = (AppUserDetails) userDetails;
        User user = appUserDetails.getUser();

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        claims.put("id", ((AppUserDetails) userDetails).getUser().getId());
        claims.put("name", ((AppUserDetails) userDetails).getUser().getName());
        claims.put("surname", ((AppUserDetails) userDetails).getUser().getSurname());
        claims.put("role", ((AppUserDetails) userDetails).getUser().getRole().name());
        claims.put("username", ((AppUserDetails) userDetails).getUser().getUsername());


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // "sub": email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeInMs)) // 24h
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public UserDetails register(RegisterUserDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ky email është i regjistruar!");
        }

        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ky username është i regjistruar!");
        }



        User newUser = new User();
        newUser.setName(registerDto.getName());
        newUser.setSurname(registerDto.getSurname());
        newUser.setBirthdate(registerDto.getBirthdate());
        newUser.setUsername(registerDto.getUsername());
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();
        newUser.setEmail(email);
        newUser.setRole(registerDto.getRole());

        newUser.setPassword(passwordEncoder.encode(password));


        userRepository.save(newUser);


        return userDetailsService.loadUserByUsername(email);
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public UserDetails validateToken(String token) {
        String email = extractEmail(token);

        return userDetailsService.loadUserByUsername(email);
    }
}
