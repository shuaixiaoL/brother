package com.lan.innovation.controller.sys.params;

import com.lan.common.utils.FileUploadUtils;
import com.lan.common.vo.BaseResultVo;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/api")
public class UploadController {

    /**
     * 文件上传
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public BaseResultVo uploadFile(HttpServletRequest request) {
        BaseResultVo brv = new BaseResultVo();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得非上传组件
//        String tableId = multipartRequest.getParameter("tableId");
        //获得上传组件
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        MultipartFile myfile = fileList.get(0);//获得第一个上传文件
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
