package com.lan.innovation.pojo;

import java.util.Date;

public class UserProject {
    private Integer id;

    private String projectNum;

    private String userNum;

    private Integer userLevel;

    private Date xgTime;

    private String finalreJection;

    private Date createTime;

    private Date updateTime;

    private Integer yn;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum == null ? null : projectNum.trim();
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getXgTime() {
        return xgTime;
    }

    public void setXgTime(Date xgTime) {
        this.xgTime = xgTime;
    }

    public String getFinalreJection() {
        return finalreJection;
    }

    public void setFinalreJection(String finalreJection) {
        this.finalreJection = finalreJection == null ? null : finalreJection.trim();
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