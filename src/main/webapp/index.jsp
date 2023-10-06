<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<form action="user?page=login" method="post">
    <div class="container">

        <div class="mb-3">
            <label for="exampleInputusername1" class="form-label">username</label>
            <input type="username" name="username" class="form-control" id="exampleInputusername1">
        </div>


        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1">
        </div>
        <a href="user?page=newuser"><br> New user register?<br></a>
        <button type="submit" class="btn btn-primary">login</button>
    </div>

</form>
</body>
</html>