<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<!-- basic scripts -->
<!--[if !IE]> 如果是不是ie -->
<script src="${path}/static/lib/ace/assets/js/jquery-2.0.3.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
[endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${path}/static/lib/ace/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>
<!-- [endif]-->
<!--[if IE]
<script type="text/javascript">window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");</script>
<!--[endif]-->

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='${path}/static/lib/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<!--  bootstarp -->
<script src="${path}/static/lib/ace/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}/static/lib/ace/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts 最后面-->

<!-- ace scripts -->
<script src="${path}/static/lib/ace/assets/js/ace-elements.min.js"></script>
<script src="${path}/static/lib/ace/assets/js/ace.min.js"></script>

<script src="${path}/static/lib/ace/assets/js/jquery.dataTables.min.js"></script>
<script src="${path}/static/lib/ace/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="${path}/static/lib/ace/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${path}/static/lib/ace/assets/js/add/date-time/bootstrap-datepicker.zh-CN.js"></script>

<!--  bootstarp -->

<script src="${path}/static/lib/ace/bootstrap/plugin/bootstrap-table/js/bootstrap-table.min.js"></script>
<script src="${path}/static/lib/ace/bootstrap/plugin/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>

<script src="${path}/static/lib/sweetalert/sweetalert-dev.js"></script>

<!-- 以下是自己引入的-->
<script src="${path}/static/lib/ace/bootstrap/add/js/sidebar-menu/sidebar-menu.js"></script>
<script src="${path}/static/lib/ace/bootstrap/add/js/bootstrap-tab/bootstrap-tab.js"></script>

<%--elemrnt和vue--%>
<!-- import Vue before Element -->
<script src="${path}/static/lib/vue/vue.js"></script>
<!-- import JavaScript -->
<script src="${path}/static/lib/element/js/index.js"></script>
<!-- import JavaScript -->
<script type="text/javascript" src="${path}/static/lib/axios.min.js"></script>