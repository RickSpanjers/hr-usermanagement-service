package nl.hrmanagement.usermanagement.service;

import nl.hrmanagement.usermanagement.dto.*;

import javax.ejb.Local;
import java.util.UUID;

@Local
public interface IServiceProvider {
    GenericResponseDTO updateProfile(UpdateProfileDTO dto);
    GenericResponseDTO changePassword(ChangePasswordDTO dto);
    GenericResponseDTO retrieveProfile(UUID id);
    GenericResponseDTO addProfileElement(AddProfileElementDTO dto);
    GenericResponseDTO removeProfileElement(RemoveProfileElementDTO dto);
    GenericResponseDTO retrieveUsersFromCompany(UUID companyId);
    GenericResponseDTO retrieveUsersFromCompanyArchived(UUID companyId);
    GenericResponseDTO retrieveUsers();
    GenericResponseDTO retrieveUsersArchived();
    GenericResponseDTO addAccount(AddAccountDTO dto);
    GenericResponseDTO archiveOrDeleteAccount(ArchiveOrRemoveAccountDTO dto);
    GenericResponseDTO archiveAllCompanyUsers(UUID companyId);
    GenericResponseDTO updateAccountRole(UpdateRoleDTO dto);
    GenericResponseDTO retrieveDashboardData_CompanyEmployees(UUID companyId);
}
