package com.lan.common.vo;

import java.util.List;

/**
 * 分页显示数据类
 */
public class PageBo<T> {
    private int pageSize = 6;// 每页显示记录数   currentPage
    private int currentPage = 1;// 当前页数.
    private int totalCount;// 总记录数
    private int totalPage;// 总页数.
    private List<T> list; // 显示到浏览器的数据.

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }

    public PageBo() {
    }

    public PageBo(int pageSize, int currentPage, int totalCount, int totalPage, List<T> list) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }
}


