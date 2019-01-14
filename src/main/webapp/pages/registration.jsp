<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Registration</title>

    <!--reset.css-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/reset.css"/>"/>
    <!--my styles-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/registrationStyle.css"/>"/>
</head>
<body>
    <h2>Registration</h2>
    <div class="components">
       <form class="form" id ="myform" method="post" action="">
           <p><label for="name">Full name </label><input name="name" id="name"type="text"><br></p>
           <p><label for="address">Address </label><input name="address" id="address" type="text"><br></p>
           <br><br>
           <p><label for="login">Login </label><input name="login" id="login" type="text"><br></p>
           <p><label for="password">Password </label><input name="password"  id="password" type="password"><br></p>
           <p><label for="password2">Re-enter Password </label><input name="password2" id="password2" type="password"><br></p>
           <p class="loginError">${errorMessage}</p>
           <button class="button" type="button" onclick="validateRegistrationForm(this.form)" name="registration">Registration</button>
           <button class="button" type="reset" name="reset">Reset</button>
        </form>
    </div>
    <script src="<c:url value="/scripts/errors.js"/>"></script>
    <script src="<c:url value="/scripts/validation.js"/>"></script>
</body>
</html>
