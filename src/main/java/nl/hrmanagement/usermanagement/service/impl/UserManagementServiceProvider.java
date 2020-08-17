package nl.hrmanagement.usermanagement.service.impl;

import com.google.gson.Gson;
import nl.hrmanagement.usermanagement.dto.*;
import nl.hrmanagement.usermanagement.model.*;
import nl.hrmanagement.usermanagement.rabbitmq.TaskSender;
import nl.hrmanagement.usermanagement.repository.*;
import nl.hrmanagement.usermanagement.security.jwt.JwtTokenUtil;
import nl.hrmanagement.usermanagement.service.IServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

@Service
@Stateless
public class UserManagementServiceProvider implements IServiceProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Gson gson = new Gson();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TaskSender rabbitMQSender;

    @Override
    public GenericResponseDTO updateProfile(UpdateProfileDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        User account = userRepository.findByUuid(dto.getUserId());
        if(account != null){
            account.setFirstName(dto.getFirstname());
            account.setLastName(dto.getLastname());
            account.setMail(dto.getMail());
            account.setAddress(dto.getZipcode());
            account.setTelephone(dto.getPhone());
            account.setCountry(dto.getCountry());
            userRepository.save(account);
            account.setPassword("");
            result = new GenericResponseDTO(true, account, "Successfully updated profile");
        }
        return result;
    }

    @Override
    public GenericResponseDTO changePassword(ChangePasswordDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(dto.getNewPassword().equals(dto.getNewPasswordConfirm())){
            User account = userRepository.findByUuid(dto.getUserId());
            account.setPassword(passwordEncoder.encryptPassword(dto.getNewPassword()));
            userRepository.save(account);
            result = new GenericResponseDTO(true, null, "Successfully changed password");
        }
        return result;
    }

    @Override
    public GenericResponseDTO retrieveProfile(UUID id){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(id != null){
            User account = userRepository.findByUuid(id);
            List<Education> educationList = educationRepository.findByUserId(account.getId());
            List<Skill> skillList = skillRepository.findByUserId(account.getId());
            List<Experience> expList = experienceRepository.findByUserId(account.getId());
            List<Language> langList = languageRepository.findByUserId(account.getId());
            account.setPassword("");
            RetrieveProfileDTO resultDTO = new RetrieveProfileDTO(account, educationList, expList, skillList, langList);
            result = new GenericResponseDTO(true, resultDTO, "Successfully retrieved user");
        }
        return result;
    }

    @Override
    public GenericResponseDTO addProfileElement(AddProfileElementDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        User account = userRepository.findByUuid(dto.getUserId());
        if(account != null){
            switch(dto.getType()) {
                case "EXPERIENCE":
                    Experience exp = new Experience(0, dto.getName(), dto.getDescription(), null, null, account);
                    experienceRepository.save(exp);
                    break;
                case "EDUCATION":
                    Education edu = new Education(0, dto.getName(), dto.getDescription(), null, null, true, account);
                    educationRepository.save(edu);
                    break;
                case "SKILL":
                    Skill skill = new Skill(0, dto.getName(), dto.getDescription(), account);
                    skillRepository.save(skill);
                    break;
                case "LANGUAGE":
                    Language language = new Language(0, dto.getName(), dto.getDescription(), account);
                    languageRepository.save(language);
                    break;

            }
            result = new GenericResponseDTO(true, account, "Successfully updated profile");
        }
        return result;
    }

    @Override
    public GenericResponseDTO removeProfileElement(RemoveProfileElementDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(dto.getProfileElementId() != 0){
            switch(dto.getType()) {
                case "EXPERIENCE":
                    Experience exp = experienceRepository.findById(dto.getProfileElementId());
                    experienceRepository.delete(exp);
                    break;
                case "EDUCATION":
                    Education edu = educationRepository.findById(dto.getProfileElementId());
                    educationRepository.delete(edu);
                    break;
                case "SKILL":
                    Skill skill = skillRepository.findById(dto.getProfileElementId());
                    skillRepository.delete(skill);
                    break;
                case "LANGUAGE":
                    Language lang = languageRepository.findById(dto.getProfileElementId());
                    languageRepository.delete(lang);
                    break;
            }
            result = new GenericResponseDTO(true, null, "Successfully updated profile");
        }
        return result;
    }

    @Override
    public GenericResponseDTO retrieveUsersFromCompany(UUID companyId){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(companyId != null){
            List<RetrieveUserInfoDTO> objects = userRepository.findAllUserIdentificationByCompanyId(companyId, false);
            result = new GenericResponseDTO(true, objects, "Successfully retrieved users");
        }
        return result;
    }

    @Override
    public GenericResponseDTO retrieveUsersFromCompanyArchived(UUID companyId){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(companyId != null){
            List<RetrieveUserInfoDTO> objects = userRepository.findAllUserIdentificationByCompanyIdArchived(companyId, true);
            result = new GenericResponseDTO(true, objects, "Successfully retrieved archived users");
        }
        return result;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Override
    public GenericResponseDTO retrieveUsers(){
        List<RetrieveUserInfoDTO> objects = userRepository.findAllUsers(false);
        return new GenericResponseDTO(true, objects, "Successfully retrieved users");
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Override
    public GenericResponseDTO retrieveUsersArchived(){
        List<RetrieveUserInfoDTO> objects = userRepository.findAllUsersArchived(true);
        return  new GenericResponseDTO(true, objects, "Successfully retrieved archived users");
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Override
    public GenericResponseDTO addAccount(AddAccountDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(dto.getModeratorId() != null){
             if(dto.getPassword().equals(dto.getPasswordConfirm())){

                    //Generate UUID based on user data
                    String toGenerateUUID = dto.getFirstname()+dto.getCountry()+dto.getPhone();
                    User newAccount = new User(0, UUID.nameUUIDFromBytes(toGenerateUUID.getBytes()), dto.getFirstname(), dto.getLastname(), dto.getCompanyId(), dto.getFunctionId());

                    newAccount.setTelephone(dto.getPhone());
                    newAccount.setMail(dto.getMail());
                    newAccount.setZipcode(dto.getZipcode());
                    newAccount.setCountry(dto.getCountry());
                    newAccount.setAddress(dto.getAddress());
                    newAccount.setPlace(dto.getPlace());
                    Role role = roleRepository.findByName(dto.getRole());
                    newAccount.setRole(role);

                    if(dto.getFunctionId() != null){
                        newAccount.setFunctionId(dto.getFunctionId());
                    }
                    if(dto.getCompanyId() != null){
                        newAccount.setCompanyId(dto.getCompanyId());
                    }

                    newAccount.setPassword(passwordEncoder.encryptPassword(dto.getPassword()));
                    userRepository.save(newAccount);
                    result = new GenericResponseDTO(true, "Success", "Successfully added account");
                }
        }
        return result;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Override
    public GenericResponseDTO archiveOrDeleteAccount(ArchiveOrRemoveAccountDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(dto.getModeratorId() != null && dto.getAccountId() != null){
            User account = userRepository.findByUuid(dto.getAccountId());
            switch(dto.getAction()){
                case "ARCHIVE":
                    account.setArchived(true);
                    userRepository.save(account);
                    break;
                case "UNARCHIVE":
                    account.setArchived(false);
                    userRepository.save(account);
                    break;
                case "DELETE":
                    userRepository.delete(account);
                    rabbitMQSender.sendDeleteQueue(account.getId());
                    break;
            }
            result = new GenericResponseDTO(true, null, "Successfully "+dto.getAction()+" account");
        }
        return result;
    }

    @Override
    public GenericResponseDTO archiveAllCompanyUsers(UUID companyId){
        GenericResponseDTO result = new GenericResponseDTO(false, "Failure", null);
        if(companyId != null){
            List<User> users = userRepository.findAllByCompanyId(companyId);
            for (User user: users) {
                user.setArchived(true);
                userRepository.save(user);
            }
            result = new GenericResponseDTO(true, null, "Successfully archived users");
        }
        return result;
    }

    @Override
    public GenericResponseDTO updateAccountRole(UpdateRoleDTO dto){
        GenericResponseDTO result = new GenericResponseDTO(false, "Unable to update user role. Please try again!", null);
        if(dto.getUserId() != null && dto.getAdminId() != null && dto.getRole() != null){
            User account = userRepository.findByUuid(dto.getUserId());
            Role role = roleRepository.findByName(dto.getRole());
            account.setRole(role);
            userRepository.save(account);
            result = new GenericResponseDTO(true, null, "Successfully updated user role!");
        }
        return result;
    }

    @Override
    public GenericResponseDTO retrieveDashboardData_CompanyEmployees(UUID companyId){
        GenericResponseDTO result = new GenericResponseDTO(false, "Unable to retrieve dashboard data. Please try again!", null);
        if(companyId != null){
            int total  = userRepository.countAllByCompanyId(companyId);
            result = new GenericResponseDTO(true, total, "Successfully retrieved employees!");
        }
        return result;
    }
}
