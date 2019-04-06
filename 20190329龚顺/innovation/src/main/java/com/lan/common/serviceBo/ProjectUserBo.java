package com.lan.common.serviceBo;

import java.io.Serializable;
import java.util.Date;

public class ProjectUserBo implements Serializable {

    //project
    private Integer id;

    private String num;

    private String name;

    private String descInfo;

    private String attachdoc;

    private Date sqTime;

    private Integer work;

    private Integer funds;

    private Integer progressStatus;//进展状态

    private String progressStatusValue;//进展状态

    private Integer applyStatus;//申请状态

    private String applyStatusValue;//申请状态

    private String compareNum;

    private String teacherNum;

    private String memberOneNum;

    private String memberTwoNum;

    private String examineNum;

    private String compareRealName;

    private String teacherRealName;

    private String memberOneRealName;

    private String memberTwoRealName;

    private String examineRealName;

    private Date teacherXgTime;
    private Date examineXgTime;

    private Integer applyFlag;//当前用户是否可申请 1可申请，2不可

    private String teacherFinalreJection;//老师驳回意见

    private String examineFinalreJection;//专家驳回意见

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
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public String getAttachdoc() {
        return attachdoc;
    }

    public void setAttachdoc(String attachdoc) {
        this.attachdoc = attachdoc;
    }

    public Date getSqTime() {
        return sqTime;
    }

    public void setSqTime(Date sqTime) {
        this.sqTime = sqTime;
    }

    public Integer getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(Integer progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getProgressStatusValue() {
        return progressStatusValue;
    }

    public void setProgressStatusValue(String progressStatusValue) {
        this.progressStatusValue = progressStatusValue;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatusValue() {
        return applyStatusValue;
    }

    public void setApplyStatusValue(String applyStatusValue) {
        this.applyStatusValue = applyStatusValue;
    }

    public String getCompareRealName() {
        return compareRealName;
    }

    public void setCompareRealName(String compareRealName) {
        this.compareRealName = compareRealName;
    }

    public String getTeacherRealName() {
        return teacherRealName;
    }

    public void setTeacherRealName(String teacherRealName) {
        this.teacherRealName = teacherRealName;
    }

    public String getMemberOneRealName() {
        return memberOneRealName;
    }

    public void setMemberOneRealName(String memberOneRealName) {
        this.memberOneRealName = memberOneRealName;
    }

    public String getMemberTwoRealName() {
        return memberTwoRealName;
    }

    public void setMemberTwoRealName(String memberTwoRealName) {
        this.memberTwoRealName = memberTwoRealName;
    }

    public String getExamineRealName() {
        return examineRealName;
    }

    public void setExamineRealName(String examineRealName) {
        this.examineRealName = examineRealName;
    }

    public String getTeacherFinalreJection() {
        return teacherFinalreJection;
    }

    public void setTeacherFinalreJection(String teacherFinalreJection) {
        this.teacherFinalreJection = teacherFinalreJection;
    }

    public String getExamineFinalreJection() {
        return examineFinalreJection;
    }

    public void setExamineFinalreJection(String examineFinalreJection) {
        this.examineFinalreJection = examineFinalreJection;
    }

    public Date getTeacherXgTime() {
        return teacherXgTime;
    }

    public void setTeacherXgTime(Date teacherXgTime) {
        this.teacherXgTime = teacherXgTime;
    }

    public String getCompareNum() {
        return compareNum;
    }

    public void setCompareNum(String compareNum) {
        this.compareNum = compareNum;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getMemberOneNum() {
        return memberOneNum;
    }

    public void setMemberOneNum(String memberOneNum) {
        this.memberOneNum = memberOneNum;
    }

    public String getMemberTwoNum() {
        return memberTwoNum;
    }

    public void setMemberTwoNum(String memberTwoNum) {
        this.memberTwoNum = memberTwoNum;
    }

    public String getExamineNum() {
        return examineNum;
    }

    public void setExamineNum(String examineNum) {
        this.examineNum = examineNum;
    }

    public Date getExamineXgTime() {
        return examineXgTime;
    }

    public void setExamineXgTime(Date examineXgTime) {
        this.examineXgTime = examineXgTime;
    }

    public Integer getApplyFlag() {
        return applyFlag;
    }

    public void setApplyFlag(Integer applyFlag) {
        this.applyFlag = applyFlag;
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
}
