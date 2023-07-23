package aaz.ilike.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class APIReview {

	private String userId;
	private String comment;
	private float note;
}
