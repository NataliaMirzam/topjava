<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Table of meals</title>
</head>
<body>
<h2 align=center>Моя еда</h2>
<table border=1 align=center cellpadding=10>
<tr>
<th>Дата/Время</th>
<th>Описание</th>
<th>Калории</th>
</tr>
<% List<MealTo> mealsTo = (List<MealTo>) request.getAttribute("mealsTo");
%>
<c:forEach items = "${mealsTo}" var="meal">
    <tr style="color:${meal.isExcess() ? 'green' : 'red'}">
        <fmt:parseDate value = "${meal.getDateTime()}" var = "dateTime" pattern = "yyyy-MM-dd'T'HH:mm" type="both" />
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dateTime}" /></td>
        <td><c:out value = "${meal.getDescription()}"/></td>
        <td><c:out value = "${meal.getCalories()}"/></td>
        </font>
    </tr>
</c:forEach>
</table>
</body>
</html>