<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../vuetemplate/css_vue_template.jsp" flush="true"></jsp:include>
    <link type="text/css" href="${path}/static/css/jsp/title_declare_student.css"/>

</head>
<body>
<div id="app">

    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form ref="titleForm" :model="titleForm" :rules="rules" label-width="120px" size="mini">
                <el-form-item>
                    <el-col :span="24"><div class="title-content" style="font-size: 30px;font-family: '宋体';">学生题目自助申报</div></el-col>
                </el-form-item>

                <el-form-item label="题目名称" prop="name">
                    <el-col :span="12">
                        <el-input v-model="titleForm.name"></el-input>
                    </el-col>
                </el-form-item>

                <el-form-item label="组长学号" prop="compareNum">
                    <el-col :span="12">
                        <el-input v-model="titleForm.compareNum" @blur="checkNum('titleForm_compareNum')"></el-input>
                    </el-col>
                    <span id="titleForm_compareNum" style="color:red"></span>
                </el-form-item>

                <el-form-item label="指导老师工号" prop="teacherNum">
                    <el-col :span="12">
                        <el-input v-model="titleForm.teacherNum" @blur="checkNum('titleForm_teacherNum')"></el-input>
                    </el-col>
                    <span id = "titleForm_teacherNum" style="color:red"></span>
                </el-form-item>
                <el-form-item label="成员一学号" prop="memberOneNum">
                    <el-col :span="12">
                        <el-input v-model="titleForm.memberOneNum" @blur="checkNum('titleForm_memberOneNum')"></el-input>
                    </el-col>
                    <span id = "titleForm_memberOneNum" style="color:red"></span>
                </el-form-item>
                <el-form-item label="成员二学号" prop="memberTwoNum">
                    <el-col :span="12">
                        <el-input v-model="titleForm.memberTwoNum" @blur="checkNum('titleForm_memberTwoNum')"></el-input>
                    </el-col>
                    <span id = "titleForm_memberTwoNum" style="color:red"></span>
                </el-form-item>
                <el-form-item label="附件文档">
                    <el-upload class="upload"
                              :action = "importFileUrl"
                              :show-file-list = "false"
                               :on-success = "handleSuccess"
                    <%-- :data="upLoadData"
                    :before-upload = "beforeUploadExcel"


                    :on-error = "handleError"--%>
                    >
                        <el-button size="small" type="primary">点击上传</el-button>
                        <el-button type="text"  @click="onClickHref">{{fileName}}</el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="题目描述" prop="descInfo">
                    <el-col :span="12">
                    <el-input type="textarea" v-model="titleForm.descInfo"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                    <el-button type="primary" @click="onSubmit('titleForm')">立即提交</el-button>
                    <el-button @click="resetForm('titleForm')">重置</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>

</div>

<jsp:include page="../vuetemplate/js_vue_template.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/static/js/jsp/title_declare_student.js"></script>
</body>
</html>
