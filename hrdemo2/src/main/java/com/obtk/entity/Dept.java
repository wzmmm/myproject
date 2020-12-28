package com.obtk.entity;

import java.io.Serializable;

public class Dept implements Serializable {

    private Integer deptNo;
    private String dname;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
