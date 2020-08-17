package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class UpdateRoleDTO {
    private UUID userId;
    private UUID adminId;
    private String role;

    public UpdateRoleDTO() {
    }

    public UpdateRoleDTO(UUID userId, UUID adminId, String role) {
        this.userId = userId;
        this.adminId = adminId;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
