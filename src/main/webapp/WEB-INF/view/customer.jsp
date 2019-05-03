<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2019/5/3
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
<h1>客户列表</h1>

<table>
    <tr>
        <th>客户名称</th>
        <th>联系人</th>
        <th>电话号码</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.contact}</td>
            <td>${customer.telephone}</td>
            <td>${customer.email}</td>
            <td></td>

        </tr>
    </c:forEach>

</table>

</body>
</html>
