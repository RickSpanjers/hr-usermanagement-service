package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class RemoveProfileElementDTO {
    private UUID userId;
    private int profileElementId;
    private String type;

    public RemoveProfileElementDTO(){

    }

    public RemoveProfileElementDTO(UUID userId, int profileElementId, String type) {
        this.userId = userId;
        this.profileElementId = profileElementId;
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getProfileElementId() {
        return profileElementId;
    }

    public void setProfileElementId(int profileElementId) {
        this.profileElementId = profileElementId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


