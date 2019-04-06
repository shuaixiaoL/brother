package com.lan.innovation.pojo.extension;

import com.lan.innovation.pojo.User;
import com.lan.innovation.pojo.UserProject;

/**
 * Created by lan_jiaxing on 2018/5/14 0014.
 */
public class UserProjectDo extends UserProject{

    /* 配置user_project和user*/
    private UserDo userDo;

    public User getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDo userDo) {
        this.userDo = userDo;
    }
}
