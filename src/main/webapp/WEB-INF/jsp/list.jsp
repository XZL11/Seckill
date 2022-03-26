<%--
  Created by IntelliJ IDEA.
  User: XZLxi
  Date: 2022/3/19
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
    <head>
        <title>活动报名系统</title>
        <%@include file="common/head.jsp"%>
    </head>
    <body>
        <!-- 页面显示部分 -->
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading text-context">
                    <h2>活动列表</h2>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>活动名称</th>
                            <th>剩余名额</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>活动时间</th>
                            <th>详情页</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="sk">
                                <tr>
                                    <td>${sk.name}</td>
                                    <td>${sk.number}</td>
                                    <td>
                                        <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td><a class="btn btn-info" href="${pageContext.request.contextPath}/${sk.seckillId}/detail" target="_blank">详情</a> </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>
