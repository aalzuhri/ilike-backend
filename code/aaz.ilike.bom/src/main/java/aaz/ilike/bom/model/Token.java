package aaz.ilike.bom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Token {

  @Id
  public String id;
  public String token;
  public String tokenType;

  public boolean revoked;

  public boolean expired;
  @Reference private IUser user;
  
  public boolean isRevoked() {
	  return revoked;
  }
  
  public boolean isExpired() {
	  return expired;
  }
}

