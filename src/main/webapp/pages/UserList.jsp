<%--
  Created by IntelliJ IDEA.
  User: mansa
  Date: 8/15/2023
  Time: 7:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib   uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>userList</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">username</th>
        <th scope="col">email</th>
        <th scope="col">address</th>
        <th scope="col">password</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.password}</td>
            <td>
                <a href="user?page=userdetails&id=${user.id}">user details</a>
            </td>

            <td>
                <form action="user?page=delete" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <td>
        <a href="user?page=adduser">add user</a>
    </td>

    </tbody>
</table>
</body>
</html>
