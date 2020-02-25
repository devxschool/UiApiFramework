package domainsOrPojo.jira;

public class CreateIssue {

    private Fields fields;

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public CreateIssue(Fields fields) {
        this.fields = fields;
    }
}
