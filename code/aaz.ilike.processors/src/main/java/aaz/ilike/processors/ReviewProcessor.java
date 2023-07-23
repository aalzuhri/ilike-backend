package aaz.ilike.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aaz.ilike.bom.model.Review;
import aaz.ilike.bom.reps.GRepository;

@Component
public class ReviewProcessor {

	@Autowired private GRepository repository;

	public Review createReview(Review c) {
		c.setId(UUID.randomUUID().toString());
		c.setAuthor(repository.getUserRep().findByEmail(c.getUserId()));
		repository.getReviewRep().save(c);
		return c;
	}

	public List<Review> getAllReviews() {
		
		Iterable<Review> lst = repository.getReviewRep().findAll();
		
		List<Review> res = new ArrayList<>();
		
		for (Review review : lst) {
			res.add(review);
			
		}
		return res;
	}
}
