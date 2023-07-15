package aaz.ilike.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.model.in.BookIn;
import aaz.ilike.api.model.out.BookOut;
import aaz.ilike.processors.BookProcessor;

@RestController
public class BookController {
	@Autowired BookProcessor processor;
	
	@PostMapping("/aaz/ilike/api/1.0/books")
	public BookOut createBook(@RequestBody BookIn bin) {
		return null;
	}
}
