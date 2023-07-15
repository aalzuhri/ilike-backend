package aaz.ilike.bom.model;

import java.util.List;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {
	private List<Item> items;
}
