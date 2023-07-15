package aaz.ilike.api.model.out;

import aaz.ilike.api.model.APIBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookOut extends APIBook {
	private String id;
	private String path;
}
