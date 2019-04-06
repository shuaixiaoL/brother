package com.lan.innovation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.common.queryvo.RejectQveryVo;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.serviceBo.ProjectUserBo;
import com.lan.common.utils.CreateUuid;
import com.lan.common.utils.ListUtils;
import com.lan.common.utils.ParamsUtils;
import com.lan.common.utils.listRemoveRepeat;
import com.lan.innovation.dao.ProjectDao;
import com.lan.innovation.dao.ProjectMapper;
import com.lan.innovation.dao.UserMapper;
import com.lan.innovation.dao.UserProjectMapper;
import com.lan.innovation.exception.CustomException;
import com.lan.innovation.pojo.*;
import com.lan.innovation.pojo.extension.ProjectDo;
import com.lan.innovation.pojo.extension.UserProjectDo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lan_jiaxing on 2018/4/12 0012.
 */
@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectDao projectDao;

    @Resource
    private UserProjectMapper userProjectMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional
    public BaseResultVo addProjectUser(Map map) {
        BaseResultVo brv = new BaseResultVo();

        Date nowDate = new Date();
        //1.增加项目操作
        String projectNum = CreateUuid.getProjectTitleUUID();
        Project project = new Project();
        project.setNum(projectNum);
        if (map.get("name") != null && !"".equals(map.get("name"))) {
            project.setName(map.get("name").toString());
        }
        if (map.get("descInfo") != null && !"".equals(map.get("descInfo"))) {
            project.setDescInfo(map.get("descInfo").toString());
        }
        if (map.get("attachdoc") != null && !"".equals(map.get("attachdoc"))) {
            project.setAttachdoc(map.get("attachdoc").toString());
        }
        project.setSqTime(nowDate);
        if (map.get("progressStatus") != null && !"".equals(map.get("progressStatus"))) {
            project.setProgressStatus(Integer.parseInt(map.get("progressStatus").toString()));
        }
        if (map.get("applyStatus") != null && !"".equals(map.get("applyStatus"))) {
            project.setApplyStatus(Integer.parseInt(map.get("applyStatus").toString()));
        }
        project.setCreateTime(nowDate);
        project.setUpdateTime(nowDate);
        project.setYn(1);
        int i = projectMapper.insert(project);
        if (i == 0) {
            brv.setResult("9999");
            brv.setMsg("Project");
        }
//        int i1 = 10/0;
        Integer temp = null;

        UserProject up = new UserProject();
        up.setProjectNum(projectNum);

        //2.2负责人
        if (map.get("compareNum") != null && !"".equals(map.get("compareNum"))) {
            up.setUserNum(map.get("compareNum").toString());
            up.setUserLevel(1);
            temp = addUserProject(up);
            if (temp == 0) {
                brv.setResult("9999");
                brv.setMsg("teacherNum");
            }
        }

        //2.2教师
        if (map.get("teacherNum") != null && !"".equals(map.get("teacherNum"))) {
            up.setUserNum(map.get("teacherNum").toString());
            up.setUserLevel(4);
            temp = addUserProject(up);
            if (temp == 0) {
                brv.setResult("9999");
                brv.setMsg("teacherNum");
            }
        }
        //2.3成员一
        if (map.get("memberOneNum") != null && !"".equals(map.get("memberOneNum"))) {
            up.setUserNum(map.get("memberOneNum").toString());
            up.setUserLevel(2);
            temp = addUserProject(up);
            if (temp == 0) {
                brv.setResult("9999");
                brv.setMsg("memberOneNum");
            }
        }
        //2.4成员二
        if (map.get("memberTwoNum") != null && !"".equals(map.get("memberTwoNum"))) {
            up.setUserNum(map.get("memberTwoNum").toString());
            up.setUserLevel(3);
            temp = addUserProject(up);
            if (temp == 0) {
                brv.setResult("9999");
                brv.setMsg("memberTwoNum");
            }
        }

        //2.4 随机为项目添加一个专家
        UserExample ue1 = new UserExample();
        UserExample.Criteria criteria = ue1.createCriteria();
        criteria.andLevelEqualTo(3);
        List<User> users = userMapper.selectByExample(ue1);

        List<String> numExperts = new ArrayList<String>();
        if (null != users && users.size() > 0) {
            for (User user : users) {
                numExperts.add(user.getNum());
            }
        }

        String numExpert = "";//专家编号,永远是随机的

        if (null != numExperts && numExperts.size() > 0) {
            numExpert = ListUtils.createRandomList(numExperts, 1).get(0).toString();
            if (StringUtils.isNotBlank(numExpert)) {
                up.setUserNum(numExpert);
                up.setUserLevel(5);
                temp = addUserProject(up);
                if (temp == 0) {
                    brv.setResult("9999");
                    brv.setMsg("expertNum");
                }
            }
        }

        return brv;
    }



    public BaseResultVo selectTitleChoose(
            User userSession,
            Integer progressStatus,
            List<Integer> applyStatusList,
            Integer currentPage,
            Integer currentSize) {
        BaseResultVo brv = new BaseResultVo();
        String numSession = userSession.getNum();

        //1.获得所有老师审核通过额题目
        ProjectExample pe1 = new ProjectExample();
        ProjectExample.Criteria criteria1 = pe1.createCriteria();
        criteria1.andProgressStatusEqualTo(progressStatus);
        criteria1.andApplyStatusIn(applyStatusList);
        criteria1.andNumLike("TI%");
        List<Project> ps = projectMapper.selectByExample(pe1);

        //这里注意一下，在第三张表中，取得nums,jdk1.8
        List<String> numsTemp = ps.stream().map(Project::getNum).collect(Collectors.toList());//提取num
        //去重
        List<String> nums = listRemoveRepeat.getNoRepeatList(numsTemp);

        //2.联合查询3张表的信息（原生的）
//        List<Project> pus = new ArrayList<Project>();
//        if (nums != null && nums.size() > 0) {
//            pus = projectDao.selectProjectUserByNum(nums, progressStatus, applyStatusList);
//            pus = projectDao.selectViewProjectByNum(nums, progressStatus, applyStatusList);
//        }
        List<ProjectUserBo> pus = new ArrayList<ProjectUserBo>();
        if (nums != null && nums.size() > 0) {
            PageHelper.startPage(currentPage, currentSize);
            pus = projectDao.selectViewProjectByNum(nums, progressStatus, applyStatusList);
            PageInfo<ProjectUserBo> pageInfo = new PageInfo<ProjectUserBo>(pus, currentSize);
            brv.setPi(pageInfo);
        }

        //3.拼接成页面需要信息
        if (pus != null && pus.size() > 0) {
            List<ProjectUserBo> puBos = splitProjectUserDoView(numSession, pus);
            brv.setResultMsg("0000", "成功");
            brv.setData(puBos);
        }
        return brv;
    }

    public BaseResultVo selectProjectUserByProgressApply(
            User userSession,
            Integer progressStatus,
            List<Integer> applyStatusList,
            Integer currentPage,
            Integer currentSize) {
        BaseResultVo brv = new BaseResultVo();
        brv.setResultMsg("9999", "失败");//默认失败的
        String numSession = userSession.getNum();

        //1.在第三章表中，更据进展状态，获得项目
        List<UserProject> ups = new ArrayList<UserProject>();
        if (progressStatus != 0 && applyStatusList.size() != 0) {
            ups = getUserProjectsByNum(numSession, progressStatus);//查询操作num;
        }

        //这里注意一下，在第三张表中，取得nums,jdk1.8
        List<String> numsTemp = ups.stream().map(UserProject::getProjectNum).collect(Collectors.toList());//提取num
        //去重
        List<String> nums = listRemoveRepeat.getNoRepeatList(numsTemp);

        //2.联合查询3张表的信息（原生的）
        List<ProjectUserBo> pus = new ArrayList<ProjectUserBo>();
        if (nums != null && nums.size() > 0) {
            PageHelper.startPage(currentPage, currentSize);
            pus = projectDao.selectViewProjectByNum(nums, progressStatus, applyStatusList);
            PageInfo<ProjectUserBo> pageInfo = new PageInfo<ProjectUserBo>(pus, currentSize);
            brv.setPi(pageInfo);
        }


        //3.拼接成页面需要信息
        if (pus != null && pus.size() > 0) {
            List<ProjectUserBo> puBos = splitProjectUserDoView(numSession, pus);
            brv.setResultMsg("0000", "success");
            brv.setData(puBos);
        }
        return brv;
    }


    @Transactional
    public BaseResultVo updateProjectUserByReject(
            RejectQveryVo rejectQveryVo,
            User userSession) {
        BaseResultVo brv = new BaseResultVo();
        brv.setResultMsg("9999", "失败");//默认返回失败

        //获得数据
        String numSession = userSession.getNum();
        ProjectUserBo pu = rejectQveryVo.getProjectUser();
        String reject = rejectQveryVo.getReject();

        if (null != pu && "" != reject) {
            //老师专家(驳回意见)，在tb_user_project中填写驳回意见（老师只会驳回学生的），更新操作
            UserProjectExample upe1 = new UserProjectExample();
            UserProjectExample.Criteria criteria = upe1.createCriteria();
            criteria.andUserNumEqualTo(numSession);
            criteria.andProjectNumEqualTo(pu.getNum());

            UserProject up1 = new UserProject();
            up1.setFinalreJection(reject);
            int i = userProjectMapper.updateByExampleSelective(up1, upe1);


            ProjectExample pe1 = new ProjectExample();
            ProjectExample.Criteria criteria2 = pe1.createCriteria();
            criteria2.andNumEqualTo(pu.getNum());

            Project p1 = new Project();
            //老师驳回，在tb_project中修改项目状态,5
            if (userSession.getLevel() == 2) {
                p1.setApplyStatus(5);
            } else if (userSession.getLevel() == 3) {
                p1.setApplyStatus(6);
            }

            projectMapper.updateByExampleSelective(p1, pe1);

            brv.setResultMsg("0000", "项目已驳回");

            List applyStatusList = new ArrayList();
            applyStatusList.add(rejectQveryVo.getProjectUser().getApplyStatus());
            //再次请求一次数据库的数据
        }

        return brv;

    }

    @Transactional
    public BaseResultVo updateProjectUserOfForm(User userSession, ProjectUserBo pu) {
        BaseResultVo brv = new BaseResultVo();

        //获得数据
        String numSession = userSession.getNum();

        ProjectExample pe1 = new ProjectExample();//条件更新
        ProjectExample.Criteria criteria = pe1.createCriteria();
        criteria.andNumEqualTo(pu.getNum());

        Project p1 = new Project();//
        p1.setDescInfo(pu.getDescInfo());
        p1.setAttachdoc(pu.getAttachdoc());
        p1.setSqTime(new Date());
        int i = projectMapper.updateByExampleSelective(p1, pe1);

        if (i == 0) {
            brv.setResultMsg("9999", "失败");
        }

        return brv;
    }

    @Transactional
    public BaseResultVo updateProjectUserOfPass(
            User userSession,
            RejectQveryVo rejectQveryVo) throws Exception {
        BaseResultVo brv = new BaseResultVo();

        //获得数据
        String numSession = userSession.getNum();
        Integer levelSession = userSession.getLevel();
        ProjectUserBo pu = rejectQveryVo.getProjectUser();
        String projectNum = pu.getNum();
        Integer funds = rejectQveryVo.getFunds();
        Integer work = rejectQveryVo.getWork();

        if ("" != numSession && null != pu) {
            //老师审核通过，在tb_project中修改项目状态,2
            if (levelSession == 2) {
                int iTemp = projectDao.updateApplyStatus(projectNum, 2);
                if(iTemp == 0){
                    brv.setResultMsg("9999", "失败");//默认返回失败
                    throw new CustomException("老师审核通过修改异常");
                }
            } else if (levelSession == 3) {
                int iTemp = projectDao.updateApplyStatus(projectNum, 3);
                if(iTemp == 0){
                    brv.setResultMsg("9999", "失败");//默认返回失败
                    throw new CustomException("专家审核通过修改异常");
                }
                //专家审核通过，插入新的tb_project
                Project p1 = new Project();
                String num = null;

                p1.setName(rejectQveryVo.getProjectUser().getName());
                p1.setFunds(rejectQveryVo.getProjectUser().getFunds());
                p1.setWork(rejectQveryVo.getProjectUser().getWork());
                if (pu.getProgressStatus() == 1) {
                    num = CreateUuid.getProjectMidUUID();
                    p1.setProgressStatus(2);
                    if (null != funds && funds != 0) {
                        p1.setFunds(funds);
                    }
                } else if (pu.getProgressStatus() == 2) {
                    num = CreateUuid.getProjectResultUUID();
                    p1.setProgressStatus(3);
                } else if (pu.getProgressStatus() == 3) {
                    num = CreateUuid.getProjectWorkUUID();
                    p1.setProgressStatus(4);
                    if (null != work && work != 0) {
                        p1.setWork(work);
                    }
                }
                p1.setNum(num);
                p1.setApplyStatus(1);
                insertProject(p1);

                //分别插入5张中间表
                UserProject up = new UserProject();
                up.setProjectNum(num);
                String compareNum = rejectQveryVo.getProjectUser().getCompareNum();
                if (StringUtils.isNotBlank(compareNum)) {
                    up.setUserNum(rejectQveryVo.getProjectUser().getCompareNum());
                    up.setUserLevel(1);
                    addUserProject(up);
                }
                String memberOneNum = rejectQveryVo.getProjectUser().getMemberOneNum();
                if (StringUtils.isNotBlank(memberOneNum)) {
                    up.setUserNum(rejectQveryVo.getProjectUser().getMemberOneNum());
                    up.setUserLevel(2);
                    addUserProject(up);
                }
                String memberTwoNum = rejectQveryVo.getProjectUser().getMemberTwoNum();
                if (StringUtils.isNotBlank(memberTwoNum)) {
                    up.setUserNum(rejectQveryVo.getProjectUser().getMemberTwoNum());
                    up.setUserLevel(3);
                    addUserProject(up);
                }
                String teacherNum = rejectQveryVo.getProjectUser().getTeacherNum();
                if (StringUtils.isNotBlank(teacherNum)) {
                    up.setUserNum(rejectQveryVo.getProjectUser().getTeacherNum());
                    up.setUserLevel(4);
                    addUserProject(up);
                }

                //2.4 随机为项目添加一个专家
                UserExample ue1 = new UserExample();
                UserExample.Criteria criteria = ue1.createCriteria();
                criteria.andLevelEqualTo(3);
                List<User> users = userMapper.selectByExample(ue1);

                List<String> numExperts = new ArrayList<String>();
                if (null != users && users.size() > 0) {
                    for (User user : users) {
                        numExperts.add(user.getNum());
                    }
                }
                String numExpert = "";//专家编号,永远是随机的
                if (null != numExperts && numExperts.size() > 0) {
                    numExpert = ListUtils.createRandomList(numExperts, 1).get(0).toString();
                    if (StringUtils.isNotBlank(numExpert)) {
                        up.setUserNum(numExpert);
                        up.setUserLevel(5);
                        addUserProject(up);
                    }
                }
            }
        }
        return brv;
    }

    @Transactional
    public BaseResultVo addProjectUserOfUserLevel(
            User userSession,
            RejectQveryVo rejectQveryVo) throws Exception {
        BaseResultVo brv = new BaseResultVo();

        //获得数据
        String numSession = userSession.getNum();
        Integer userLevel = rejectQveryVo.getUserLevel();
        ProjectUserBo pu = rejectQveryVo.getProjectUser();

        UserProject userProject = new UserProject();
        userProject.setProjectNum(pu.getNum());
        userProject.setUserNum(numSession);
        userProject.setUserLevel(userLevel);
        Integer j = addUserProject(userProject);

        if (j == 0) {
            brv.setResultMsg("9999", "失败");
            throw new CustomException("申请失败");
        }
        return brv;
    }


    /////////////////////////////本类使用的private//////////////////////////////////
    //1.获得当前用户 所拥有的项目num
    private List<UserProject> getUserProjectsByNum(String numSession, Integer progressStatus) {
        UserProjectExample upe1 = new UserProjectExample();
        UserProjectExample.Criteria criteria = upe1.createCriteria();
        criteria.andUserNumEqualTo(numSession);
        //设计数据库的坑
        if (progressStatus == 1) {
            criteria.andProjectNumLike("TI%");
        } else if (progressStatus == 2) {
            criteria.andProjectNumLike("MI%");
        } else if (progressStatus == 3) {
            criteria.andProjectNumLike("RI%");
        } else if (progressStatus == 4) {
            criteria.andProjectNumLike("WI%");
        }
        return userProjectMapper.selectByExample(upe1);
    }

    //2.获得所有项目
    private List<UserProject> getUserProjects() {
        UserProjectExample upe1 = new UserProjectExample();
        UserProjectExample.Criteria criteria = upe1.createCriteria();
        return userProjectMapper.selectByExample(upe1);
    }

    //3.拼接ProjectUserDo
       private List<ProjectUserBo> splitProjectUserDo(List<ProjectDo> pus) {
        List<ProjectUserBo> puBos = new ArrayList<ProjectUserBo>();
        for (ProjectDo project : pus) {
            ProjectUserBo puBo = new ProjectUserBo();
            puBo.setNum(project.getNum());
            puBo.setName(project.getName());
            puBo.setDescInfo(project.getDescInfo());
            puBo.setAttachdoc(project.getAttachdoc());
            puBo.setSqTime(project.getSqTime());
            puBo.setProgressStatus(project.getProgressStatus());
            puBo.setProgressStatusValue(ParamsUtils.getValuesetByNameKey("progress_status", project.getProgressStatus()));
            puBo.setApplyStatus(project.getApplyStatus());
            puBo.setApplyStatusValue(ParamsUtils.getValuesetByNameKey("apply_status", project.getApplyStatus()));

            /*第二层*/
            for (UserProjectDo userProject : project.getUserProjectDoList()) {
                int UserLevel = userProject.getUserLevel();
                if (UserLevel == 1) {
                    /*第三层*/
                    puBo.setCompareNum(userProject.getUserDo().getNum());
                    puBo.setCompareRealName(userProject.getUserDo().getRealName());
                } else if (UserLevel == 2) {
                    puBo.setMemberOneNum(userProject.getUserDo().getNum());
                    puBo.setMemberOneRealName(userProject.getUserDo().getRealName());
                } else if (UserLevel == 3) {
                    puBo.setMemberTwoNum(userProject.getUserDo().getNum());
                    puBo.setMemberTwoRealName(userProject.getUserDo().getRealName());
                } else if (UserLevel == 4) {
                    puBo.setTeacherNum(userProject.getUserDo().getNum());
                    puBo.setTeacherXgTime(userProject.getXgTime());
                    puBo.setTeacherFinalreJection(userProject.getFinalreJection());
                    puBo.setTeacherRealName(userProject.getUserDo().getRealName());
                } else if (UserLevel == 5) {
                    puBo.setExamineNum(userProject.getUserDo().getNum());
                    puBo.setExamineXgTime(userProject.getXgTime());
                    puBo.setExamineFinalreJection(userProject.getFinalreJection());
                    puBo.setExamineRealName(userProject.getUserDo().getRealName());
                }
            }
            puBos.add(puBo);
        }
        return puBos;
    }
    private List<ProjectUserBo> splitProjectUserDoView(String numSession, List<ProjectUserBo> puBos) {
        for (ProjectUserBo puBo : puBos) {
            puBo.setProgressStatusValue(ParamsUtils.getValuesetByNameKey("progress_status", puBo.getProgressStatus()));
            puBo.setApplyStatusValue(ParamsUtils.getValuesetByNameKey("apply_status", puBo.getApplyStatus()));
            //判断是否可以申请applyFlag
            if (numSession.equals(puBo.getCompareNum()) || numSession.equals(puBo.getMemberOneNum()) || numSession.equals(puBo.getMemberTwoNum())) {
                puBo.setApplyFlag(2);
            }  else if(StringUtils.isNotBlank(puBo.getCompareNum()) && StringUtils.isNotBlank(puBo.getMemberOneNum()) && StringUtils.isNotBlank(puBo.getMemberTwoNum())) {
                puBo.setApplyFlag(2);
            } else {
                puBo.setApplyFlag(1);
            }
        }
        return puBos;
    }

    //4.获得所有项目
    private List<Project> getProjectsByProgressApply(
            Integer progressStatus,
            List<Integer> applyStatusList
    ) {
        ProjectExample pe1 = new ProjectExample();
        ProjectExample.Criteria criteria = pe1.createCriteria();
        criteria.andApplyStatusIn(applyStatusList);
        criteria.andProgressStatusEqualTo(progressStatus);
        return projectMapper.selectByExample(pe1);
    }

    //5.更新进展状态
//    private int updateApplyStatus(String num, Integer applyStatus) {
//        ProjectExample pe1 = new ProjectExample();
//        ProjectExample.Criteria criteria2 = pe1.createCriteria();
//        criteria2.andNumEqualTo(num);
//
//        Project p1 = new Project();
//        p1.setApplyStatus(applyStatus);
//        return projectMapper.updateByExampleSelective(p1, pe1);

        //修改审核状态

//    }

    //6.分别看添加成员编号，增加第三表tb_user_Project
    public Integer addUserProject(UserProject up1) {
        Date nowDate = new Date();
        UserProject up = new UserProject();
        up.setProjectNum(up1.getProjectNum());
        up.setUserNum(up1.getUserNum());
        up.setUserLevel(up1.getUserLevel());
        up.setFinalreJection(up1.getFinalreJection());
        up.setXgTime(nowDate);
        up.setCreateTime(nowDate);
        up.setUpdateTime(nowDate);
        up.setYn(1);

        //2.1组长  后面的反着来，没有插入来检查
        return userProjectMapper.insert(up);
    }

    //7.插入project
    private Integer insertProject(Project p) {
        Date nowDate = new Date();
        Project p1 = new Project();
        p1.setNum(p.getNum());
        p1.setName(p.getName());
        p1.setSqTime(nowDate);
        p1.setWork(p.getWork());
        p1.setFunds(p.getFunds());
        p1.setDescInfo(p.getDescInfo());
        p1.setAttachdoc(p.getAttachdoc());
        p1.setProgressStatus(p.getProgressStatus());
        p1.setApplyStatus(p.getApplyStatus());
        p1.setCreateTime(nowDate);
        p1.setUpdateTime(nowDate);
        p1.setYn(1);
        return projectMapper.insert(p1);
    }



}
