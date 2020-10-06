<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--静态包含 base标签、css样式、jQuery--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            $("#delete").click(function (){
                var deleteBookName = $(this).parent().parent().find("td:first").text();
                return confirm("你确定要删除【"+ deleteBookName +"】吗？");
                // return flase：代表不提交申请
                // return true：代表提交申请
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--静态包含manager_menu--%>
    <%@include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

<%--未分页--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a id="update" href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}&method=update">修改</a></td>
                <td><a id="delete" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageNo}">添加图书</a></td>
        </tr>

    </table>
<%--    静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>
<%--静态包含页脚信息--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>