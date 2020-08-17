package nl.hrmanagement.usermanagement.service;

import nl.hrmanagement.usermanagement.dto.*;
import org.springframework.http.ResponseEntity;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Local
public interface IRESTController {
    ResponseEntity updateProfile(UpdateProfileDTO dto);
    ResponseEntity retrieveProfile(UUID id, HttpServletRequest request);
    ResponseEntity changePassword(ChangePasswordDTO dto);
    ResponseEntity addProfileElement(AddProfileElementDTO dto);
    ResponseEntity removeProfileElement(RemoveProfileElementDTO dto);
    ResponseEntity retrieveUsersFromCompany(UUID id);
    ResponseEntity retrieveUsersFromCompanyArchived(UUID id);
    ResponseEntity retrieveUsers();
    ResponseEntity retrieveUsersArchived();
    ResponseEntity addUser(AddAccountDTO dto);
    ResponseEntity archiveOrDeleteAccount(ArchiveOrRemoveAccountDTO dto);
    ResponseEntity updateRole(UpdateRoleDTO dto);
    ResponseEntity retrieveDashboard(UUID companyId);
}
