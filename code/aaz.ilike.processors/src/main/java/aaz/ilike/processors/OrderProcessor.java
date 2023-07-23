package aaz.ilike.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aaz.ilike.bom.reps.GRepository;

@Component
public class OrderProcessor {

	@Autowired
	private GRepository repository;
}
