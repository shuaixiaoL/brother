package com.lan.innovation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.utils.DtfpUtils;
import com.lan.innovation.dao.SysRoleMapper;
import com.lan.innovation.dao.SysUserMapper;
import com.lan.innovation.dao.SysUserRoleMapper;
import com.lan.innovation.dao.UserMapper;
import com.lan.innovation.pojo.*;
import com.lan.common.vo.SysUserVo;
import com.lan.innovation.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SysUserVo queryUsersInfoByExRole(SysUserVo sysUserVo, SysRole sysRole) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlList = new StringBuffer();
        sqlList.append(" select su.*, sur.sys_role_id, sr.role_code, sr.role_level, sr.role_name ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(1) as aaa ");


        sql.append(" from sys_user su left join sys_user_role sur on sur.sys_user_id = su.id LEFT JOIN sys_role sr ON sr.id = sur.sys_role_id where 1 = 1  ");
        sql.append(" AND sr.role_level >= " + sysRole.getRoleLevel());
        if(StringUtils.isNotBlank(sysUserVo.getRoleCode())) {
            sql.append(" AND sr.role_code = " + sysUserVo.getRoleCode());
        }
        sqlList.append(sql.toString());
        sqlList.append(" LIMIT " + (sysUserVo.getCurrentPage() - 1) * sysUserVo.getPageSize() + "," + sysUserVo.getPageSize() );

        sysUserVo.setList(DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sqlList.toString())));

        sqlCount.append(sql.toString());
        sysUserVo.setTotalCount(Integer.parseInt(jdbcTemplate.queryForList(sqlCount.toString()).get(0).get("aaa").toString()));
        return sysUserVo;
    }

    @Override
    public BaseResultVo updateRoleId(SysUserVo sysUserVo) {
        BaseResultVo brv = new BaseResultVo();

        // 先删除，再添加
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
        criteria.andSysUserIdEqualTo(sysUserVo.getId());//userId
        sysUserRoleMapper.deleteByExample(sysUserRoleExample);

        //添加
        List<Map<String, Object>> list = jdbcTemplate.queryForList(" SELECT * FROM sys_role WHERE ROLE_CODE = '" + sysUserVo.getRoleCode() +"'");
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(DtfpUtils.getUUID());
        sysUserRole.setSysUserId(sysUserVo.getId());
        sysUserRole.setSysRoleId(list.get(0).get("id").toString());
        int result = sysUserRoleMapper.insert(sysUserRole);
        if(result > 0) {
            brv.setResultMsg("0000", "授权成功");
        } else {
            brv.setResultMsg("9999", "授权失败");
        }
        return brv;
    }

    @Override
    public BaseResultVo deleteUser(SysUserVo sysUserVo) {
        BaseResultVo brv = new BaseResultVo();
        int result = sysUserMapper.deleteByPrimaryKey(sysUserVo.getId());
        if(result > 0) {
            brv.setResultMsg("0000", "删除成功");
        } else {
            brv.setResultMsg("9999", "删除失败");
        }
        return brv;
    }

    @Override
    public SysUser getUserById(SysUser sysUser) {
        return sysUserMapper.selectByPrimaryKey(sysUser.getId());
    }

    @Override
    public BaseResultVo updateUser(SysUser user, String id) {
        BaseResultVo brv = new BaseResultVo();

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user, sysUser);
        sysUser.setId(id);

        int result = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if(result > 0) {
            brv.setResultMsg("0000", "更新成功");
        } else {
            brv.setResultMsg("9999", "更新失败");
        }
        return brv;
    }

    @Override
    public BaseResultVo getMoneyByUserId(String id) {
        BaseResultVo brv = new BaseResultVo();
        SysUser result = sysUserMapper.selectByPrimaryKey(id);
        if(result != null) {
            brv.setData(result.getMoney());
        } else {
            brv.setResultMsg("9999", "失败");
            brv.setData(0);
        }
        return brv;
    }

    @Override
    public SysUserVo queryUsersByEx(SysUserVo sysUserVo) {
        return null;
    }

    @Override
    public BaseResultVo selectUserByNum(String num, Integer level) {
        BaseResultVo brv = new BaseResultVo();


        //数据库查询
        UserExample example1 = new UserExample();
        UserExample.Criteria criteria = example1.createCriteria();
        criteria.andNumEqualTo(num);
        criteria.andLevelEqualTo(level);
        //根据不同编号条件查询不同 1学生2老师3专家
//        if (num.startsWith("TJ")) {
//            criteria.andLevelEqualTo(2);
//        } else if (num.startsWith("ZJ")) {
//            criteria.andLevelEqualTo(3);
//        } else {
//            criteria.andLevelEqualTo(1);
//        }
        List<User> users = userMapper.selectByExample(example1);
        return brv;
    }

    /**
     * 登陆，查询user
     */
    @Override
    public BaseResultVo selectUser(User user) {
        BaseResultVo brv = new BaseResultVo();

        UserExample example1 = new UserExample();
        UserExample.Criteria criteria = example1.createCriteria();
        criteria.andNumEqualTo(user.getNum());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example1);
        if (users != null && users.size() > 0) {
            brv.setData(users.get(0));
        } else {
            brv.setResultMsg("9999", "用户名或密码错误");
        }
        return brv;
    }



    /**
     * 查询获得所有用户
     * @return
     */
    @Override
    public Map selectUsers(
            Integer level,
            Integer currentPage, Integer currentSize
    ) {
        Map map = new HashMap();

        //1.设置分页信息
        PageHelper.startPage(currentPage, currentSize);
        //2.执行查询
        UserExample example1 = new UserExample();
        example1.setOrderByClause("level");//设置排序
        if (level != null && level != 0) {
            UserExample.Criteria criteria = example1.createCriteria();
            criteria.andLevelEqualTo(level);
        }
        List<User> users = userMapper.selectByExample(example1);
        //3.取分页结果 Rows：itemList , Total
        PageInfo<User> pageInfo = new PageInfo<User>(users, currentSize);

        map.put("userPageInfo", pageInfo);
        return map;
    }


    @Transactional
    @Override
    public BaseResultVo regist(SysUser sysUser) throws Exception {
        BaseResultVo brv = new BaseResultVo();

        //判断用户是否存在
        List<SysUser> users = checkUsercode(sysUser.getUsercode());
        if(users.size() > 0) {
            throw new Exception("该账号已存在");
        }

        //插入用户
        String userUuid = DtfpUtils.getUUID();
        sysUser.setId(userUuid);
        int a1 = sysUserMapper.insert(sysUser);
        if(a1 < 1) {
            throw new Exception("注册失败");
        }

        //插入权限（普通用户）
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(DtfpUtils.getUUID());
        sysUserRole.setSysUserId(userUuid);
        sysUserRole.setSysRoleId("3");
        int a2 = sysUserRoleMapper.insert(sysUserRole);
        if(a2 < 1) {
            throw new Exception("授权失败");
        }
        brv.setResultMsg("0000", "注册成功，去登录吧");
        return brv;
    }

    @Override
    public List<SysUser> checkUsercode(String usercode) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUsercodeEqualTo(usercode);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return sysUsers;
    }

    @Override
    public List<SysUser> checkUserPwd(SysUserVo sysUserVo){
        //验证账号密码
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUsercodeEqualTo(sysUserVo.getUsercode());
        criteria.andPasswordEqualTo(sysUserVo.getPassword());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return sysUsers;
    }

    @Override
    public SysRole checkRoleId(SysUserVo sysUserVo) {
        return sysRoleMapper.selectByPrimaryKey(sysUserVo.getRoleId());
    }

    @Override
    public List<SysUserRole> checkUserRole(SysUserVo sysUserVo) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria2 = sysUserRoleExample.createCriteria();
        criteria2.andSysRoleIdEqualTo(sysUserVo.getRoleId());
        criteria2.andSysUserIdEqualTo(sysUserVo.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        return sysUserRoles;
    }
}
