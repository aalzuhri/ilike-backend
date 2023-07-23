package aaz.ilike.security.auth;

import aaz.ilike.bom.model.Cart;
import aaz.ilike.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String gender;
	private String phone;
	private String address;
//	private Cart cart;
	private String role;
}
