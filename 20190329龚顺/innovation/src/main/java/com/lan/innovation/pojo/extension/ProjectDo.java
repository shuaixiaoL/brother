package com.lan.innovation.pojo.extension;

import com.lan.innovation.pojo.Project;
import com.lan.innovation.pojo.UserProject;

import java.util.ArrayList;
import java.util.List;

public class ProjectDo extends Project {

    private List<UserProjectDo> userProjectDoList = new ArrayList<UserProjectDo>();

    public List<UserProjectDo> getUserProjectDoList() {
        return userProjectDoList;
    }

    public void setUserProjectDoList(List<UserProjectDo> userProjectDoList) {
        this.userProjectDoList = userProjectDoList;
    }
}
