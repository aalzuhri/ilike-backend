package aaz.ilike.bom.reps;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import aaz.ilike.bom.model.Book;

@Repository
public interface BookRepository extends DatastoreRepository<Book, String> {

}
