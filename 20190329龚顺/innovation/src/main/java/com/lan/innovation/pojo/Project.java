package com.lan.innovation.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private Integer id;

    private String num;

    private String name;

    private String descInfo;

    private String attachdoc;

    private Date sqTime;

    private Integer work;

    private Integer funds;

    private Integer progressStatus;

    private Integer applyStatus;

    private Date createTime;

    private Date updateTime;

    private Integer yn;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo == null ? null : descInfo.trim();
    }

    public String getAttachdoc() {
        return attachdoc;
    }

    public void setAttachdoc(String attachdoc) {
        this.attachdoc = attachdoc == null ? null : attachdoc.trim();
    }

    public Date getSqTime() {
        return sqTime;
    }

    public void setSqTime(Date sqTime) {
        this.sqTime = sqTime;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Integer getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(Integer progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }


}