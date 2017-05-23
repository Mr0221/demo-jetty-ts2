package cn.lai.netstoss.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 瀹炰綋绫�
 * 娉ㄦ剰锛氫笌瑕佹搷浣滅殑琛ㄤ繚鎸佷竴鑷�
 * @author 杈惧唴IT鍩硅闆嗗洟
 *
 */
public class Admin implements Serializable{
    private String adminID;
    private String adminCode;
    private String password;
    private String name;
    private String telephone;
    private String email;
    private Timestamp enrolldate;
    public String getAdminID() {
        return adminID;
    }
    public void setAdminID(final String adminID) {
        this.adminID = adminID;
    }
    public String getAdminCode() {
        return adminCode;
    }
    public void setAdminCode(final String adminCode) {
        this.adminCode = adminCode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public Timestamp getEnrolldate() {
        return enrolldate;
    }
    public void setEnrolldate(final Timestamp enrolldate) {
        this.enrolldate = enrolldate;
    }
    @Override
    public String toString() {
        return "Admin [adminID=" + adminID + ", adminCode=" + adminCode + ", password=" + password + ", name=" + name
                + ", telephone=" + telephone + ", email=" + email + ", enrolldate=" + enrolldate + "]";
    }


}
