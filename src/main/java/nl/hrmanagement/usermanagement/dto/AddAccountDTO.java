package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class AddAccountDTO {
    private UUID userId;
    private String mail;
    private String firstname;
    private String lastname;
    private String address;
    private String place;
    private String phone;
    private String country;
    private String zipcode;
    private String password;
    private String passwordConfirm;
    private String role;
    private UUID functionId;
    private UUID companyId;
    private UUID moderatorId;

    public AddAccountDTO() {
    }

    public AddAccountDTO(UUID userId, String mail, String firstname, String lastname, String address, String place, String phone, String country, String zipcode, String password, String passwordConfirm, String role, UUID functionId, UUID companyId, UUID moderatorId) {
        this.userId = userId;
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.place = place;
        this.phone = phone;
        this.country = country;
        this.zipcode = zipcode;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.role = role;
        this.functionId = functionId;
        this.companyId = companyId;
        this.moderatorId = moderatorId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getFunctionId() {
        return functionId;
    }

    public void setFunctionId(UUID functionId) {
        this.functionId = functionId;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public UUID getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(UUID moderatorId) {
        this.moderatorId = moderatorId;
    }
}
