<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/14/2021
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>PRODUCER</td>
        <td>PRICE</td>
    </tr>
    <c:forEach var="p" items="products">
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.producer}</td>
        <td>${p.price}</td>
    </c:forEach>
</table>

</body>
</html>
