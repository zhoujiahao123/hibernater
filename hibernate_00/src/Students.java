
import java.sql.Blob;
import java.util.Date;

/**
 * Created by DreamBoy on 2016/5/15.
 */
//学生类
public class Students {
    //1. 必须为公有的类
    //2. 必须提供公有的不带参数的默认的构造方法
    //3. 属性私有
    //4. 属性setter/getter封装

    private int sid; //学号
    private String sname; //姓名
    private String gender; //性别
    private Date birthday; //出生日期
    private String address; //地址

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    private Blob picture;
    public Students() {
    }

    public Students(String sname, String gender, Date birthday, String address) {
        this.sname = sname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Students{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}