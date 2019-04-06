<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String contextPath =request.getContextPath();
    String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<style>
    @import url("${path}/static/element/css/index.css");
    #app{
        width: 99%;
    }
</style>
<script type="text/javascript">
    var contextPath = '<%=contextPath %>';
    //        debugger;
    var bashPath = '<%=basePath %>';
    var local = document.location;
    var contextPort = local.protocol  + "//" + local.host;
    var numSession = '${user.num}';
    var levelSession = '${user.level}';
</script>