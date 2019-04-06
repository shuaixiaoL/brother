package com.lan.common.queryvo;


import com.lan.common.serviceBo.ProjectUserBo;

import java.io.Serializable;

public class RejectQveryVo implements Serializable {

    private String numSession;
    private Integer levelSession;
    private Integer userLevel;
    private ProjectUserBo projectUser;
    private String reject;
    private Integer funds;
    private Integer work;
    private Integer currentPage;
    private Integer currentSize;

    public String getNumSession() {
        return numSession;
    }

    public void setNumSession(String numSession) {
        this.numSession = numSession;
    }

    public ProjectUserBo getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(ProjectUserBo projectUser) {
        this.projectUser = projectUser;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public Integer getLevelSession() {
        return levelSession;
    }

    public void setLevelSession(Integer levelSession) {
        this.levelSession = levelSession;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }
}
