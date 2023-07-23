package aaz.ilike.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class APIUser {

    private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String gender;
	private String phone;
	private String address;
	private String role;
}
