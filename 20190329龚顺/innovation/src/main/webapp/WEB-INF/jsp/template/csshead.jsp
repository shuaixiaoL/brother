<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<!-- basic styles -->
<!--  bootstarp -->
<link rel="stylesheet" href="${path}/static/lib/ace/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/font-awesome.min.css" />

<!--[if IE 7]
<link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
[endif]-->

<!-- page specific plugin styles 插件方式 在最后-->
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/datepicker.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/ui.jqgrid.css" />

<!-- fonts 改为本地-->
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/add/cyrillic.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/ace.min.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/assets/css/ace-skins.min.css" />
<!--[if lte IE 8]
<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
[endif]-->
<!--  bootstarp -->
<link rel="stylesheet" href="${path}/static/lib/ace/bootstrap/plugin/bootstrap-table/css/bootstrap-table.min.css"/>

<link rel="stylesheet" href="${path}/static/lib/sweetalert/sweetalert.css"/>
<!-- 自己编写引入 -->
<link rel="stylesheet" href="${path}/static/lib/ace/bootstrap/add/css/bootstrap-tab/bootstrap-tab.css" />
<link rel="stylesheet" href="${path}/static/lib/ace/bootstrap/add/css/sidebar-menu/sidebar-menu.css"/>



<!-- inline styles related to this page 与此页相关的内联样式 -->
<style type="text/css" rel="stylesheet">
    <%-- element --%>
    @import url("${path}/static/lib/element/css/index.css");

    .table th, .table td {
        text-align: center;
        vertical-align: middle!important;
    }
</style>

<script type="text/javascript">
    var sysUser = '${sysUser}';
    var numSession = '${user.num}';
    var levelSession1 = '${user.level}';
</script>
