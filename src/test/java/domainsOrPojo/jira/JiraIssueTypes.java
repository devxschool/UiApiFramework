package domainsOrPojo.jira;

public enum JiraIssueTypes{
    STORY("10001","Story"),
    BUG("10003","Bug"),
    TASK("10002","Task"),
    SUB_TASK("10005","Sub-Task");

    private String issueId;
    private String strValue;

    JiraIssueTypes(String issueId, String strValue) {
        this.issueId = issueId;
        this.strValue = strValue;
    }

    public String getIssueId(){
        return issueId;
    }
    public String getStrValue(){
        return strValue;
    }

}
