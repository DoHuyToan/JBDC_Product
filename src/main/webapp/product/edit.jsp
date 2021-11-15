<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/14/2021
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> <a href="/products">RETURN LIST OF PRODUCTS</a> </h2>
<form method="post">
    <table>
        <c:if test="${pro != null}">
            <input type="hidden" name="id" value="${pro.getId()}"/>
        </c:if>
        <tr>
            <td>NAME</td>
            <td>PRODUCER</td>
            <td>PRICE</td>
            <td>ACTION</td>
        </tr>
        <tr>
            <td><input type="text" name="name" value="${pro.getName()}" placeholder="Nhap Ten" ></td>
            <td><input type="text" name="producer" value="${pro.getProducer()}" placeholder="Nhap Nha san xuat" ></td>
            <td><input type="text" name="price" value="${pro.getPrice()}" placeholder="Nhap Gia" ></td>
            <td><input type="submit" value="edit"></td>
        </tr>
    </table>
</form>
</body>
</html>
