<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>首页</title>

    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <jsp:include page="template/csshead.jsp" flush="true" />
    <style type="text/css">
        #Index {
            /*margin:-16px -12px;*/
            height: 654px;
            background:url("${path}/static/img/index.png") no-repeat right;
            background-size: cover;
            overflow: hidden;
        }
        .page-content {
            padding: 0px 20px 0px;
        }
        .tab-content {
            padding: 0px 12px;
        }
    </style>

    <jsp:include page="template/jshead.jsp" flush="true" />

</head>

<body>

<!--加入头部-->
<jsp:include page="layout/header.jsp" flush="true" />

<!--主内容区域-->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <!--左侧的导航栏-->
        <jsp:include page="layout/navigation.jsp"></jsp:include>

        <!--右侧的内容-->
        <div class="main-content">
            <!--右侧上方的导航指标和搜索按钮-->
            <%--<jsp:include page="layout/breadcrumbs.jsp"></jsp:include>--%>

            <!--中心的内容区域-->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12" style="padding-left:5px;">
                        <ul class="nav-my-tab">
                            <li class="rightforward"><a href="#">
                                <i class="icon-forward"></i></a>
                            </li>
                            <li class="leftbackward"><a href="#" >
                                <i class="icon-backward"></i></a>
                            </li>
                           <li class="middletab">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="active"><a href="#Index" role="tab" data-toggle="tab">
                                        <i class="icon-home"></i>系统首页</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="Index">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div><!-- /.main-content -->
        <!-- 加入边上皮肤选择 -->
       <%-- <jsp:include page="layout/settings.jsp" flush="true"></jsp:include>--%>
        <!--加入底部导航栏-->
        <jsp:include page="layout/footer.jsp" flush="true"/>
    </div><!-- /.main-container-inner -->


    <!-- 回到顶部按钮 -->
    <jsp:include page="layout/btnscrollup.jsp" flush="true"></jsp:include>

</div><!-- /.main-container -->
    <jsp:include page="template/jsbody.jsp" flush="true" />

<script src="${path}/static/js/jsp/index.js"></script>
<script>
    var index = new Index().init();
</script>
</body>
</html>
