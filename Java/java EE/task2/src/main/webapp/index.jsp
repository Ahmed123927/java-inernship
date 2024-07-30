<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<form method="post" action="ageCalculator">
  <label for="dob">Date of Birth:</label>
  <input type="date" id="dob" name="dob" required><br>
  <button type="submit">Calculate Age</button>
</form>
</body>
</html>