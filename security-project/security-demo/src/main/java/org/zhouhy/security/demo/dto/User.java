package org.zhouhy.security.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer Id;
    private String username;
    private String password;
    private Date birthday;
}
