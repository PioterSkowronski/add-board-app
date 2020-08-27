<%--
  Created by IntelliJ IDEA.
  User: pioter
  Date: 26.08.2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${advert.title}</title>
</head>
<body>
<br><br>
<p>--------------------------------------------</p>
<h1>${advert.title}</h1>
<h3>${advert.description}</h3>
<h3>Cena: ${advert.price}PLN</h3>
<h3>Kontakt: ${advert.user.firstName}   tel. ${advert.user.phoneNumber}   Email: ${advert.user.email}</h3>
<p>Utworzono: ${advert.created.toString()}</p>
<p>--------------------------------------------</p>
<br><br>

</body>
</html>
