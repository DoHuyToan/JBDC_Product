<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/14/2021
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>PRODUCTS MANAGER</h1>
<h2> <a href="/products?action=create">ADD NEW USER</a> </h2>
<h2> <a href="/products?action=find">FIND</a> </h2>
<form>
    <h2>FIND BY PRODUCER</h2>
    <input type="text" name="producer" placeholder="Nhap Nha san xuat">
    <input type="submit" value="find">
</form>
<table border="1" cellpadding="5">
    <caption> <h2>LIST OF PRODUCT</h2> </caption>
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>PRODUCER</td>
        <td>PRICE</td>
        <td>ACTION</td>
    </tr>
<%--    products: GỌI DANH SÁCH VÀO, var: PHẦN TỬ TRONG products--%>
    <c:forEach var="p" items="${products}">
        <tr>
            <td><c:out value="${p.id}"/></td>
            <td><c:out value="${p.name}"/></td>
            <td><c:out value="${p.producer}"/></td>
            <td><c:out value="${p.price}"/></td>
            <td>
                <a href="/products?action=edit&id=${p.id}">EDIT</a>
                <a href="/products?action=delete&id=${p.id}">DETELE</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
