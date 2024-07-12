<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/8/2024
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Dorm</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        a {
            cursor: pointer;
        }

        td {
            font-size: smaller;
        }

        td table {
            font-size: medium;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <div class="row">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h2 class=" fw-bold">Choose Dorm</h2>
                            </div>
                        </div>
                        <div class="col-12 mt-4">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col">No.</th>
                                        <th scope="col">Dorm name</th>
                                        <th scope="col">Total floor</th>
                                        <th scope="col">Total room</th>
                                        <th scope="col">Available</th>
                                        <th scope="col">Status</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="d" items="${requestScope.dormList}">
                                        <tr>
                                            <td>${d.dormId}</td>
                                            <td>${d.dormName}</td>
                                            <td>${d.numberOfFloor}</td>
                                            <td>${d.numberOfRoom}</td>
                                            <td>${d.available}</td>
                                            <c:choose>
                                                <c:when test="${d.status.statusName.equals('active')}">
                                                    <td style="color: green">${d.status.statusName}</td>
                                                </c:when>
                                                <c:when test="${d.status.statusName.equals('inactive')}">
                                                    <td style="color: red">${d.status.statusName}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td style="color: #e6b400">${d.status.statusName}</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                <c:if test="${d.getStatus().getStatusName().equals('active') || d.getStatus().getStatusName().equals('maintenance')}">
                                                    <a href="${pageContext.request.contextPath}/user-dorm-detail?dormName=${d.dormName}">Detail</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
