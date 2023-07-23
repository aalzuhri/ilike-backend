package aaz.ilike.security.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aaz/ilike/api")
public class DemoController {
	@Autowired AuthenticationManager authenticationManager;
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
    
//    @PostMapping("/aaz/ilike/api/public/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//    	try {
//    		UsernamePasswordAuthenticationToken atk = new UsernamePasswordAuthenticationToken(email, password);
//    		Authentication authentication = authenticationManager.authenticate(atk);
//    		
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = tokenProvider.generateToken(authentication);
//            List<Contact> all = rep.getContactRep().findByEmail(email);
//            System.out.println(all.get(0).getEmail());
//            if ((all == null) || (all.size() <= 0))
//            	throw new UsernameNotFoundException(email);
//            
//            Contact c = all.get(0);
//            FinancialAccount f = rep.getFaRep().findByContact(c);
//            Subscription s = rep.getSubscriptionRep().findByContact(c);
//            APIUserOut lr = ModelTransformer.UserModel2API(c,f,s);
//            List<UserRole> mbs = rep.getUserRoleRep().findByContact(c);
//            
//            mbs.stream().forEach(m -> lr.addRole(m.getRole().getName()));
//            lr.setType(c.getType());
//            
//            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(lr);
//    	} catch (InternalAuthenticationServiceException e) {
//    		throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("the user %s is not defined", email));
//    	} catch (BadCredentialsException bce) {
//    		throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("wrong password for the user %s", email));
//    	}   
//    }
}
