<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../vuetemplate/css_vue_template.jsp" flush="true"></jsp:include>
    <style>

    </style>
</head>
<body>

<div id="app">

    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form ref="rowForm" label-width="80px" v-model="rowForm" size="mini">
                <el-form-item label="账号">
                    <el-input v-model="rowForm.usercode"></el-input>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="rowForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="rowForm.password"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-select v-model="rowForm.sex">
                        <el-option v-for="(item,index) in fw_dim.SEX" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="rowForm.tel"></el-input>
                </el-form-item>
                <el-form-item label="简介">
                    <el-input v-model="rowForm.descInfo"></el-input>
                </el-form-item>
                <el-form-item label="小金库">
                    <el-input v-model="rowForm.money"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                        <el-button type="primary" @click="onSubmit('rowForm')">立即提交</el-button>
                        <%-- <el-button @click="resetForm('rowForm')">重置</el-button>--%>
                    </el-col>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>

</div>

<jsp:include page="../../vuetemplate/js_vue_template.jsp" flush="true"></jsp:include>
<script type="text/javascript">


    var vue = new Vue({
        el: '#app',
        data() {
            return {
                fw_dim:{
                    SEX: [],
                },
                formInline:{
                    region:"",
                },
                tableData: [],
                pageInfo: {
                    currentPage: 1,
                    pageSize: 6,
                    total: 0,
                },
                outerVisible: false,
                innerVisible: false,
                dialogVisible: false,
                rejectFlag: false,
                rowForm: {},//选择中行的数据
                rejectTextarea: '',
            }
        },
        methods: {
            dateFormatter: function (row, column, cellValue, index) {
                var date = row[column.property];
                if (date == undefined) {
                    return "";
                }
                return moment(date).format("YYYY-MM-DD HH:mm:ss");
            },
            onSubmit: function() {
                this.updateUser();
            },
            /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
            //格式化维度
            formatFwdim: function(dmval, value){
                for(let k in this.fw_dim[dmval]){
                    if(this.fw_dim[dmval][k].value == value){
                        return this.fw_dim[dmval][k].text;
                    }
                }
            },
            fwdim: function() {
                let self = this;
                axios.get(contextPath + '/rest/api/dim/SEX').then(function(res){
                    const data = res.data;
                    if(data.SEX && data.SEX.length) {
                        self.fw_dim.SEX = data.SEX;
                    }
                    //首页分页查询
                    self.getUserById();
                }.bind(this));
            },
            getUserById: function () {
                let self = this;
                axios.get(contextPath + '/rest/api/info/getUserById')
                    .then(function (response) {
                       const data = response.data;
                       self.rowForm = data
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            updateUser: function () {
                let self = this;
                axios.post(contextPath + '/rest/api/info/updateUser',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        self.$message(data.msg);
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            }
        },
        //在实例创建完成后被立即调用。老师审核通过
        created: function () {
            this.fwdim();
        },
        mounted: function () {

        }
    })

</script>
</body>
</html>
