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
        .monry {
            float: right;
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
            <span class="monry">我的小金库：￥{{money}}元</span>
            <%--<el-button type="primary" class="btn-add" size="mini" @click="handleBacthPay()">批量结算</el-button>--%>

        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <template>
                <el-table :data="tableData" style="width: 100%" @select="handleSelete" @select-all="handleAllSelete">
                    <el-table-column type="selection" width="55" prop="id"></el-table-column>
                    <el-table-column prop="name" label="商品名称"></el-table-column>
                    <el-table-column prop="price" label="单价(元)"></el-table-column>
                    <el-table-column prop="number" label="已选">
                        <template slot-scope="scope">
                            <el-input-number v-model="scope.row.number" :precision="2" size="mini" :min="1"></el-input-number>
                        </template>
                    </el-table-column>
                    <el-table-column prop="price" label="合计(元)">
                        <template slot-scope="scope">
                            {{scope.row.number * scope.row.price}}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            <el-button type="primary" size="mini" @click="handlePay(scope.$index, scope.row)">结算</el-button>
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
                    {{rowForm.name}}
                </el-form-item>
                <el-form-item label="单价(元)">
                    {{rowForm.price}}
                </el-form-item>
                <el-form-item label="选择">
                    <el-input-number v-model="rowForm.number" :precision="0" size="mini" :min="1"></el-input-number>
                </el-form-item>
                <el-form-item label="合计(元)">
                    ￥{{rowForm.price * rowForm.number}}元
                </el-form-item>
                <el-form-item label="小金库">
                    ￥{{money}}元 &nbsp;-&nbsp;￥{{rowForm.price * rowForm.number}}元 &nbsp;=&nbsp;￥{{(money - rowForm.price * rowForm.number)}}元
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                        <el-button type="primary" @click="onSubmit('rowForm')">立即支付</el-button>
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
                money: 0,
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
                this.queryShopcartByUserId();
            },
            //关于分页
            handleSizeChange:function(val){
                this.pageInfo.pageSize = val;
                this.queryShopcartByUserId();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryShopcartByUserId();
            },
            handleSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
                
            },
            handleAllSelete: function(selection, row) {
                this.ids = commonMain.getArrFromObjArr(selection, "id");
            },
            handleBacthDelete() {
                this.deleteBatchShopcart();
            },
            handleBacthPay() {

            },
            handleDelete(index, row) {
                this.deleteShopcart(row.id);
            },
            handlePay(index, row){
                let self = this;
                if(row.number > row.num) {
                    self.$message("库存不够");
                    return;
                }
                //打开模版框
                this.dialogVisible = true;
                this.rowForm = row;
            },
            onSubmit(formName, row) {
                let self = this;
                if(self.rowForm.number * self.rowForm.price > self.money) {
                    self.$message("不好意思，钱不够");
                    return;
                }
                this.pay();
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
                    self.queryShopcartByUserId();
                }.bind(this));
            },
            getMoneyByUserId: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/shopcartCartinfo/getMoneyByUserId')
                    .then(function (response) {
                        const data = response.data;
                        self.money = data.data;
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            deleteBatchShopcart: function () {
                let self = this;
                if(self.ids && !self.ids.length) {
                    self.$message("请先勾选");
                }
                axios.post(
                    contextPath + '/rest/api/shopcartCartinfo/deleteBatchShopcart', {
                         ids: JSON.stringify(self.ids)
                    })
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.queryShopcartByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            deleteShopcart: function (id) {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/shopcartCartinfo/deleteShopcart/' + id)
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.queryShopcartByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            queryShopcartByUserId: function () {
                let self = this;
                axios.get(
                    contextPath + '/rest/api/shopcartCartinfo/queryShopcartByUserId',
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
            pay: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/shopcartCartinfo/pay',
                    self.rowForm
                )
                    .then(function (response) {
                        const data = response.data;
                        if (data.result == '0000') {
                            self.$message(data.msg);
                            self.dialogVisible = false;
                            self.queryShopcartByUserId();
                            self.getMoneyByUserId();
                        } else if (data.result == '9999') {
                            self.$message(data.msg);
                        }
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
            this.getMoneyByUserId();
        }
    })

</script>
</body>
</html>
