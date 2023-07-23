package aaz.ilike.bom.reps;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.google.cloud.spring.data.datastore.repository.query.Query;

import aaz.ilike.bom.model.IUser;
import aaz.ilike.bom.model.Token;

@Repository
public interface TokenRepository extends DatastoreRepository<Token, String> {

	public Optional<Token> findByToken(String token);
	
	@Query("SELECT * FROM Token WHERE user = @user AND revoked = false AND expired = false")
    public List<Token> findAllValidTokenByUser(@Param("user") IUser user);
	
	
}
