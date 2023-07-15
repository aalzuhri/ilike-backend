package aaz.ilike.bom.model;

import org.springframework.data.annotation.Reference;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review {
	private String userId;
	private String comment;
	private float note;
	@Reference private IUser author;
}
