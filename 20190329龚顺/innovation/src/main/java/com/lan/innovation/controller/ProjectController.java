package com.lan.innovation.controller;

import com.lan.common.queryvo.RejectQveryVo;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.serviceBo.ProjectUserBo;
import com.lan.common.utils.FileUploadUtils;
import com.lan.innovation.pojo.User;
import com.lan.innovation.service.ProjectService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/innovation")
public class ProjectController {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private ProjectService projectService;

    /**
     * 增加操作
     */
    @RequestMapping(value = "/projectUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo addProjectUser(@RequestBody Map map) {
        return projectService.addProjectUser(map);
    }


    /**
     * 增加操作(主要增加第三张表)组长，成员一，成员二
     */
    @PostMapping(value = "/projectUser/userLevel")
    @ResponseBody
    public BaseResultVo addProjectUser(
            @RequestBody RejectQveryVo rejectQveryVo,
            HttpSession session
    ) {
        User userSession = (User) session.getAttribute("user");
        try {
            return projectService.addProjectUserOfUserLevel(userSession, rejectQveryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询操作(numSession),分页 ,查询所有(非选题)
     */
    @GetMapping(value = "/projectUser/progressapply")
    @ResponseBody
    public BaseResultVo selectProjectUserByProgressApply(
            @RequestParam(value = "progressStatus") Integer progressStatus,
            @RequestParam(value = "applyStatusList") String applyStatusList,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "currentSize", defaultValue = "6") Integer currentSize,
            HttpServletRequest request
    ) {
        User userSession = (User) request.getSession().getAttribute("user");
        String [] a = applyStatusList.split(",");
        List<Integer> l = new ArrayList<Integer>();
        for (String s : a) {
            l.add(Integer.parseInt(s));
        }
        return projectService.selectProjectUserByProgressApply(userSession, progressStatus, l, currentPage, currentSize);
    }

    /**
     * 查询操作(numSession),分页
     */
    @RequestMapping(value = "/projectUser/titleChoose", method = RequestMethod.GET)
    @ResponseBody
    public BaseResultVo selectTitleChoose(
            @RequestParam Integer progressStatus,
            @RequestParam String applyStatusList,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "currentSize", defaultValue = "6") Integer currentSize,
            HttpSession session
    ) {
        User userSession = (User) session.getAttribute("user");
        String [] a = applyStatusList.split(",");
        List<Integer> l = new ArrayList<Integer>();
        for (String s : a) {
            l.add(Integer.parseInt(s));
        }
        return projectService.selectTitleChoose(userSession, progressStatus, l, currentPage, currentSize);
    }


    /**
     * 驳回意见，不要考虑用煞笔的map接受，特别是map里面有对象
     */
    @PutMapping(value = "/projectUser/reject")
    @ResponseBody
    public BaseResultVo updateProjectUserByReject(
            @RequestBody RejectQveryVo rejectQveryVo,
            HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        return projectService.updateProjectUserByReject(rejectQveryVo, userSession);
    }

    /**
     * 审核通过，不要考虑用煞笔的map接受，特别是map里面有对象
     */
    @PutMapping(value = "/projectUser/passExamine")
    @ResponseBody
    public BaseResultVo updateProjectUserOfPass(
            @RequestBody RejectQveryVo rejectQveryVo,
            HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        try {
            return projectService.updateProjectUserOfPass(userSession, rejectQveryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增加操作(实际更新操作)
     */
    @PutMapping(value = "/projectUser/form")
    @ResponseBody
    public BaseResultVo updateProjectUserOfForm(
            @RequestBody ProjectUserBo projectUser,
            HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        return projectService.updateProjectUserOfForm(userSession, projectUser);
    }

    /**
     * 文件上传
     */
    @PostMapping(value = "/project/upload")
    @ResponseBody
    public BaseResultVo uploadFile(HttpServletRequest request) {
        BaseResultVo brv = new BaseResultVo();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得非上传组件
//        String tableId = multipartRequest.getParameter("tableId");
        //获得上传组件
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        MultipartFile  myfile = fileList.get(0);//获得第一个上传文件
        try {
            String originalFilename = myfile.getOriginalFilename();//获得上传文件名.后缀
            String pathTemp = request.getServletContext().getRealPath("/temp/");
            String pathNameTemp = pathTemp + originalFilename; //临时完整目录文件名.后缀
            // 得到上传文件名称
            String filename = FileUploadUtils.getRealName(originalFilename);
            // 得到随机名称.后缀
            String uuidname = FileUploadUtils.getUUIDFileName(filename);
            // 得到随机目录
            String randomDirectory = FileUploadUtils.getRandomDirectory(filename);
            // 注意:随机目录可能不存在，需要创建.
            File rd = new File("G:/upload/temp/", randomDirectory);
            if (!rd.exists()) {
                rd.mkdirs();
            }
            System.out.println(rd);
            //先把传过来的文件放在临时文件夹下，然后从文件夹中取出
            InputStream inputStream = myfile.getInputStream();
            IOUtils.copy(inputStream, new FileOutputStream(new File(rd, uuidname)));
            //删除临时文件
//            File fileTemp = new File(pathNameTemp);
//            if(fileTemp.exists() && fileTemp.isFile()) {
//                fileTemp.delete();
//            }
            brv.setResultMsg("0000", "成功");
            brv.setData("/pic" + randomDirectory + "/" + uuidname);
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setResultMsg("9999", "失败");
            return brv;
        }

    }
}
