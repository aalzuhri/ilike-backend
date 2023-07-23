package aaz.ilike.bom.reps;

import aaz.ilike.bom.model.IUser;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

@Repository
public interface UserRepository extends DatastoreRepository<IUser, String> {

	public IUser findByEmail(String email);
}
