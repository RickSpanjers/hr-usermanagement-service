package nl.hrmanagement.usermanagement.repository;


import nl.hrmanagement.usermanagement.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findById(int id);

    Role findByName(String name);
}
