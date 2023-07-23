package aaz.ilike.api.model.out;

import aaz.ilike.api.model.APIReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewOut extends APIReview{
	private String id;
	private UserOut user;

}
