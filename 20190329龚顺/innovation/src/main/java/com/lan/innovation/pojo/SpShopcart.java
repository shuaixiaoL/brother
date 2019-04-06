package com.lan.innovation.pojo;

public class SpShopcart {
    private String id;

    private Integer number;

    private String spGoodId;

    private String sysUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSpGoodId() {
        return spGoodId;
    }

    public void setSpGoodId(String spGoodId) {
        this.spGoodId = spGoodId == null ? null : spGoodId.trim();
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId == null ? null : sysUserId.trim();
    }
}