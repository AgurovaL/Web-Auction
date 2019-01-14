<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Edit items</title>
    <!--reset.css-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/reset.css"/>"/>
    <!--my styles-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/createItemStyle.css"/>"/>
</head>
<body>
    <header>
        <p id="username">You are logged in as <%= request.getAttribute("login")%></p>
        <a class="ref" href="/login">logout / login</a>
    </header>
    <h2>Online marketplace</h2>
    <div class="mainComponents">
        <form class="form" method="post" action="">
            <p><label for="title">Title </label><input id="title" type="text" name="title"><br></p>
            <p><label for="description">Description </label><input id="description" type="text" name="description"><br></p>
            <br><br>
            <p><label for="startPrice">Start Price </label><input id="startPrice" type="text" name="startPrice"><br></p>
            <p><label for="bidInc">Bid inc </label><input id="bidInc" type="text" name="bidInc"><br></p>
            <p>
                <label for="stopDate">Stop date </label>
                <input id="stopDate" type="date" placeholder="click to enter date" name="stopDate"><br>
            </p>
            <button class="button" type="button" id="save" onclick="validateElementForm(this.form)">Save</button>
            <button class="button" type="reset" id="reset">Reset</button>
            <p class="errorMessage">${errorMessage}</p>
        </form>
        <div class="links">
            <a class="ref" href="/showItems">Show All Items</a><br>
            <a class="ref" href="/showMyItem">Show My Items</a><br>
            <a class="ref" href="/createItemServlet">Sell</a><br>
        </div>
    </div>
    <script src="<c:url value="/scripts/errors.js"/>"></script>
    <script src="<c:url value="/scripts/validation.js"/>"></script>
</body>
</html>