import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
    private Integer cid;
    private String custName;
    private String custLevel;
    private String custSource;
    private String custPhone;
    private String custMobile;
    private Set<Contacter> list = new HashSet<>();

    public Set<Contacter> getList() {
        return list;
    }

    public void setList(Set<Contacter> list) {
        this.list = list;
    }

    public Customer() {
    }

    public Customer(String custName, String custLevel, String custSource, String custPhone, String custMobile, Set<Contacter> list) {
        this.custName = custName;
        this.custLevel = custLevel;
        this.custSource = custSource;
        this.custPhone = custPhone;
        this.custMobile = custMobile;
        this.list = list;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }
}
