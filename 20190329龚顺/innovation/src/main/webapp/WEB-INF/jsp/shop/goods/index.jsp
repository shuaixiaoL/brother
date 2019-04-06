<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="mvcPath" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../vuetemplate/css_vue_template.jsp" flush="true"></jsp:include>
    <style>
        body {
            font-size: 10px;
            color: #0b6cbc;
        }

        .time {
            font-size: 13px;
            color: #999;
        }

        .bottom {
            margin-top: 13px;
            line-height: 12px;
        }

        .button {
            padding: 6px;
            float: right;
        }

        .image {
            width: 100%;
            display: block;
            height: 200px;
        }

        .clearfix:before,
        .clearfix:after {
            display: table;
            content: "";
        }

        .clearfix:after {
            clear: both
        }

        .el-col-offset-0 {
            margin-left: 15px;
            margin-bottom: 5px;
        }

        .el-col-offset-2 {
            margin-left: 15px;
            margin-bottom: 5px;
        }

        .el-input-number--mini {
            width: 100px;
        }
    </style>
</head>
<body>

<div id="app">

    <el-row>
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
    </el-row>

    <el-row>
        <el-col :span="7" v-for="(item, index) in cardResults" :key="item.id" :offset="index > 0 ? 2 : 0">
            <el-card :body-style="{ padding: '0px' }">
                <img :src="item.imgSrc" class="image">
                <div style="padding: 10px;">
                    <span>{{ item.name }}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="bottom clearfix">
                        <span>商家:{{ item.username }}</span>
                    </div>
                    <div class="bottom clearfix">
                        <span>分类:{{ formatFwdim("SPFL", item.goodsClassify) }}</span>
                    </div>
                    <div class="bottom clearfix">
                        <span>上新时间:{{ item.updateTime }}</span>
                    </div>
                    <div class="bottom clearfix">
                        <span>单价:￥{{ item.price }}元</span>
                        <span style="float: right">库存:{{ item.num }}个</span>
                    </div>
                    <div class="bottom clearfix">
                        <el-button type="primary" class="button" @click="handleOpen(item)">加入购物车</el-button>
                    </div>
                </div>
            </el-card>
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
                加入购物车
            </div>

            <el-form ref="rowForm" label-width="80px" v-model="rowForm" size="mini">
                <el-form-item label="商品名称:">
                    {{cardResultsRow.name}}
                </el-form-item>
                <el-form-item label="商家:">
                    {{cardResultsRow.username}}
                </el-form-item>
                <el-form-item label="分类:">
                    {{ formatFwdim("SPFL", cardResultsRow.goodsClassify) }}
                </el-form-item>
                <el-form-item label="上新时间:">
                    {{cardResultsRow.updateTime}}
                </el-form-item>
                <el-form-item label="单价:">
                    ￥{{cardResultsRow.price}}元
                </el-form-item>
                <el-form-item label="选择数量:">
                    <el-input-number v-model="rowForm.number" :precision="0" size="mini" :min="1"></el-input-number>
                </el-form-item>
                <el-form-item>
                    <el-col :span="24">
                        <el-button type="primary" @click="onSubmit('rowForm')">加入购物车</el-button>
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
                cardResults:[],
                pageInfo: {
                    currentPage: 1,
                    pageSize: 6,
                    total: 0,
                },
                dialogVisible: false,
                cardResultsRow:{},
                rowForm: {
                    id:"",
                    number:1,
                },
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
                this.addShopcart();
            },
            search() {
                let self = this;
                self.pageInfo.currentPage = 1;
                self.queryGoods();
            },
            //关于分页
            handleSizeChange:function(val){
                let self = this;
                this.pageInfo.pageSize = val;
                self.queryGoods();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryGoods();
            },
            handleOpen(row) {
                let self = this;
                //打开模版框
                this.dialogVisible = true;
                this.cardResultsRow = row;
                this.rowForm.id = row.id;//去当前商品id
                self.rowForm.number = 1;//重置为1
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
                    self.queryGoods();
                }.bind(this));
            },
            queryGoods: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/goodsIndex/queryGoods',
                    {
                        name: self.formInline.name,
                        goodsClassify: self.formInline.goodsClassify,
                        currentPage: self.pageInfo.currentPage,
                        pageSize: self.pageInfo.pageSize
                    }
                )
                    .then(function (response) {
                        const data = response.data;
                        if(data) {
                            self.cardResults = data.list;
                            self.pageInfo.total = data.totalCount;
                        }
                    }.bind(this))
                    .catch(function (error) {
                    }.bind(this));
            },
            addShopcart: function () {
                let self = this;
                axios.post(contextPath + '/rest/api/goodsIndex/addShopcart',
                    {
                        id: self.rowForm.id,
                        number: self.rowForm.number,
                    }
                )
                    .then(function (response) {
                        const data = response.data;
                        self.$message(data.msg);
                        self.dialogVisible = false;
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
