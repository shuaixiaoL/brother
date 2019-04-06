package com.lan.innovation.dao;

import com.lan.common.serviceBo.ProjectUserBo;
import com.lan.innovation.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {

    /* 自定义的 */
    List<Project> selectProjectUserByNum(@Param("nums") List<String> nums,
                                         @Param("progressStatus") Integer progressStatus,
                                         @Param("applyStatusList") List<Integer> applyStatusList);

    /* 自定义的 */
    List<ProjectUserBo> selectViewProjectByNum(@Param("nums") List<String> nums,
                                               @Param("progressStatus") Integer progressStatus,
                                               @Param("applyStatusList") List<Integer> applyStatusList);

    //修改进展状态
    Integer updateApplyStatus(@Param("projectNum") String projectNum,
                                    @Param("progressStatus") Integer progressStatus);
}