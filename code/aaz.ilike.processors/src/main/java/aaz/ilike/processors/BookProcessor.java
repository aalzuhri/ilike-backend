package aaz.ilike.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aaz.ilike.bom.model.Book;
import aaz.ilike.bom.reps.GRepository;

@Component
public class BookProcessor {

	@Autowired
	GRepository repository;
	
	public Book createBook(Book b) {
		
		b.setId(UUID.randomUUID().toString());
		repository.getBookRep().save(b);
		return b;
	}

	public Book updateBook(Book b, String id) {
		b = repository.getBookRep().findById(id).orElse(null);
		
		if(b !=null) {
			b.setId(id);
			repository.getBookRep().save(b);
			return b;
		}
		return null;
	}

	public Book getBookById(String id) {
		Book b = repository.getBookRep().findById(id).orElse(null);
		if(b != null) {
			return b;
		}
		return null;
	}

	public List<Book> getAllBooks() {
		Iterable<Book> lst = repository.getBookRep().findAll();
		
		List<Book> books = new ArrayList<>();
		
		for (Book book : lst) {
			books.add(book);
		}
		
		return books;
	}
	
}
