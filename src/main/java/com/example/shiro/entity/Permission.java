package com.example.shiro.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author luffy
 * @since 2020-06-08 22:19:56
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 267256838989671092L;
    
    private Integer pid;
    
    private String pname;
    
    private Integer ptype;
    
    private String pvalue;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

}