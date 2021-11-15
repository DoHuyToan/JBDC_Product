<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/14/2021
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <h2> <a href="/products">RETURN LIST OF PRODUCTS</a> </h2>
    <table border="1" cellpadding="5">
        <tr>
            <td>NAME</td>
            <td>PRODUCER</td>
            <td>PRICE</td>
            <td>ACTION</td>
        </tr>
        <tr>
            <td>
                <input type="text" name="name" placeholder="Nhap ten">
            </td>
            <td>
                <input type="text" name="producer" placeholder="Nhap Nha san xuat">
            </td>
            <td>
                <input type="text" name="price" placeholder="Nhap gia">
            </td>
            <td>
                <input type="submit" value="add">
            </td>

        </tr>
    </table>
</form>

</body>
</html>
