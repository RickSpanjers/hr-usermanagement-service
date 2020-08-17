package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class UpdateProfileDTO {

    private UUID userId;
    private String mail;
    private String firstname;
    private String lastname;
    private String phone;
    private String country;
    private String zipcode;
    private String place;

    public UpdateProfileDTO() {
    }

    public UpdateProfileDTO(UUID userId, String mail, String firstname, String lastname, String phone, String country, String zipcode, String place) {
        this.userId = userId;
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.country = country;
        this.zipcode = zipcode;
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
