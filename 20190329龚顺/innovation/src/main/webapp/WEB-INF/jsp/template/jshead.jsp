<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<%
    String contextPath =request.getContextPath();
    String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
%>
<script type="text/javascript">
    var contextPath = '<%=contextPath %>';
    //        debugger;
    var bashPath = '<%=basePath %>';
</script>

<!-- ace settings handler ACE设置处理程序 -->
<script src="${path}/static/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries HTML5的垫片和respond.js IE8支持HTML5元素和媒体查询-->
<!--[if lt IE 9]
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.min.js"></script>
[endif]-->


