package domainsOrPojo;

import java.util.List;

public class ResponseBody {

private Meta _meta;
private List<User> result;

    public Meta get_meta() {
        return _meta;
    }

    public void set_meta(Meta _meta) {
        this._meta = _meta;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
