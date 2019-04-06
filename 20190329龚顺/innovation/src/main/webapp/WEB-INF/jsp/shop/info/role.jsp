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
        .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
        }
        .avatar-uploader .el-upload:hover {
            border-color: #409EFF;
        }
        .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            line-height: 178px;
            text-align: center;
        }
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }
    </style>
</head>
<body>

<div id="app">

    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
           <%-- <el-button type="primary" class="btn-add" size="mini" @click="handleAdd()">增加</el-button>--%>
           <%-- <el-button type="primary" class="btn-add" size="mini" @click="handleApple()">授权</el-button>--%>

        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <template>
                <el-table :data="tableData" style="width: 100%" @select="handleSelete" @select-all="handleAllSelete">
                    <el-table-column type="selection" width="55" prop="id"></el-table-column>
                    <el-table-column prop="roleCode" label="编码">
                    </el-table-column>
                    <el-table-column prop="roleLevel" label="级别"></el-table-column>
                    <el-table-column prop="roleName" label="名称"></el-table-column>
                    <el-table-column prop="type" label="类型"></el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" @click="handleApple(scope.$index, scope.row)">授权</el-button>
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
                <el-form-item label="商品名称">
                    <el-input v-model="rowForm.name"></el-input>
                </el-form-item>
                <el-form-item label="商品分类">
                    <el-select v-model="rowForm.goodsClassify">
                        <el-option v-for="(item,index) in fw_dim.SPFL" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="上新时间">
                    {{rowForm.updateTime}}
                </el-form-item>
                <el-form-item label="单价(元)">
                    <el-input-number v-model="rowForm.price" :precision="2" size="mini" :min="1"></el-input-number>
                </el-form-item>
                <el-form-item label="库存">
                    <el-input-number v-model="rowForm.num" :precision="0" size="mini" :min="1"></el-input-number>
                </el-form-item>
                <el-form-item label="图片">
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

    <template>
        <el-dialog title="" :visible.sync="dialogVisible2" class="customClass" top="true" width="80%" center>
            <div slot="title" class="dialog-header">
                显示详细
            </div>

            <el-tree
                    ref="tree"
                    :data="allPermission"
                    show-checkbox
                    node-key="id"
                    :default-expanded-keys="[111, 3]"
                    :default-checked-keys="checkPermission"
                    :props="defaultProps">
            </el-tree>
            <el-button type="primary" @click="apple">立即提交</el-button>
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
                },
                formInline:{
                    name:"",
                    goodsClassify:"",
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
                dialogVisible2: false,
                rejectFlag: false,
                rowForm: {
                    id: '',
                    name: '',
                    goodsClassify: '',
                    updateTime: new Date(),
                    price: 0,
                    num: 0,
                    imgSrc: '',
                },//选择中行的数据
                allPermission:[],
                checkPermission:[111],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
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
                this.getRole();
            },
            //关于分页
            handleSizeChange:function(val){
                this.pageInfo.pageSize = val;
                this.getRole();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.getRole();
            },
            handleSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
                
            },
            handleAllSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
            },
            handleBacthDelete() {
                this.deleteBatchGoods();
            },
            handleAdd: function(){
                //打开模版框
                this.dialogVisible = true;
                this.rowForm= {
                    id: 0,
                    name: '',
                    goodsClassify: '',
                    updateTime: new Date(),
                    price: 0,
                    num: 0,
                    imgSrc: '',
                };
                this.rowForm.type = 'add';
            },
            handleApple: function(index, row){
                //打开模版框
                this.dialogVisible2 = true;
                this.rowForm = row;
                this.getRolePermission(row.id);
            },
            handleDelete(index, row) {
                this.deleteGoods(row.id);
            },
            handleEdit(index, row) {
                //打开模版框
                this.dialogVisible = true;
                //获得本行数据,双向绑定
                this.rowForm =row;
                this.imageUrl = this.rowForm.imgSrc;
                this.rowForm.type = 'update';
            },
            onSubmit(formName, type) {
                if(type == 'add') {
                    this.addGoods();
                } else if(type == 'update') {
                    this.updateGoods()
                }
            },
            //重置按钮
            resetForm(formName) {
                //这里有点小特殊，并不所有数据需要重置，只重置descInfo，就行了
                // this.$refs[formName].resetFields();
                this.rowForm.descInfo = '';
            },
            apple: function(){
                let self = this;
                let permissionIds = self.$refs.tree.getCheckedKeys();//选中树setCheckedKeys
                let permissionIds2 = self.$refs.tree.getHalfCheckedKeys();//选中树setCheckedKeys
                permissionIds.push(...permissionIds2);
                this.applePermission(self.rowForm.id, permissionIds)
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
                axios.get(contextPath + '/rest/api/dim/SEX,SPFL').then(function(res){
                    const data = res.data;
                    if(data.SEX && data.SEX.length) {
                        self.fw_dim.SEX = data.SEX;
                    }
                    if(data.SPFL && data.SPFL.length) {
                        self.fw_dim.SPFL = data.SPFL;
                    }
                    //首页分页查询
                    self.getRole();
                    self.getAllPermission();
                }.bind(this));
            },
            deleteBatchGoods: function () {
                let self = this;
                if(self.ids && !self.ids.length) {
                    self.$message("请先勾选");
                    return;
                }
                axios.post(
                    contextPath + '/rest/api/goodsCreate/deleteBatchGoods', {
                         ids: JSON.stringify(self.ids)
                    })
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.getRole();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            deleteGoods: function (id) {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/goodsCreate/deleteGoods/' + id)
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.getRole();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            addGoods: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/goodsCreate/addGoods',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            //打开模版框
                            self.dialogVisible = false;
                            self.getRole();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            updateGoods: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/goodsCreate/updateGoods',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            //打开模版框
                            self.dialogVisible = false;
                            self.getRole();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            getRole: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/getRole',
                    {
                        params:{
                            currentPage: self.pageInfo.currentPage,
                            pageSize: self.pageInfo.pageSize
                        }
                    }
                )
                    .then(function (response) {
                       const data = response.data;
                       if(data) {
                           // if(data.list) {
                               self.tableData = data;
                           // }
                           // if(data.totalCount) {
                           //     self.pageInfo.total = data.totalCount;
                           // }
                       }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            getAllPermission: function () {
                let self = this;
                axios.get(contextPath + '/rest/api/getAllPermission')
                    .then(function (response) {
                       const data = response.data;
                       if(data) {
                          self.allPermission = data;
                       }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            getRolePermission: function (roleId) {
                let self = this;
                axios.get(contextPath + '/rest/api/getRolePermission',{
                    params: {
                        id: roleId
                    }
                })
                    .then(function (response) {
                       const data = response.data;
                       if(data) {
                          self.checkPermission = data;
                           self.$refs.tree.setCheckedKeys(self.checkPermission);//选中树setCheckedKeys
                       }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            applePermission: function (roleId, permissionIds) {
                let self = this;
                axios.get(contextPath + '/rest/api/applePermission',{
                    params: {
                        id: roleId,
                        permissionIds: JSON.stringify(permissionIds),
                    }
                })
                    .then(function (response) {
                       const data = response.data;
                        self.dialogVisible2 = false;
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
