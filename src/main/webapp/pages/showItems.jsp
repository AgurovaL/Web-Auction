<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Show Items</title>

     <!--reset.css-->
     <link rel="stylesheet" type="text/css" href="<c:url value="/styles/reset.css"/>"/>
     <!--my styles-->
     <link rel="stylesheet" type="text/css" href="<c:url value="/styles/style.css"/>"/>
     <link rel="stylesheet" type="text/css" href="<c:url value="/styles/showItemsStyle.css"/>"/>
</head>
<body>
    <header>
        <p id="username">You are logged in as <%= request.getAttribute("login")%></p>
        <span><a class="ref" class="logout" href="/login">logout / login</a></span>
    </header>
    <h2>Online marketplace</h2>
    <p>Search parameters</p>
    <div class="mainComponents">
        <form class="form" id ="keyForm" method="post" action="/searchItem">
            <p><label for="keyword">Keyword:</label><br></p>
            <input name="keyword" type="text">
            <button class="button" type="submit" id="search">Search</button>
        </form>
        <div class="links">
            <a class="ref" href="/showItems">Show All Items</a><br>
            <a class="ref" href="/showMyItem">Show My Items</a><br>
            <a class="ref" href="/createItem">Sell</a><br>
        </div>
    </div>

    <table class="itemsTable">
        <thead>Items</thead>
        <tr>
            <th>UID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Seller</th>
            <th>Start Price</th>
            <th>Bid inc</th>
            <th>Best offer</th>
            <th>Bidder</th>
            <th>Stop date</th>
            <th>Bidding</th>
        </tr>
        <c:forEach var="item" items="${itemsList}">
        <tr>
            <td>${item.getItemId()}</td>
            <td>${item.getTitle()}</td>
            <td>${item.getDescription()}</td>
            <td>${item.getSeller()}</td>
            <td>${item.getStartPrice()}</td>
            <td>${item.getBidIncrement()}</td>
            <td>${item.calcBestOffer()}</td>
            <td>${item.getFirstBidder()}</td>
            <td><fmt:formatDate value="${item.getStopDate()}" pattern="dd-MM-yy"/></td>
            <td class="bid">

                <form class="form" id ="${item.getItemId()}" method="post" action="/makeBid" commandName="item">
                   <input type="text" name="bid" id="bid">
                   <input type="hidden" name="itemId" value="${item.getItemId()}">
                   <button class="button" onclick="validateBidForm(${item.getItemId()})" type="button">Bid</button>
                   <p class="errorMessage">${errorMessage}</p>
                </form>

            </td>
         </c:forEach>
        </tr>
    </table>
    <p class="errorMessage">${errorMessage}</p>
    <script src="<c:url value="/scripts/errors.js"/>"></script>
    <script src="<c:url value="/scripts/validation.js"/>"></script>
</body>
</html>




