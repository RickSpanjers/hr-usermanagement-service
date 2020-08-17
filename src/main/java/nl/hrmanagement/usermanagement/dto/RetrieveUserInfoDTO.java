package nl.hrmanagement.usermanagement.dto;

import nl.hrmanagement.usermanagement.model.Role;

import java.util.UUID;

public class RetrieveUserInfoDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private Role role;

    public RetrieveUserInfoDTO(){

    }

    public RetrieveUserInfoDTO(UUID id, String firstname, String lastname, Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
