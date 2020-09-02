<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dodaj ogłoszenie</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href='<c:url value="/css/sb-admin-2.css"/>' rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Dodaj ogłoszenie!</h1>
                        </div>
                        <form:form cssClass="user" method="post"
                                   modelAttribute="advertisement">
                            <div class="form-group">
                                <form:input path="title" type="text" class="form-control form-control-user"
                                            placeholder="Tytuł"/>
                                <form:errors path="title"/><br>
                            </div>
                            <div class="form-group">
                                <form:textarea path="description" type="text" class="form-control form-control-user" id="exampleInputEmail"
                                               placeholder="Opis"/>
                                <form:errors path="description"/><br>
                            </div>
                            <div class="form-group">
                                <form:input path="price" type="text" pattern="([0-9]+\.[0-9]{2})|([0-9]+)|([0-9]+\.[0-9])" title="Wprowadź liczbę, jako separator użyj kropki" class="form-control form-control-user"
                                            placeholder="Cena"/>
                                <form:errors path="price"/><br>
                            </div>
                            <div class="form-group">
                                <form:select itemValue="id" itemLabel="name" path="category" items="${categories}" class="form-control form-control-user"/>
                            </div>
                            <input type="submit" value="Zapisz zmiany" class="btn btn-primary btn-user btn-block">

                        </form:form>
                        <span><h3>${message}</h3><br></span>

                        <div class="text-center">
                            <a class="small" href="/home">Wróć do panelu użytkownika</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

</html>