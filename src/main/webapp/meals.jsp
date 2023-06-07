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
<h3><a href="index.html">Home</a></h3>
<hr>
<h2 align=center>Моя еда</h2>
<a href="meals?action=add">Add Meal</a>
<table border=1 align=center cellpadding=10>
<tr>
<th>Дата/Время</th>
<th>Описание</th>
<th>Калории</th>
<th></th>
<th></th>
</tr>
<c:forEach items = "${mealsTo}" var="meal">
    <tr style="color:${meal.isExcess() ? 'green' : 'red'}">
        <fmt:parseDate value = "${meal.getDateTime()}" var = "dateTime" pattern = "yyyy-MM-dd'T'HH:mm" type="both" />
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dateTime}" /></td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
        <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>