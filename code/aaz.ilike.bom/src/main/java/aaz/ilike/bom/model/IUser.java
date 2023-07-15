package aaz.ilike.bom.model;

import org.springframework.data.annotation.Id;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="iuser")
public class IUser {
	@Id private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String gender;
	private String phone;
	private String address;
	private Cart cart;
}
