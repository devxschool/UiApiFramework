package domainsOrPojo.jira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueType {

    @JsonProperty("id")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IssueType(String name) {
        this.name = name;
    }
}
