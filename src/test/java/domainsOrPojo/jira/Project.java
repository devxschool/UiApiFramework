package domainsOrPojo.jira;

public class Project {

    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Project(String key) {
        this.key = key;
    }
}
