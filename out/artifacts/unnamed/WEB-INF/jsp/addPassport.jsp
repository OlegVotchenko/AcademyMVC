<%--
  Created by IntelliJ IDEA.
  User: strazhko
  Date: 17.08.2017
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Passport</title>
</head>
<body>

<form action="add_passport_action" method="post">
    <div>
        <label for="first_name">First Name</label>
        <input id="first_name" name="first_name">
    </div>
    <div>
        <label for="last_name">Last Name</label>
        <input id="last_name" name="last_name">
    </div>
    <input type="submit" value="Submit">
    <input type="reset">
</form>

</body>
</html>
