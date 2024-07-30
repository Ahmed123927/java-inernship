<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Factorial Table</title>

</head>
<body>

<table border="1">
    <tr>
        <th>Number</th>
        <th>Factorial</th>
    </tr>
    <%
        for (int i = 0; i <= 10; i++) {
            long factorial = calculateFactorial(i);
    %>
    <tr>
        <td><%= i %></td>
        <td><%= factorial %></td>
    </tr>
    <% } %>
</table>
</body>
</html>

<%!

    private long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }
%>
