package nl.hrmanagement.usermanagement.service.impl;

import com.google.gson.Gson;
import nl.hrmanagement.usermanagement.dto.*;
import nl.hrmanagement.usermanagement.service.IRESTController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@RestController
@Stateless
public class UserManagementController implements IRESTController {

    @Autowired
    private UserManagementServiceProvider userServiceProvider;
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    @PostMapping(path = "/updateProfile", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateProfile(@NotNull @RequestBody UpdateProfileDTO dto){
        String body = gson.toJson(userServiceProvider.updateProfile(dto));
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/profile/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity retrieveProfile(@PathVariable UUID id, HttpServletRequest request) {
        boolean test = request.isUserInRole("MODERATOR");
        String body = gson.toJson(userServiceProvider.retrieveProfile(id));
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/changePassword",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity changePassword(@NotNull @RequestBody ChangePasswordDTO dto){
        String body = gson.toJson(userServiceProvider.changePassword(dto));
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/profile/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addProfileElement(@NotNull @RequestBody AddProfileElementDTO dto){
        String body = gson.toJson(userServiceProvider.addProfileElement(dto));
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/profile/remove",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity removeProfileElement(@NotNull @RequestBody RemoveProfileElementDTO dto){
        String body = gson.toJson(userServiceProvider.removeProfileElement(dto));
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/fromCompany/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity retrieveUsersFromCompany(@PathVariable UUID id) {
        String body = gson.toJson(userServiceProvider.retrieveUsersFromCompany(id));
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/fromCompanyArchived/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity retrieveUsersFromCompanyArchived(@PathVariable UUID id) {
        String body = gson.toJson(userServiceProvider.retrieveUsersFromCompanyArchived(id));
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity retrieveUsers() {
        String body = gson.toJson(userServiceProvider.retrieveUsers());
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/all/archived", produces = "application/json")
    @ResponseBody
    public ResponseEntity retrieveUsersArchived() {
        String body = gson.toJson(userServiceProvider.retrieveUsersArchived());
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addUser(@NotNull @RequestBody AddAccountDTO dto) {
        String body = gson.toJson(userServiceProvider.addAccount(dto));
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/archiveOrDelete",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity archiveOrDeleteAccount(@NotNull @RequestBody ArchiveOrRemoveAccountDTO dto) {
        String body = gson.toJson(userServiceProvider.archiveOrDeleteAccount(dto));
        return generateRequestResponse(body);
    }

    @Override
    @PostMapping(
            path = "/updateRole",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateRole(@NotNull @RequestBody UpdateRoleDTO dto) {
        String body = gson.toJson(userServiceProvider.updateAccountRole(dto));
        return generateRequestResponse(body);
    }

    @Override
    @GetMapping(path = "/retrieveDashboardData/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity retrieveDashboard(@PathVariable UUID companyId) {
        String body = gson.toJson(userServiceProvider.retrieveDashboardData_CompanyEmployees(companyId));
        return generateRequestResponse(body);
    }

    private ResponseEntity generateRequestResponse(String body){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        headers.add("Responded", "User Management Service");
        if(body != null){
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(body, headers, status);
    }



}
