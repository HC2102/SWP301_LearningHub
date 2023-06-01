package swp391.group2.learninghub.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class User {
    @Id
    private String email;
    private String real_name;
    private String phone_num;
            private String password;
    private String roleId;
    private boolean isActive;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signup_date;
    private String role;

    public User() {
    }

    public User(String email, String real_name, String phone_num, String password, String roleId, boolean isActive, Date signup_date) {
        this.email = email;
        this.real_name = real_name;
        this.phone_num = phone_num;
        this.password = password;
        this.roleId = roleId;
        this.isActive = isActive;
        this.signup_date = signup_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Date signup_date) {
        this.signup_date = signup_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", real_name='" + real_name + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", isActive=" + isActive +
                ", signup_date=" + signup_date +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
