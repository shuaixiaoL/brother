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
        <el-col :span="23" v-for="(item, index) in cardResults" :key="item.id" :offset="index > 0 ? 2 : 0">
            <el-card :body-style="{ padding: '0px' }">
                <div style="padding: 10px;">
                    <div class="bottom clearfix">
                        <span>楼主:{{ item.username }}</span>
                        <span style="float: right">发布时间:{{ item.updateTime }}</span>
                    </div>
                    <div class="bottom clearfix">
                        <span>内容:{{ item.message }}</span>
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
                cardResults:[],
                pageInfo: {
                    currentPage: 1,
                    pageSize: 6,
                    total: 0,
                },
            }
        },
        methods: {
            search() {
                let self = this;
                self.pageInfo.currentPage = 1;
                self.queryMessageByStatus();
            },
            //关于分页
            handleSizeChange:function(val){
                let self = this;
                this.pageInfo.pageSize = val;
                self.queryMessageByStatus();
            },
            handleCurrentChange:function(val){
                this.pageInfo.currentPage = val;
                this.queryMessageByStatus();
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
                    self.queryMessageByStatus();
                }.bind(this));
            },
            queryMessageByStatus: function () {
                let self = this;
                axios.post(
                    contextPath + '/rest/api/messageIndex/queryMessageByStatus',
                    {
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
