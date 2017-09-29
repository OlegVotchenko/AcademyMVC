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
<h1>Students</h1>

<table>
    <tr>
        <td>id</td>
        <td>firstName</td>
        <td>lastName</td>
        <td>Date</td>
        <td></td>

    </tr>
    <c:forEach items="${students}" var="students">
        <tr>
            <td>${students.id}</td>
            <td>${students.passport.firstName}</td>
            <td>${students.passport.lastName}</td>
            <td>${students.birthday}</td>
            <td><a href="<c:url value="/deleteStudent/${students.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>

    <%--${students}--%>
    <%--<c:forEach items="${passports}" var="passport">--%>
        <%--<td>${passport.id}</td>--%>
        <%--<td>${passport.firstName}</td>--%>
        <%--<td>${passport.lastName}</td>--%>
    <%--</c:forEach>--%>


</table>






</body>
</html>
