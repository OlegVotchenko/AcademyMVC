<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: strazhko
  Date: 22.08.2017
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="add_student_action" method="post">
    <%--add data here--%>
    <label for="date">Date</label>
    <input id="date" type="date" name="date"/>

    <select name="passportId">
        <option>--Create--</option>
        <c:forEach items="${passports}" var="passport">
            <option value="${passport.id}">${passport.id} ${passport.firstName} ${passport.lastName}</option>
        </c:forEach>
    </select>

    <input type="submit" value="submit">
    <input type="reset">

</form>

</body>
</html>
