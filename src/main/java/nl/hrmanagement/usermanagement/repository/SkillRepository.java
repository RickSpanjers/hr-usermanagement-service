package nl.hrmanagement.usermanagement.repository;

import nl.hrmanagement.usermanagement.model.Skill;
import nl.hrmanagement.usermanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {

    Skill findById(int id);
    @Query(value="SELECT * FROM skill s WHERE s.user_id = ?1", nativeQuery = true)
    @QueryHints(@QueryHint(name= org.hibernate.annotations.QueryHints.CACHEABLE, value="true"))
    List<Skill> findByUserId(int userId);
}
