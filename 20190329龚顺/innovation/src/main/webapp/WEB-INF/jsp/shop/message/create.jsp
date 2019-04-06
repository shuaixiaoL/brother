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
        .btn-del {
            float: left;
        }
        .btn-add {
            float: right;
        }
    </style>
</head>
<body>

<div id="app">

    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="分类：">
                    <el-select v-model="formInline.messageStatus" clearable  placeholder="全部">
                        <el-option v-for="(item,index) in fw_dim.LY" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-button type="danger" class="btn-del" size="mini" @click="handleBacthDelete()">批量删除</el-button>
            <el-button type="primary" class="btn-add" size="mini" @click="handleAdd()">增加</el-button>

        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <template>
                <el-table :data="tableData" style="width: 100%" @select="handleSelete" @select-all="handleAllSelete">
                    <el-table-column type="selection" width="55" prop="id"></el-table-column>
                    <el-table-column prop="username" label="楼主"></el-table-column>
                    <el-table-column prop="messageStatus" label="状态">
                        <template slot-scope="scope">
                            {{ formatFwdim("LY", scope.row.messageStatus) }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="updateTime" label="上新时间"></el-table-column>
                    <el-table-column prop="message" label="内容"></el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            <el-button v-show="scope.row.messageStatus != 'LY2'" size="mini" type="text" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
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
                <el-form-item label="内容">
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 6, maxRows: 8}"
                            placeholder="请输入内容"
                            v-model="rowForm.message">
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                        <el-button type="primary" @click="onSubmit('rowForm', rowForm.type)">立即提交</el-button>
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
                fw_dim:{
                    SEX: [],
                    SPFL: [],
                    LY: [],
                },
                formInline:{
                    messageStatus:"",
                },
                tableData: [],
                pageInfo: {
                    currentPage: 1,
                    pageSize: 6,
                    total: 0,
                },
                ids:[],
                outerVisible: false,
                innerVisible: false,
                dialogVisible: false,
                rejectFlag: false,
                rowForm: {
                    message: '',
                },//选择中行的数据
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
                this.queryMessageByUserId();
            },
            //关于分页
            handleSizeChange:function(val){
                this.pageInfo.pageSize = val;
                this.queryMessageByUserId();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryMessageByUserId();
            },
            handleSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
                
            },
            handleAllSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
            },
            handleBacthDelete() {
                this.deleteBatchMessage();
            },
            handleAdd: function(){
                //打开模版框
                this.dialogVisible = true;
                this.rowForm= {
                    message: '',
                };
                this.rowForm.type = 'add';
            },
            handleDelete(index, row) {
                this.deleteMessage(row.id);
            },
            handleEdit(index, row) {
                //打开模版框
                this.dialogVisible = true;
                //获得本行数据,双向绑定
                this.rowForm =row;
                this.rowForm.type = 'update';
            },
            onSubmit(formName, type) {
                if(type == 'add') {
                    this.addMessage();
                } else if(type == 'update') {
                    this.updateMessage()
                }
            },
            //重置按钮
            resetForm(formName) {
                //这里有点小特殊，并不所有数据需要重置，只重置descInfo，就行了
                // this.$refs[formName].resetFields();
                this.rowForm.descInfo = '';
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
                axios.get(contextPath + '/rest/api/dim/SEX,SPFL,LY').then(function(res){
                    const data = res.data;
                    if(data.SEX && data.SEX.length) {
                        self.fw_dim.SEX = data.SEX;
                    }
                    if(data.SPFL && data.SPFL.length) {
                        self.fw_dim.SPFL = data.SPFL;
                    }
                    if(data.LY && data.LY.length) {
                        self.fw_dim.LY = data.LY;
                    }
                    //首页分页查询
                    self.queryMessageByUserId();
                }.bind(this));
            },
            deleteBatchMessage: function () {
                let self = this;
                if(self.ids && !self.ids.length) {
                    self.$message("请先勾选");
                    return;
                }
                axios.post(
                    contextPath + '/rest/api/messageCreate/deleteBatchMessage', {
                         ids: JSON.stringify(self.ids)
                    })
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.queryMessageByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            deleteMessage: function (id) {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/messageCreate/deleteMessage/' + id)
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.queryMessageByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            addMessage: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/messageCreate/addMessage',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            //打开模版框
                            self.dialogVisible = false;
                            self.queryMessageByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            updateMessage: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/messageCreate/updateMessage',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            //打开模版框
                            self.dialogVisible = false;
                            self.queryMessageByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            queryMessageByUserId: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/messageCreate/queryMessageByUserId',
                    {
                        params:{
                            messageStatus: self.formInline.messageStatus,
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
            this.fwdim();
        },
        mounted: function () {

        }
    })

</script>
</body>
</html>
