package aaz.ilike.api.transformer;

import aaz.ilike.api.model.in.BookIn;
import aaz.ilike.api.model.in.CategoryIn;
import aaz.ilike.api.model.out.BookOut;
import aaz.ilike.api.model.out.CategoryOut;
import aaz.ilike.bom.model.Book;
import aaz.ilike.bom.model.Category;

public class Transformer {
	public static Book BookA2B(BookIn b) {
		Book res = new Book();
		
		res.setAuthors(b.getAuthors());
		res.setBookAbstract(b.getBookAbstract());
		res.setEditor(b.getEditor());
		res.setPrice(b.getPrice());
		res.setTitle(b.getTitle());
		res.setYear(b.getYear());
		res.setCategories(b.getCategories());
		
		return res;
	}
	
	public static BookOut BookB2A(Book b) {
		BookOut res = new BookOut();
		
		res.setAuthors(b.getAuthors());
		res.setBookAbstract(b.getBookAbstract());
		res.setEditor(b.getEditor());
		res.setPrice(b.getPrice());
		res.setTitle(b.getTitle());
		res.setYear(b.getYear());
		res.setCategories(b.getCategories());
		res.setId(b.getId());
		res.setPath(b.getPath());
		
		return res;
	}

	public static Category CategoryA2B(CategoryIn c) {
		Category res = new Category();
		res.setName(c.getName());
		return res;
	}
	
	public static CategoryOut CategoryB2A(Category c) {
		CategoryOut res = new CategoryOut();
		res.setName(c.getName());
		return res;
	}
}
