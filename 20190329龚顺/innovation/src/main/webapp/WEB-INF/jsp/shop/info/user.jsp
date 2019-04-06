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
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="用户身份：">
                    <el-select v-model="formInline.region" clearable>
                        <el-option v-for="(item,index) in dp_topic.ROLE" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <template>
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column type="selection" width="55"></el-table-column>
                    <el-table-column prop="usercode" label="账号"></el-table-column>
                    <el-table-column prop="username" label="昵称"></el-table-column>
                    <el-table-column prop="sex" label="性别">
                        <template slot-scope="scope">
                            {{ formatFwdim("SEX", scope.row.sex) }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="tel" label="电话"></el-table-column>
                    <el-table-column prop="descInfo" label="简介"></el-table-column>
                    <el-table-column prop="roleCode" label="身份">
                        <template slot-scope="scope">
                            {{ formatdpTopic("ROLE", scope.row.roleCode) }}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
        </el-col>
    </el-row>
    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="text-align: center">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageInfo.currentPage"
                    :page-size="pageInfo.pageSize"
                    layout="total, prev, pager, next"
                    :total="pageInfo.total"
                    background
            >
            </el-pagination>
        </el-col>
    </el-row>

    <%--弹出框（提交）--%>
    <template>
        <el-dialog title="" :visible.sync="dialogVisible" class="customClass" top="true" width="80%" center>
            <div slot="title" class="dialog-header">
                显示详细
            </div>

            <el-form ref="rowForm" label-width="80px" v-model="rowForm" size="mini">
                <el-form-item label="账号">
                    <el-input v-model="rowForm.usercode"></el-input>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="rowForm.username"></el-input>
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
                <el-form-item label="身份">
                    <el-select v-model="rowForm.roleCode" clearable  @change="updateRoleId">
                        <el-option v-for="(item,index) in dp_topic.ROLE" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                        <el-button type="primary" @click="onSubmit('rowForm')">立即提交</el-button>
                       <%-- <el-button @click="resetForm('rowForm')">重置</el-button>--%>
                    </el-col>
                </el-form-item>
            </el-form>

        </el-dialog>
    </template>

</div>

<jsp:include page="../../vuetemplate/js_vue_template.jsp" flush="true"></jsp:include>
<script type="text/javascript">


    var vue = new Vue({
        el: '#app',
        data() {
            return {
                dp_topic: {
                    ROLE:[]
                },
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
            sysRoleIdFormatter: function (row, column, cellValue, index) {
                var item = row[column.property];
                if(item == 0) {
                    return "超级超级管理员";
                } else if(item == 1) {
                    return "超级管理员";
                } else if (item == 2) {
                    return "院系管理员";
                } else if (item == 3) {
                    return "普通用户";
                } else {
                    return "";
                }
            },
            search() {
                let self = this;
                self.pageInfo.currentPage = 1;
                this.queryUsersInfoByExRole();
            },
            //关于分页
            handleSizeChange:function(val){
                this.pageInfo.pageSize = val;
                this.queryUsersInfoByExRole();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryUsersInfoByExRole();
            },
            handleDelete(index, row) {
                this.deleteUser(row);
            },
            handleEdit(index, row) {
                //打开模版框
                this.dialogVisible = true;
                //获得本行数据,双向绑定
                var data = this.tableData;
                this.rowForm = data[index];
            },

            onSubmit(formName) {

            },
            //重置按钮
            resetForm(formName) {
                //这里有点小特殊，并不所有数据需要重置，只重置descInfo，就行了
                // this.$refs[formName].resetFields();
                this.rowForm.descInfo = '';
            },

            /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
            //格式化维度
            formatdpTopic: function(dmval, value){
                for(let k in this.dp_topic[dmval]){
                    if(this.dp_topic[dmval][k].value == value){
                        return this.dp_topic[dmval][k].text;
                    }
                }
            },
            //格式化维度
            formatFwdim: function(dmval, value){
                for(let k in this.fw_dim[dmval]){
                    if(this.fw_dim[dmval][k].value == value){
                        return this.fw_dim[dmval][k].text;
                    }
                }
            },
            dpTopic: function() {
                let self = this;
                axios.get(contextPath + '/rest/api/roleByLevel').then(function(res){
                    const data = res.data;
                    self.dp_topic.ROLE = data;
                }.bind(this));
            },
            fwdim: function() {
                let self = this;
                axios.get(contextPath + '/rest/api/dim/SEX').then(function(res){
                    const data = res.data;
                    if(data.SEX && data.SEX.length) {
                        self.fw_dim.SEX = data.SEX;
                    }
                    //首页分页查询
                    self.queryUsersInfoByExRole();
                }.bind(this));
            },
            deleteUser: function (row) {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/info/deleteUser',
                    {
                        params:{
                            id: row.id,
                        }
                    }
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.queryUsersInfoByExRole();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                        console.log(error);
                    }.bind(this));
            },
            updateRoleId: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/info/updateRoleId',
                    {
                        params:{
                            roleCode: self.rowForm.roleCode,
                            id: self.rowForm.id,
                        }
                    }
                )
                    .then(function (response) {
                        const data = response.data;
                        if(data) {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            queryUsersInfoByExRole: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/info/queryUsersInfoByExRole',
                    {
                        params:{
                            roleCode: self.formInline.region,
                            currentPage: self.pageInfo.currentPage,
                            pageSize: self.pageInfo.pageSize
                        }
                    }
                )
                    .then(function (response) {
                       const data = response.data;
                       if(data) {
                           if(data.list) {
                               self.tableData = data.list;
                           }
                           if(data.totalCount) {
                               self.pageInfo.total = data.totalCount;
                           }
                       }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
        },
        //在实例创建完成后被立即调用。老师审核通过
        created: function () {
            this.dpTopic();
            this.fwdim();
        },
        mounted: function () {

        }
    })

</script>
</body>
</html>
