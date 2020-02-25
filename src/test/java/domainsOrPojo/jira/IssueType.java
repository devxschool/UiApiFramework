package domainsOrPojo.jira;

public class IssueType {

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
