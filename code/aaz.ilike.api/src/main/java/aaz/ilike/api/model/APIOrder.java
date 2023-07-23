package aaz.ilike.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

import aaz.ilike.bom.model.IUser;
import aaz.ilike.bom.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class APIOrder {
	private long orderDate;
	private double total;
	private String status;
}
