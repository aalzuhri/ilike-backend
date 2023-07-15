package aaz.ilike.bom.reps;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import aaz.ilike.bom.model.Order;

@Repository
public interface OrderRepository extends DatastoreRepository<Order, String> {

}
