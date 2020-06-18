package com.example.shiro.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author luffy
 * @since 2020-06-08 22:20:43
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -64194768699443922L;
    
    private Integer id;
    
    private Integer rid;
    
    private Integer pid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}