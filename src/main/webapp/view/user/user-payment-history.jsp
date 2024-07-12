<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/11/2024
  Time: 8:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment history</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 610px;
            overflow: auto;
        }

        td {
            font-size: smaller;
        }

        .date-field {
            width: 10rem;
            height: 38px;
            padding-left: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
        }

        .search-field {
            width: 25rem;
            height: 38px;
            padding-left: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class="fw-bold">Payment history</h2>
                    <div class="d-flex justify-content-between">
                        <form>
                            <input class="date-field" type="date" id="filterDate" name="date">
                            <button class="btn btn-success" style="border-radius: 0px; margin-bottom: 4px"
                                    type="submit">Filter By Date
                            </button>
                        </form>
                        <form>
                            <div class="d-flex justify-content-start">
                                <input type="text" class="search-field" placeholder="Search..." name="title">
                                <button type="submit" class="input-group-text border-0"><i
                                        class="lni lni-search-alt"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table">
                                    <form>
                                        <table class="table table-striped-columns text-center">
                                            <tr>
                                                <th>No</th>
                                                <th>Payment Type</th>
                                                <th>Total Amount</th>
                                                <th>Status</th>
                                                <th>Date Created</th>
                                            </tr>
                                            <% int noId = 1;%>
                                            <c:forEach var="bill" items="${requestScope.billList}">
                                                <tr>
                                                    <td><%=noId++%>
                                                    </td>
                                                    <td>${bill.getBillName()}</td>
                                                    <c:set var="amount" value="${bill.getTotalAmount()}" />
                                                    <fmt:formatNumber value="${amount}" var="formattedAmount" type="number" groupingUsed="true" />
                                                    <td>${formattedAmount} VND</td>
                                                    <c:choose>
                                                        <c:when test="${bill.getBillStatus().getStatusName().equals('pending')}">
                                                            <td style="color: orange">${bill.getBillStatus().getStatusName()}</td>
                                                        </c:when>
                                                        <c:when test="${bill.getBillStatus().getStatusName().equals('paid')}">
                                                            <td style="color: green">${bill.getBillStatus().getStatusName()}</td>
                                                        </c:when>
                                                        <c:when test="${bill.getBillStatus().getStatusName().equals('not paid')}">
                                                            <td style="color: red">${bill.getBillStatus().getStatusName()}</td>
                                                        </c:when>
                                                    </c:choose>
                                                    <td>${bill.getDateCreated()}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </form>
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
