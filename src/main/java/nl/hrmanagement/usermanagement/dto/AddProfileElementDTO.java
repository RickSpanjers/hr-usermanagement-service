package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class AddProfileElementDTO {
    private UUID userId;
    private String name;
    private String description;
    private String certification;
    private String type;

    public AddProfileElementDTO(UUID userId, String name, String description, String certification, String type) {
        this.name = name;
        this.description = description;
        this.certification = certification;
        this.type = type;
        this.userId = userId;
    }

    public AddProfileElementDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
