<%--
  Created by IntelliJ IDEA.
  User: pioter
  Date: 25.08.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<form method="post">
    <label>Cena minimalna:</label>
    <input name="min" value="0">
    <label>Cena maksymalna:</label>
    <input name="max" value="1000000"><br>
    <label><h2>Kategorie:</h2></label>
    <c:forEach var="category" items="${categories}">
        <input type="checkbox" name="category" value="${category.name}" Checked>${category.name}
        </c:forEach><br>
    <input type="submit">
</form>
<c:forEach items="${adverts}" var="advert">
    <br><br>
    <p>--------------------------------------------</p>
    <h4>${advert.category.name}</h4>
    <h1><a href='<c:url value="/adds/${advert.id}" />'>${advert.title}</a></h1>
    <h3>Cena: ${advert.price}PLN</h3>
    <p>Utworzono: ${advert.created.toString()}</p>
    <p>--------------------------------------------</p>
    <br><br>
</c:forEach>

</body>
</html>
