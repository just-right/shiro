package com.example.shiro.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author luffy
 * @since 2020-06-08 22:19:05
 */
public class User implements Serializable {
    private static final long serialVersionUID = 600183465123429977L;
    
    private Integer uid;
    
    private String name;
    
    private String password;
    
    private String salt;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}