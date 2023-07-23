package aaz.ilike.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aaz.ilike.api.common.IlikeConstants;
import aaz.ilike.api.model.in.ItemIn;
import aaz.ilike.api.model.in.OrderIn;
import aaz.ilike.bom.model.Item;
import aaz.ilike.bom.model.Order;
import aaz.ilike.bom.reps.GRepository;
import aaz.ilike.processors.OrderProcessor;

@RestController
public class OrderController {

	@Autowired
	private OrderProcessor processor;
	
	@Autowired
	private GRepository repository;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(IlikeConstants.MakeOrderServletPath)
	public ResponseEntity<String> makeOrder(@RequestBody OrderIn ord) {

		Order c = new Order();
		c.setOrderId(UUID.randomUUID().toString());
		c.setOrderDate(ord.getOrderDate());
		c.setStatus(c.getStatus());
		c.setTotal(ord.getTotal());
		c.setUser(repository.getUserRep().findByEmail(ord.getUserId()));
		
		// add items
		List<ItemIn> lstItems = ord.getItems();
		List<Item> items = new ArrayList<>();
		
		for (ItemIn itemIn : lstItems) {
			Item item = new Item();
			item.setId(UUID.randomUUID().toString());
			item.setQuantity(itemIn.getQuantity());
			item.setBook(repository.getBookRep().findById(itemIn.getBook()).get());
			repository.getItemRep().save(item);
			items.add(item);
		}
		
		c.setItems(items);
		
		repository.getOrderRep().save(c);
		
		return ResponseEntity.ok().body("Success");
		
	}
	
//	@GetMapping(IlikeConstants.AllCategoriesServletPath)
//	public ResponseEntity<List<CategoryOut>> getAllCategories() {
//		System.out.println("### ## # request to retrieve all categories");
//		List<Category> bcat = processor.getAllCategories();
//		List<CategoryOut> res = new ArrayList<>();
//		for (Category c : bcat)
//			res.add(Transformer.CategoryB2A(c));
//		return ResponseEntity.ok().body(res);
//	}
}
