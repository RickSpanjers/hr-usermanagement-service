package nl.hrmanagement.usermanagement.repository;

import nl.hrmanagement.usermanagement.model.Experience;
import nl.hrmanagement.usermanagement.model.Language;
import nl.hrmanagement.usermanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ExperienceRepository  extends CrudRepository<Experience, Integer> {
    Experience findById(int id);
    @Query(value="SELECT * FROM experience e WHERE e.user_id = ?1", nativeQuery = true)
    @QueryHints(@QueryHint(name= org.hibernate.annotations.QueryHints.CACHEABLE, value="true"))
    List<Experience> findByUserId(int userId);
}
