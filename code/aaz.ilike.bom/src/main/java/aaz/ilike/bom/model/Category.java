package aaz.ilike.bom.model;

import org.springframework.data.annotation.Id;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="category")
public class Category {
	@Id private String name;
}
