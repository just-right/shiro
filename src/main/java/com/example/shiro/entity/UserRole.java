package com.example.shiro.entity;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author luffy
 * @since 2020-06-08 22:20:17
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -16545743171389599L;
    
    private Integer id;
    
    private Integer uid;
    
    private Integer rid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

}