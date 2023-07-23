package aaz.ilike.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.common.IlikeConstants;
import aaz.ilike.api.model.in.CategoryIn;
import aaz.ilike.api.model.in.ReviewIn;
import aaz.ilike.api.model.out.CategoryOut;
import aaz.ilike.api.model.out.ReviewOut;
import aaz.ilike.api.transformer.Transformer;
import aaz.ilike.bom.model.Category;
import aaz.ilike.bom.model.Review;
import aaz.ilike.processors.ReviewProcessor;

@RestController
public class ReviewController {

	@Autowired
	private ReviewProcessor processor;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(IlikeConstants.ReviewServletPath)
	public ResponseEntity<ReviewOut> createReview(@RequestBody ReviewIn rv) {
		Review c = Transformer.ReviewA2B(rv);
		c = processor.createReview(c);
		return ResponseEntity.ok().body(Transformer.ReviewB2A(c));
		
	}
	
	@GetMapping(IlikeConstants.GetAllReviewsServletPath)
	public ResponseEntity<List<ReviewOut>> getAllreviews() {
		List<Review> lst = processor.getAllReviews();
		List<ReviewOut> res = new ArrayList<>();
		
		lst.stream().forEach(c->res.add(Transformer.ReviewB2A(c)));
		return ResponseEntity.ok().body(res);
		
	}
	
}
