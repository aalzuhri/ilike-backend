package aaz.ilike.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIBook {
	private String title;
	private String authors;
	private String bookAbstract;
	private String editor;
	private int year;
	private double price;
	private List<String> categories = new ArrayList<>();
}
