package aaz.ilike.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.model.in.CategoryIn;
import aaz.ilike.api.model.out.CategoryOut;
import aaz.ilike.api.transformer.Transformer;
import aaz.ilike.bom.model.Category;
import aaz.ilike.processors.CategoryProcessor;

@RestController
public class CategoryController {
	@Autowired private CategoryProcessor processor;
	
	@PostMapping("/aaz/ilike/api/1.0/categories")
	public CategoryOut createCategory(@RequestBody CategoryIn ctg) {
		System.out.println("### ## # request to create a new category: " + ctg.getName());
		Category c = Transformer.CategoryA2B(ctg);
		c = processor.createCategory(c);
		return Transformer.CategoryB2A(c);
		
	}
	
	@GetMapping("/aaz/ilike/api/1.0/categories/all")
	public List<CategoryOut> getAllCategories() {
		System.out.println("### ## # request to retrieve all categories");
		List<Category> bcat = processor.getAllCategories();
		List<CategoryOut> res = new ArrayList<>();
		for (Category c : bcat)
			res.add(Transformer.CategoryB2A(c));
		return res;
	}
}
