package com.example.shiro.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author luffy
 * @since 2020-06-08 22:19:35
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 647679655475372448L;
    
    private Integer rid;
    
    private String rname;
    
    private String rdesc;
    
    private String rvalue;


    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }

}