package nl.hrmanagement.usermanagement.repository;

import nl.hrmanagement.usermanagement.model.Education;
import nl.hrmanagement.usermanagement.model.Experience;
import nl.hrmanagement.usermanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface EducationRepository extends CrudRepository<Education, Integer> {
    Education findById(int id);
    @Query(value="SELECT * FROM education e WHERE e.user_id = ?1", nativeQuery = true)
    @QueryHints(@QueryHint(name= org.hibernate.annotations.QueryHints.CACHEABLE, value="true"))
    List<Education> findByUserId(int userId);
}
