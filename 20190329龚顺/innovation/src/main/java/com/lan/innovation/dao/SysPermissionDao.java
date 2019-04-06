package com.lan.innovation.dao;

import com.lan.innovation.pojo.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * <p>Title: SysPermissionDao</p>
 * <p>Description: 权限mapper</p>
 */
public interface SysPermissionDao {
	
	//根据用户id查询菜单
	public List<SysPermission> findMenuListByUserId(@Param("userid") String userid)throws Exception;
	//根据用户id查询权限url
	public List<SysPermission> findPermissionListByUserId(@Param("userid") String userid) throws Exception;

}
