package aaz.ilike.processors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aaz.ilike.bom.model.Category;
import aaz.ilike.bom.reps.GRepository;

@Component
public class CategoryProcessor {
	@Autowired private GRepository rep;
	
	public Category createCategory(Category c) {
		Category res = rep.getCategoryRep().findById(c.getName()).orElse(null);
		if (res == null) {
			res = new Category();
			res.setName(c.getName());
			rep.getCategoryRep().save(res);
		}
		return res;
	}
	
	public List<Category> getAllCategories() {
		Iterable<Category> cts = rep.getCategoryRep().findAll();
		ArrayList<Category> res = new ArrayList<>();
		cts.forEach(res::add);
		return res;
	}
}
