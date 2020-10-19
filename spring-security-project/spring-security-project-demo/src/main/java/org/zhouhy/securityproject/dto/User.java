package org.zhouhy.securityproject.dto;


import org.hibernate.validator.constraints.NotEmpty;
import org.zhouhy.securityproject.annotation.MyConstraint;

import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    private String id;

    @MyConstraint(message = "用户名不合法")
    private String username;

    @NotEmpty(message="password 不能为空")
    private String password;

    @Past(message="birthday 必须为过去时间")
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
