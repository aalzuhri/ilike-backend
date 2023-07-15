package aaz.ilike.bom.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="orders")
public class Order {
	@Id private String orderId;
	@Reference private IUser user;
	private long orderDate;
	private double total;
	private String status;
	private List<Item> items;
}
