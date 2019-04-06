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
    <link type="text/css" href="${path}/static/css/jsp/param_sys.css"/>
</head>
<body>

<div id="app">

    <el-row :gutter="10">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <template>
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column prop="type" label="类型"></el-table-column>
                    <el-table-column prop="name" label="名称"></el-table-column>
                    <el-table-column prop="cnName" label="中文名"></el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="handleMush(scope.$index, scope.row)">细类</el-button>
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
                    :current-page="pageInfo.pageNum"
                    :page-size="pageInfo.pageSize"
                    layout="total, prev, pager, next"
                    :total="pageInfo.total"
                    background
            >
            </el-pagination>
        </el-col>
    </el-row>

    <%--弹出框（详细）--%>
    <template>
        <el-dialog title="" :visible.sync="outerVisible" class="customClass" top="true" width="80%" center>
            <div slot="title" class="dialog-header">
                显示详细
            </div>

            <el-form ref="tableFormRow" label-width="80px" v-model="tableDataRow" size="mini">
                <el-form-item label="项目名称">
                    {{tableDataRow.name}}
                </el-form-item>
                <el-form-item label="项目描述">
                    {{tableDataRow.descInfo}}
                </el-form-item>
                <el-form-item label="附件文档" v-if="tableDataRow.attachdoc != null">
                    {{tableDataRow.attachdoc}}
                    <%-- <el-input v-model="tableDataRow.attachdoc" :disabled="true"></el-input>--%>
                </el-form-item>
                <el-form-item label="申请时间" v-if="tableDataRow.sqTime != null">
                    {{tableDataRow.sqTime}}
                </el-form-item>
                <el-form-item label="申请状态" v-if="tableDataRow.applyStatus != null">
                    {{tableDataRow.applyStatusValue}}
                </el-form-item>
                <el-form-item label="组长" v-if="tableDataRow.compareRealName != null">
                    {{tableDataRow.compareRealName}}
                </el-form-item>
                <el-form-item label="成员一" v-if="tableDataRow.memberOneRealName != null">
                    {{tableDataRow.memberOneRealName}}
                </el-form-item>
                <el-form-item label="成员二" v-if="tableDataRow.memberTwoRealName != null">
                    {{tableDataRow.memberTwoRealName}}
                </el-form-item>
                <el-form-item label="指导老师" v-if="tableDataRow.teacherRealName != null">
                    {{tableDataRow.teacherRealName}}
                </el-form-item>
                <el-form-item label="驳回意见" v-if="tableDataRow.applyStatus == 5">
                    {{tableDataRow.teacherFinalreJection}}
                </el-form-item>
            </el-form>

            <%--<div slot="footer" class="dialog-footer">
                <el-button  type="primary"  size="mini" @click="outerVisible = false">确 定</el-button>
            </div>--%>
        </el-dialog>
    </template>

    <%--弹出框（提交）--%>
    <template>
        <el-dialog title="" :visible.sync="dialogVisible" class="customClass" top="true" width="80%" center>
            <div slot="title" class="dialog-header">
                {{tableDataRow.cnName}}
            </div>

            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <template>
                    <el-table :data="pageInfoSecund.list" style="width: 100%">
                        <el-table-column prop="keyset" label="键"></el-table-column>
                        <el-table-column prop="valueset" label="值"></el-table-column>
                        <el-table-column prop="remark" label="描述"></el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="handleMush(scope.$index, scope.row)">修改</el-button>
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
                            :current-page="pageInfoSecund.pageNum"
                            :page-size="pageInfoSecund.pageSize"
                            layout="total, prev, pager, next"
                            :total="pageInfoSecund.total"
                            background
                    >
                    </el-pagination>
                </el-col>
            </el-row>

            <%--<div slot="footer" class="dialog-footer">
                <el-button  type="primary"  size="mini" @click="outerVisible = false">确 定</el-button>
            </div>--%>
        </el-dialog>
    </template>

</div>

<jsp:include page="../vuetemplate/js_vue_template.jsp" flush="true"></jsp:include>

<script type="text/javascript" src="${path}/static/js/jsp/param_sys.js"></script>
</body>
</html>
