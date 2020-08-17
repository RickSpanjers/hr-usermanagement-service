package nl.hrmanagement.usermanagement.dto;

import nl.hrmanagement.usermanagement.model.*;

import java.util.List;

public class RetrieveProfileDTO {

    private User user;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Skill> skillList;
    private List<Language> languageList;

    public RetrieveProfileDTO(User user, List<Education> educationList, List<Experience> experienceList, List<Skill> skillList, List<Language> languageList) {
        this.user = user;
        this.educationList = educationList;
        this.experienceList = experienceList;
        this.skillList = skillList;
        this.languageList = languageList;
    }

    public RetrieveProfileDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
}
