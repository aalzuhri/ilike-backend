package aaz.ilike.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import aaz.ilike.bom.model.IUser;
import aaz.ilike.bom.model.Token;
import aaz.ilike.bom.reps.GRepository;
import aaz.ilike.security.config.JwtService;
import aaz.ilike.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final GRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        IUser user = repository.getUserRep().findByEmail(request.getEmail());
        if(user != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the user already exist");
        }
        var createdUser = IUser.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("user")
                .build();
        var savedUser = repository.getUserRep().save(createdUser);
        var jwtToken = jwtService.generateToken(createdUser);
        var refreshToken = jwtService.generateRefreshToken(createdUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    	System.out.println(request.getEmail());
    	var user = repository.getUserRep().findByEmail(request.getEmail());
    	String password = passwordEncoder.encode(request.getPassword());
    	
    	if(passwordEncoder.matches(request.getPassword(),user.getPassword())) {
    		System.out.println("work");
    	}
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                		
//                        request.getEmail(),
//                        request.getPassword()
//                )
//                
//        );
        
//        var user = repository.getUserRep().findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
       
        var refreshToken = jwtService.generateRefreshToken(user);
       
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(IUser user, String jwtToken) {
        var token = Token.builder()
        		.id(UUID.randomUUID().toString())
                .user(user)
                .token(jwtToken)
                .tokenType("type")
                .expired(false)
                .revoked(false)
                .build();
        repository.getTokenRep().save(token);
    }

    private void revokeAllUserTokens(IUser user) {
    	
        var validUserTokens = repository.getTokenRep().findAllValidTokenByUser(user);
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        repository.getTokenRep().saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.getUserRep().findByEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
