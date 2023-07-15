package aaz.ilike.bom.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="book")
public class Book {
	@Id private String id;
	private String title;
	private String authors;
	private String bookAbstract;
	private String editor;
	private int year;
	private double price;
	private String path;
	private List<String> categories = new ArrayList<String>();
}
