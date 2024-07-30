<%--
  Created by IntelliJ IDEA.
  User: Ahmed
  Date: 7/30/2024
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Date of Birth: <%= request.getAttribute("dob") %></p>
<p>Age: <%= request.getAttribute("years") %> years, <%= request.getAttribute("months") %> months, <%= request.getAttribute("days") %> days, <%= request.getAttribute("hours") %> hours</p>
</body>
</html>
