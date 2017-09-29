<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Spring</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<c:if test="${param.error != null}">
    <div id="errorBlock">
        ${param.error}
    </div>
</c:if>
<h1>Passports</h1>
<table>
    <tr>
        <td>id</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td></td>
    </tr>

    <c:forEach items="${passports}" var="passport">
        <tr>
            <td>${passport.id}</td>
            <td>${passport.firstName}</td>
            <td>${passport.lastName}</td>
            <td><a href="<c:url value="/deletePassport/${passport.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>


</table>

<a class="menuItem" href="<c:url value="/add_passport"/>"> Add Passport </a>
<a class="menuItem" href="<c:url value="/students"/>">Show Students</a>
<a class="menuItem" href="<c:url value="/add_student"/>">Add Students</a>


</body>
</html>
