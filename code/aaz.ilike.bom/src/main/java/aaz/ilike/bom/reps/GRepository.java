package aaz.ilike.bom.reps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class GRepository {
	@Autowired private CategoryRepository categoryRep;
	@Autowired private BookRepository bookRep;
	@Autowired private OrderRepository orderRep;
	@Autowired private UserRepository userRep;
	@Autowired private TokenRepository tokenRep;
	@Autowired private ReviewRepository reviewRep;
	@Autowired private ItemRepository itemRep;
}
