package nl.hrmanagement.usermanagement.dto;

import java.util.UUID;

public class ArchiveOrRemoveAccountDTO {
    private UUID moderatorId;
    private UUID accountId;
    private String action;

    public ArchiveOrRemoveAccountDTO() {
    }

    public ArchiveOrRemoveAccountDTO(UUID moderatorId, UUID accountId, String action) {
        this.moderatorId = moderatorId;
        this.accountId = accountId;
        this.action = action;
    }

    public UUID getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(UUID moderatorId) {
        this.moderatorId = moderatorId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
