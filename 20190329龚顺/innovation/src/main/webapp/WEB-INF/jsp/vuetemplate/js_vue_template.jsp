<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<%--elemrnt和vue--%>
<script type="text/javascript" src="${path}/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src = "${path}/static/moment/moment.js"></script>
<!-- import Vue before Element -->
<script type="text/javascript" src="${path}/static/vue/js/vue.js"></script>
<!-- import JavaScript -->
<script type="text/javascript" src="${path}/static/element/js/index.js"></script>
<script type="text/javascript" src="${path}/static/js/axios.min.js"></script>

<script type="text/javascript" src="${path}/static/js/jsp/common.js"></script>
<script type="text/javascript" src="${path}/static/js/common.js"></script>
<script>
    initCommon();//初始化共同函数
</script>



