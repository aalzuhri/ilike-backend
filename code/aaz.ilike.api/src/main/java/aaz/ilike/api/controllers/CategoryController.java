package aaz.ilike.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.common.IlikeConstants;
import aaz.ilike.api.model.in.CategoryIn;
import aaz.ilike.api.model.out.CategoryOut;
import aaz.ilike.api.transformer.Transformer;
import aaz.ilike.bom.model.Category;
import aaz.ilike.processors.CategoryProcessor;

@RestController
public class CategoryController {
	@Autowired private CategoryProcessor processor;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(IlikeConstants.CreateBookServletPath)
	public ResponseEntity<CategoryOut> createCategory(@RequestBody CategoryIn ctg) {
		System.out.println("### ## # request to create a new category: " + ctg.getName());
		Category c = Transformer.CategoryA2B(ctg);
		c = processor.createCategory(c);
		return ResponseEntity.ok().body(Transformer.CategoryB2A(c));
		
	}
	
	@GetMapping(IlikeConstants.AllCategoriesServletPath)
	public ResponseEntity<List<CategoryOut>> getAllCategories() {
		System.out.println("### ## # request to retrieve all categories");
		List<Category> bcat = processor.getAllCategories();
		List<CategoryOut> res = new ArrayList<>();
		for (Category c : bcat)
			res.add(Transformer.CategoryB2A(c));
		return ResponseEntity.ok().body(res);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(IlikeConstants.DeleteCategoryServletPath)
	public ResponseEntity<CategoryOut> deleteCategoty(@RequestParam String category) {
		System.out.println("### ## # request to delete "+category);
		Category bcat = processor.deleteCategory(category);
		if(bcat == null) 
			return null;
		
		CategoryOut res = Transformer.CategoryB2A(bcat);
		
		return ResponseEntity.ok().body(res);
	}
}
