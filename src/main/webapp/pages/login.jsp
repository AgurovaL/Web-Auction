<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/reset.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/style.css"/>"/>
</head>
<body>
    <h2>Login</h2>
    <div class="mainComponents">
        <form class="form" id ="myform" method="post" action="">
            <p>
                <label for="login">Login </label>
                <input type="text" name="login" id = "login"><br>
            </p>
            <p>
                <label for="password">Password </label>
                <input id = "password" name="password" type="password"><br>
             </p>
             <p class="errorMessage">${errorMessage}</p>
        </form>
        <div class="links">
            <a class="ref" onclick="validateLoginForm()">Sign In</a><br>
            <a class="ref" href="/showItems">Enter as guest</a><br>
            <a class="ref" href="/registration">Register</a><br>
        </div>
    </div>
<script src="<c:url value="/scripts/errors.js"/>"></script>
<script src="<c:url value="/scripts/validation.js"/>"></script>
</body>
</html>