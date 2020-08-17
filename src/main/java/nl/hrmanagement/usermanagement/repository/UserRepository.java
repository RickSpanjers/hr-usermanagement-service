package nl.hrmanagement.usermanagement.repository;

import nl.hrmanagement.usermanagement.dto.RetrieveUserInfoDTO;
import nl.hrmanagement.usermanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUuid(UUID id);
    List<User> findAllByCompanyId(UUID companyId);
    @Query(value="SELECT new nl.hrmanagement.usermanagement.dto.RetrieveUserInfoDTO(u.uuid, u.firstName, u.lastName, u.role) FROM User u WHERE u.companyId = ?1 AND u.archived = ?2", nativeQuery = false)
    List<RetrieveUserInfoDTO> findAllUserIdentificationByCompanyId(UUID companyId, boolean archived);
    @Query(value="SELECT new nl.hrmanagement.usermanagement.dto.RetrieveUserInfoDTO(u.uuid, u.firstName, u.lastName, u.role) FROM User u WHERE u.companyId = ?1 AND u.archived = ?2", nativeQuery = false)
    List<RetrieveUserInfoDTO> findAllUserIdentificationByCompanyIdArchived(UUID companyId, boolean archived);
    @Query(value="SELECT new nl.hrmanagement.usermanagement.dto.RetrieveUserInfoDTO(u.uuid, u.firstName, u.lastName, u.role) FROM User u WHERE u.archived = ?1", nativeQuery = false)
    List<RetrieveUserInfoDTO> findAllUsers(boolean archived);
    @Query(value="SELECT new nl.hrmanagement.usermanagement.dto.RetrieveUserInfoDTO(u.uuid, u.firstName, u.lastName, u.role) FROM User u WHERE u.archived = ?1", nativeQuery = false)
    List<RetrieveUserInfoDTO> findAllUsersArchived(boolean archived);
    int countAllByCompanyId(UUID companyId);
}
