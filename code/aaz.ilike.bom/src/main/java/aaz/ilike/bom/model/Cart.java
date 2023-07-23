package aaz.ilike.bom.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {
	@Id private String id;
	private List<Item> items;
}
