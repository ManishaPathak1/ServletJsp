<%--
  Created by IntelliJ IDEA.
  User: mansa
  Date: 8/17/2023
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib   uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="user?page=updateUser" method="POST">
    <div class="container">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="hidden" name="id" value="${user.id}">
            <input type="text" name="userName" class="form-control" value="${user.userName}"  id="username">
        </div>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" name="email" value="${user.email}"  class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" name="address" value="${user.address}" class="form-control" id="address">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="password" value="${user.password}" class="form-control" id="exampleInputPassword1">
        </div>
        <input type="submit" value="UPDATE" name="submit">
    </div>
</form>

</body>
</html>
