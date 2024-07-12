<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/28/2024
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-style.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class=" fw-bold ">Dashboard</h2>
                    <div class="row">
                        <div class="col-12 col-md-3">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <h6 class="mb-2 text-uppercase"><a style="color: black" href="${pageContext.request.contextPath}/admin/students">Total Account</a></h6>
                                    <div class="d-flex justify-content-between">
                                        <div style="color: green" class="mb-2">
                                            <!--write func to count total students and insert total here -->
                                            <span class="mb-2 fs-2">${requestScope.numberOfAccount}</span>
                                        </div>
                                        <i class="lni lni-users" style="font-size: 60px"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-3">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <h6 class="mb-2 text-uppercase"><a style="color: black" href="${pageContext.request.contextPath}/admin-dorm">Total Dorms</a></h6>
                                    <div class="d-flex justify-content-between">
                                        <div style="color: green" class="mb-2">
                                            <!--write func to count total rooms and insert total here -->
                                            <span class="mb-2 fs-2">${requestScope.numberOfDorm}</span>
                                        </div>
                                        <i class="lni lni-apartment" style="font-size: 60px"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-3">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <h6 class="mb-2 text-uppercase"><a style="color: black" href="${pageContext.request.contextPath}/admin-room">Total Rooms</a></h6>
                                    <div class="d-flex justify-content-between">
                                        <div style="color: green" class="mb-2">
                                            <!--write func to count total slots and insert total here -->
                                            <span class="mb-2 fs-2">${requestScope.numberOfRoom}</span>
                                        </div>
                                        <i class="lni lni-home" style="font-size: 60px"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-3">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <h6 class="mb-2 text-uppercase"><a style="color: black" href="${pageContext.request.contextPath}/admin-application">Application</a></h6>
                                    <div class="d-flex justify-content-between">
                                        <div style="color: green" class="mb-2">
                                            <!--write func to count total request and insert total here -->
                                            <span class="mb-2 fs-2"></span>
                                        </div>
                                        <i class="lni lni-envelope" style="font-size: 60px"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
</body>
</html>
