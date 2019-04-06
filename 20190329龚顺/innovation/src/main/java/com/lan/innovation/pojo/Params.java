package com.lan.innovation.pojo;

import java.util.Date;

public class Params {
    private Long id;

    private Integer type;

    private String name;

    private String cnName;

    private Integer keyset;

    private String valueset;

    private String remark;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Integer yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public Integer getKeyset() {
        return keyset;
    }

    public void setKeyset(Integer keyset) {
        this.keyset = keyset;
    }

    public String getValueset() {
        return valueset;
    }

    public void setValueset(String valueset) {
        this.valueset = valueset == null ? null : valueset.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}