package domainsOrPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseBody {

private Meta _meta;

@JsonProperty("result")
private List<User> users;


    public Meta get_meta() {
        return _meta;
    }

    public void set_meta(Meta _meta) {
        this._meta = _meta;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
