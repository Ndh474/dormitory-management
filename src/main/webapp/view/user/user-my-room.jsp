<%@ page import="entity.Chart" %>
<%@ page import="services.InformationService" %>
<%@ page import="services.impl.InformationServiceImpl" %>
<%@ page import="entity.Account" %>
<%@ page import="entity.Information" %>
<%@ page import="services.RoomUsageService" %>
<%@ page import="services.impl.RoomUsageServiceImpl" %>
<%@ page import="util.AppConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/16/2024
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My room</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart', 'line']});
        google.charts.setOnLoadCallback(drawElectricChart);
        google.charts.setOnLoadCallback(drawWaterChart);

        function drawElectricChart() {
            var data = google.visualization.arrayToDataTable([
                ['Month', 'Electric usage'],
                <%
                  InformationService informationService = new InformationServiceImpl();
                  Account account = (Account) request.getSession().getAttribute("account");
                  Information information = informationService.getByAccountID(account.getAccountID());
                  RoomUsageService roomUsageService = new RoomUsageServiceImpl();
                  for (Chart c : roomUsageService.getUsagePerMonth(information.getRoomName())) {
              %>
                ['<%=c.getMonth()%>/<%=c.getYear()%>', <%=c.getElectric_usage()%>],
                <%}%>
            ]);
            var options = {
                height: 450,
                hAxis: {
                    title: 'Monthly electricity amount'
                },
                vAxis: {
                    title: ''
                },
                series: {
                    0: {color: 'red'}
                }
            };
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart_electric'));
            chart.draw(data, options);
        }

        function drawWaterChart() {
            var data = google.visualization.arrayToDataTable([
                ['Month', 'Water usage'],
                <%
                  for (Chart c : roomUsageService.getUsagePerMonth(information.getRoomName())) {
              %>
                ['<%=c.getMonth()%>/<%=c.getYear()%>', <%=c.getWater_usage()%>],
                <%}%>
            ]);
            var options = {
                height: 450,
                hAxis: {
                    title: 'Monthly water amount'
                },
                vAxis: {
                    title: ''
                }
            };
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart_water'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class="fw-bold">My room</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <h5 style="color: green">Room information</h5>
                            <!--update room detail form-->
                            <div class="d-flex justify-content-evenly">
                                <div>
                                    <table class="table table_style table-striped-columns">
                                        <tr>
                                            <td>Room Name</td>
                                            <td>${requestScope.room.getRoomName()}</td>
                                        </tr>
                                        <tr>
                                            <td>Dorm Name</td>
                                            <td>${requestScope.room.getDorm().getDormName()}</td>
                                        </tr>
                                        <tr>
                                            <td>Room Type</td>
                                            <td>${requestScope.room.getRoomType().getRoomCapacity()} - beds</td>
                                        </tr>
                                        <tr>
                                            <td><label>Room Gender</label></td>
                                            <td>${requestScope.room.getRoomGender()}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div>
                                    <table class="table table_style table-striped-columns">
                                        <tr>
                                            <td>Electric used previous month:</td>
                                            <td>${requestScope.roomUsagePreviousMonth.getElectricityUsed()}</td>
                                        </tr>
                                        <tr>
                                            <td>Water used previous month:</td>
                                            <td>${requestScope.roomUsagePreviousMonth.getWaterUsed()}</td>
                                        </tr>
                                        <tr>
                                            <td>Number electric used:</td>
                                            <td>${requestScope.roomUsageCurrentMonth.getElectricityUsed()}</td>
                                        </tr>
                                        <tr>
                                            <td>Number water used:</td>
                                            <td>${requestScope.roomUsageCurrentMonth.getWaterUsed()}</td>
                                        </tr>
                                        <tr>
                                            <td>Total Amount:</td>
                                            <c:set var="totalAmount" value="${requestScope.totalAmount}" />

                                            <fmt:formatNumber value="${totalAmount}" var="formattedTotalAmount" type="number" groupingUsed="true" />
                                            <td>${formattedTotalAmount} VND</td>
                                        </tr>
                                    </table>
                                </div>
                                <div>
                                    <table class="table table_style table-striped-columns">
                                        <tr>
                                            <td><label>Total Slot</label></td>
                                            <td>${requestScope.room.getRoomType().getRoomCapacity()}</td>
                                        </tr>
                                        <tr>
                                            <td><label>Used Slot</label></td>
                                            <td>${requestScope.room.getNumberOfStudent()}</td>
                                        </tr>
                                        <tr>
                                            <td><label>Free electricity</label></td>
                                            <td>${requestScope.room.getRoomType().getElectricityFree()}</td>
                                        </tr>
                                        <tr>
                                            <td><label>Free water</label></td>
                                            <td>${requestScope.room.getRoomType().getWaterFree()}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <h5 style="color: green" >Electricity/Water usage history</h5>
                        <div class="row">
                            <div class="col-6">
                                <div id="curve_chart_electric" ></div>
                            </div>
                            <div class="col-6">
                                <div id="curve_chart_water" ></div>
                            </div>
                        </div>
                        <h5 style="color: green">List of student</h5>
                        <div class="row mb-5">
                            <div class="col-10">
                                <div class="card border-1 scrollable-table">
                                    <table class="table table-striped-columns">
                                        <thead>
                                        <tr>
                                            <th scope="col" style="width: 4%">No</th>
                                            <th scope="col" style="width: 10%">Roll number</th>
                                            <th scope="col" style="width: 15%">Full name</th>
                                            <th scope="col" style="width: 6%">DOB</th>
                                            <th scope="col" style="width: 6%">Gender</th>
                                            <th scope="col" style="width: 15%">Address</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% int noId = 1;%>
                                        <c:forEach var="member" items="${requestScope.roomMemberList}">
                                            <tr>
                                                <td><%=noId++%>
                                                </td>
                                                <td>${member.getRollNumber()}</td>
                                                <td>${member.getFullName()}</td>
                                                <td>${member.getDob()}</td>
                                                <td>${member.getGender()}</td>
                                                <td>${member.getAddress()}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
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
