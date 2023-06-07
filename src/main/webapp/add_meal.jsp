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
<h2 align=center>Add meal</h2>
<form action="meals?action=add" method="post">
<table border=0 align=center cellpadding=10>
<tr>
<td>DateTime</td>
<td><input type="datetime-local" name="datetime" value=""></td>
</tr>
<tr>
<td>Description</td>
<td><input type="text" name="description"></td>
</tr>
<tr>
<td>Calories</td>
<td><input type="text" name="calories"></td>
</tr>
<tr>
<td rowspan=3>
<button type="submit">Save</button>
<button><a href="meals">Cancel</a></button>
</td>
</tr>
</table>
</form>
</body>
</html>