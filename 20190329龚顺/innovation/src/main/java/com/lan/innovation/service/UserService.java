package com.lan.innovation.service;

import com.lan.common.vo.BaseResultVo;
import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.pojo.SysUserRole;
import com.lan.innovation.pojo.User;
import com.lan.common.vo.SysUserVo;

import java.util.List;
import java.util.Map;

public interface UserService {

    public SysUserVo queryUsersInfoByExRole(SysUserVo sysUserVo, SysRole sysRole);

    public BaseResultVo updateRoleId(SysUserVo sysUserVo);

    public BaseResultVo deleteUser(SysUserVo sysUserVo);

    public SysUser getUserById(SysUser sysUser);

    public BaseResultVo updateUser(SysUser user, String id);

    public BaseResultVo getMoneyByUserId(String id);

    public SysUserVo queryUsersByEx(SysUserVo sysUserVo);

    public BaseResultVo selectUserByNum(String num, Integer level);

    public BaseResultVo selectUser(User user);

    public Map selectUsers(
            Integer level,
            Integer currentPage, Integer currentSize
    );

    public BaseResultVo regist(SysUser sysUser) throws Exception;

    public List<SysUser> checkUsercode(String usercode);

    public List<SysUser> checkUserPwd(SysUserVo sysUserVo);

    public SysRole checkRoleId(SysUserVo sysUserVo);

    public List<SysUserRole> checkUserRole(SysUserVo sysUserVo);

}
