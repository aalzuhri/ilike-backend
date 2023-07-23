package aaz.ilike.security.config;


import aaz.ilike.bom.reps.GRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.InvalidClaimException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final GRepository repository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
//    	log.info(request.getServletPath());
//        if (request.getServletPath().startsWith("/aaz/ilike/api/private")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            var isTokenValid = repository.getTokenRep().findByToken(jwt)
//                    .map(t -> !t.isExpired() && !t.isRevoked())
//                    .orElse(false);
//            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
    	log.info("new request -> " + request.getServletPath());

//		if (request.getServletPath().startsWith(NusoftConstants.PublicServletPath)) { desabble security
		if (request.getServletPath().startsWith("/aaz/ilike/api/public")) {
			
			log.info("This is a public request -> authorized");
			filterChain.doFilter(request, response);
			return;
		}
      
        	
            String jwt = getJWTFromRequest(request);
          
            if (StringUtils.hasText(jwt)) {
            	
            	UsernamePasswordAuthenticationToken authentication = jwtService.isTokenValid(jwt);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
            
            	response.sendError(HttpStatus.FORBIDDEN.value(), "no token in authorization headers");
            }
       
    }
    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
