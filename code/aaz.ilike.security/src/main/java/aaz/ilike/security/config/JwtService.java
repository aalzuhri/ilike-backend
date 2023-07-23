package aaz.ilike.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import aaz.ilike.bom.model.Token;
import aaz.ilike.bom.reps.GRepository;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;
    private Algorithm alg;
    @Autowired
    private GRepository repository;
    
    @Autowired
    private UserDetailsService userDetailsService;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    public UsernamePasswordAuthenticationToken isTokenValid(String authToken) {
////        final String username = extractUsername(authToken);
////        return (username.equals(userDetails.getUsername())) && !isTokenExpired(authToken);
//    	
//    	 String secretKey = "6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878"; // Replace this with your actual secret key
//         Algorithm algorithm = Algorithm.HMAC256(getSignInKey().toString());
//         
//    	JWTVerifier verifier = JWT.require(algorithm).build();
//    	DecodedJWT infos = verifier.verify(authToken);
//    	String username = infos.getSubject();
//    	String[] roles = infos.getClaim("roles").asArray(String.class);
//    	Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//    	Arrays.stream(roles).forEach(role -> {
//    		authorities.add(new SimpleGrantedAuthority(role));
//    	});
//    	return new UsernamePasswordAuthenticationToken(username, null, authorities);
////    	return null;
//    }
    
    public UsernamePasswordAuthenticationToken isTokenValid(String authToken) {
    	Optional<Token> t= repository.getTokenRep().findByToken(authToken);
    	if(t.isPresent()) {
    		
    	
        final String username = extractUsername(authToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null && !isTokenExpired(authToken)) {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();
            DecodedJWT decodedJWT = verifier.verify(authToken);
//            String[] roles = [];
//            roles[0] = t.get().getUser().getRole().toString();
            
            String role = t.get().getUser().getRole();
            //decodedJWT.getClaim("role").asArray(String.class);
//            System.out.println(roles);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
             authorities.add(new SimpleGrantedAuthority(role));
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
    	}
        return null;
    }

    private Algorithm getAlgorithm() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Algorithm.HMAC256(keyBytes);
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
