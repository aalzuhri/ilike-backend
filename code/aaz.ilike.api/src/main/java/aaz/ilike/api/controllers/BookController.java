package aaz.ilike.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.common.IlikeConstants;
import aaz.ilike.api.model.in.BookIn;
import aaz.ilike.api.model.out.BookOut;
import aaz.ilike.api.transformer.Transformer;
import aaz.ilike.bom.model.Book;
import aaz.ilike.processors.BookProcessor;

@RestController
public class BookController {
	@Autowired BookProcessor processor;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(IlikeConstants.CreateBookServletPath)
	public ResponseEntity<BookOut> createBook(@RequestBody BookIn bin) {
		Book b = Transformer.BookA2B(bin);
		b = processor.createBook(b);
		return ResponseEntity.ok().body(Transformer.BookB2A(b));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(IlikeConstants.CreateBookServletPath)
	public ResponseEntity<BookOut> updateBook(@RequestBody BookIn bin, @RequestParam("id") String id) {
		Book b = Transformer.BookA2B(bin);
	    b  = processor.updateBook(b, id);
	    
		return ResponseEntity.ok().body(b != null ?Transformer.BookB2A(b):null);
	}
	
	@GetMapping(IlikeConstants.BookByIdServletPath)
	public ResponseEntity<BookOut> getBookById(@RequestParam("id") String id) {
		return ResponseEntity.ok().body(Transformer.BookB2A(processor.getBookById(id)));
	}
	
	@GetMapping(IlikeConstants.AllBooksServletPath)
	public ResponseEntity<List<BookOut>> getAllBooks() {
		
		List<BookOut> res = new ArrayList<>();
		List<Book> lst = processor.getAllBooks();
		
		lst.stream().forEach(b->res.add(Transformer.BookB2A(b)));
		
		return ResponseEntity.ok().body(res);
	}
}
