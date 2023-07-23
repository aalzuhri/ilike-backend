package aaz.ilike.api.model.in;

import java.util.List;

import aaz.ilike.api.model.APIOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderIn extends APIOrder{

	private String userId;
	private List<ItemIn> items;
}
