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
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="商品分类：">
                    <el-select v-model="formInline.goodsClassify" clearable  placeholder="全部">
                        <el-option v-for="(item,index) in fw_dim.SPFL" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="formInline.name" placeholder="搜索商品"></el-input>
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
                    <el-table-column prop="name" label="商品名称"></el-table-column>
                    <el-table-column prop="goodsClassify" label="商品分类">
                        <template slot-scope="scope">
                            {{ formatFwdim("SPFL", scope.row.goodsClassify) }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="updateTime" label="上新时间"></el-table-column>
                    <el-table-column prop="price" label="单价(元)"></el-table-column>
                    <el-table-column prop="num" label="库存"></el-table-column>
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
                    <el-upload
                            class="avatar-uploader"
                            :action="uploadAction"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
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
                uploadAction: '',
                imageUrl: '', //图片上传地址
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
                this.queryGoodsByUserId();
            },
            //关于分页
            handleSizeChange:function(val){
                this.pageInfo.pageSize = val;
                this.queryGoodsByUserId();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryGoodsByUserId();
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
            handleAvatarSuccess(res, file) {
                let self = this;
                const data = res;
                if (data.result == '0000') {
                    self.$message(data.msg);
                    this.rowForm.imgSrc = data.data;
                    this.imageUrl = URL.createObjectURL(file.raw);
                } else if (data.result == '9999') {
                    self.$message(data.msg);
                }

            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
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
                    self.queryGoodsByUserId();
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
                            self.queryGoodsByUserId();
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
                            self.queryGoodsByUserId();
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
                            self.queryGoodsByUserId();
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
                            self.queryGoodsByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }

                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            queryGoodsByUserId: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/goodsCreate/queryGoodsByUserId',
                    {
                        params:{
                            name: self.formInline.name,
                            goodsClassify: self.formInline.goodsClassify,
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
            this.uploadAction = contextPath + "/rest/api/upload"
        },
        mounted: function () {

        }
    })

</script>
</body>
</html>
