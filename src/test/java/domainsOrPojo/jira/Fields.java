package domainsOrPojo.jira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fields {

    private Project project;
    private String summary;
    private String description;

    @JsonProperty("issuetype")
    private IssueType issueType;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Fields(Project project, String summary, String description, IssueType issueType) {
        this.project = project;
        this.summary = summary;
        this.description = description;
        this.issueType = issueType;
    }
}
