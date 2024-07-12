<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.News" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="entity.Information" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/20/2024
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class="fw-bold">Home</h2>
                    <div class="row">
                        <div class="col-md-8">
                            <div style="background-color: #198754" class="card border-1">
                                <div class="card-body py-1">
                                    <h5 style="color: white; margin-bottom: 1px">News</h5>
                                </div>
                            </div>
                            <div style="margin-top: 1rem" class="card border-1">
                                <div class="card-body py-4">
                                    <div class="row">
                                        <c:forEach var="news" items="${requestScope.newsList}">
                                            <div class="col-12 col-md-6">
                                                <a href="${pageContext.request.contextPath}/user-news-detail?id=${news.getNewsId()}">
                                                    <h5>${news.getTitle()}</h5></a>
                                                <p>${news.getDateCreated()}</p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <h5 class="mb-2" style="color: #e6b400">
                                        Personal information
                                    </h5>
                                    <div>
                                        <p>Name: ${requestScope.information.getFullName()}</p>
                                        <p>DOB: ${requestScope.information.getDob()}</p>
                                        <p>Gender: ${requestScope.information.getGender()}</p>
                                        <%
                                            double amount = (Double) ((Information) request.getAttribute("information")).getBalance();
                                            NumberFormat formatter = NumberFormat.getInstance();
                                            String formattedAmount = formatter.format(amount);
                                        %>
                                        <p>Balance: <%=formattedAmount%> VND</p>
                                        <p>Room: <a
                                                href="${pageContext.request.contextPath}/user/myroom">${requestScope.information.getRoomName()}</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="card border-1 mt-4">
                                <div class="card-body py-4">
                                    <h5 class="mb-2" style="color: #e6b400">
                                        Contact
                                    </h5>
                                    <div>
                                        <p>Security room: (024) 6680 5 913</p>
                                        <p>Health station: (024) 6680 5 917</p>
                                        <p>Dormitory management: (024) 7308 1313 (Office hours)</p>
                                        <p>Email: ktx@fpt.edu.vn</p>
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
