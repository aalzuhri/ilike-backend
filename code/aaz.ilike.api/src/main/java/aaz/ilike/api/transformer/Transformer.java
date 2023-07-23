package aaz.ilike.api.transformer;

import aaz.ilike.api.model.in.BookIn;
import aaz.ilike.api.model.in.CategoryIn;
import aaz.ilike.api.model.in.ReviewIn;
import aaz.ilike.api.model.in.UserIn;
import aaz.ilike.api.model.out.BookOut;
import aaz.ilike.api.model.out.CategoryOut;
import aaz.ilike.api.model.out.ReviewOut;
import aaz.ilike.api.model.out.UserOut;
import aaz.ilike.bom.model.Book;
import aaz.ilike.bom.model.Category;
import aaz.ilike.bom.model.IUser;
import aaz.ilike.bom.model.Review;

public class Transformer {
	public static Book BookA2B(BookIn b) {
		Book res = new Book();
		
		res.setAuthors(b.getAuthors());
		res.setBookAbstract(b.getBookAbstract());
		res.setEditor(b.getEditor());
		res.setPrice(b.getPrice());
		res.setTitle(b.getTitle());
		res.setYear(b.getYear());
		res.setPath(b.getPath());
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
	
	public static IUser IuserA2B(UserIn c) {
		IUser res = new IUser();
		res.setAddress(c.getAddress());
		res.setCart(null);
		res.setEmail(c.getEmail());
		res.setFirstname(c.getFirstname());
		res.setGender(c.getGender());
		res.setLastname(c.getLastname());
		res.setPassword(null);
		res.setPhone(c.getPhone());
		res.setRole(c.getRole());
		return res;
	}
	
	public static UserOut UserB2A(IUser c) {
		UserOut res = new UserOut();
		res.setAddress(c.getAddress());
		res.setAddress(c.getAddress());
		res.setEmail(c.getEmail());
		res.setFirstname(c.getFirstname());
		res.setGender(c.getGender());
		res.setLastname(c.getLastname());
		res.setPassword(null);
		res.setPhone(c.getPhone());
		res.setRole(c.getRole());
		return res;
	}
	
	public static Review ReviewA2B(ReviewIn c) {
		Review res = new Review();
		res.setComment(c.getComment());
		res.setNote(c.getNote());
		res.setUserId(c.getUserId());
		return res;
	}
	
	public static ReviewOut ReviewB2A(Review c) {
		ReviewOut res = new ReviewOut();
		res.setComment(c.getComment());
		res.setId(c.getId());
		res.setNote(c.getNote());
		res.setUser(UserB2A(c.getAuthor()));
		res.setUserId(c.getUserId());
		return res;
	}
}
