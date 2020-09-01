<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tablica ogłoszeń</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href='<c:url value="/css/sb-admin-2.css"/>' rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Tablica ogłoszeń</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <sec:authorize access="!isAuthenticated()">
            <li class="nav-item">
                <a class="nav-link" href="/login">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Zaloguj się</span></a>
            </li>
        </sec:authorize>
        <sec:authorize url="/admin">
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/admin"/>'>
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Panel administratora</span></a>
            </li>
        </sec:authorize>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Filtr
        </div>
        <form id="filter" method="post">
            <label>  Cena minimalna:</label>
            <input name="min" value="0">
            <label>  Cena maksymalna:</label>
            <input name="max" value="1000000"><br>
            <label >Kategorie:</label><br>
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="category" value="${category.name}" Checked>${category.name}<br>
            </c:forEach><br>
            <input type="submit" value="Zatwierdź">
        </form>


        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form action="/search" method="get" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" name="value" class="form-control bg-light border-0 small" placeholder="Wyszukaj ogłoszenie..." aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <input class="btn btn-primary" type="submit">
                                <i class="fas fa-search fa-sm"></i>
                            </input>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search" action="/search" method="get">
                                <div class="input-group">
                                    <input type="text" name="value" class="form-control bg-light border-0 small" placeholder="Wyszukaj ogłoszenie..." aria-label="Search" aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <input class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>
                                        </input>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <sec:authorize access="isAuthenticated()">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Panel użytkownika</span>
                            </sec:authorize>
                            <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/home">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Moje ogłoszenia
                            </a>
                            <div class="dropdown-divider"></div>
                            <form action="<c:url value="/logout"/>" method="post" class="dropdown-item">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                <input type="submit" value="Wyloguj">
                            </form>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Wszystkie ogłoszenia: </h1>

                <c:forEach items="${adverts}" var="advert">
                    <br>
                    <p>--------------------------------------------</p>
                    <h4>${advert.category.name}</h4>
                    <h1><a href='<c:url value="/adds/${advert.id}" />'>${advert.title}</a></h1>
                    <h3>Cena: ${advert.price}PLN</h3>
                    <p>Utworzono: ${advert.created.toString()}</p>
                    <p>--------------------------------------------</p>
                    <br>
                </c:forEach>



            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

</html>
