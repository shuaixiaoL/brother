<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <title>攀枝花学院创新创业管理系统</title>
</head>
<body>

    <script type="text/javascript">
        <%--window.open('${path}/index.do','_parent');--%>
        window.open('${path}/rest/login','_parent');
    </script>
</body>
</html>
