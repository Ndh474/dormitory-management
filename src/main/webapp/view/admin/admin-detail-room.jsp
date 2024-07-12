<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="services.RoomUsageService" %>
<%@ page import="services.impl.RoomUsageServiceImpl" %>
<%@ page import="entity.Chart" %>
<%@ page import="services.RoomService" %>
<%@ page import="services.impl.RoomServiceImpl" %>
<%@ page import="entity.Room" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/8/2024
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail room</title>
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
                    Room room =(Room) request.getAttribute("room");
                    RoomUsageService roomUsageService = new RoomUsageServiceImpl();
                    for (Chart c : roomUsageService.getUsagePerMonth(room.getRoomName())) {
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
                  for (Chart c : roomUsageService.getUsagePerMonth(room.getRoomName())) {
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
    <style>
        .select_style {
            margin-left: 10px;
        }

        .select_style select {
            height: 26px;
            background: white;
            border: 1px solid #198754;
            width: 200px;
        }

        .scrollable-table {
            max-height: 300px;
            overflow: auto;
        }

        a {
            cursor: pointer;
        }

        td {
            font-size: smaller;
        }

        .table_style td {
            height: 40px;
        }

        span {
            padding-right: 20px;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        input[type=number] {
            -moz-appearance: textfield;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class="fw-bold">Room manager</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <h5 style="color: green">Room information</h5>
                            <!--update room detail form-->
                            <form action="${pageContext.request.contextPath}/admin-detail-room" method="post"
                                  id="update-room-form">
                                <div class="d-flex justify-content-evenly">
                                    <div>
                                        <table class="table table_style table-striped-columns">
                                            <tr>
                                                <td><label for="room-name">Room Name</label></td>
                                                <td><input type="text" name="roomName" id="room-name"
                                                           value="${requestScope.room.getRoomName()}" readonly></td>
                                            </tr>
                                            <tr>
                                                <td><label for="dorm-name">Dorm Name</label></td>
                                                <td id="dorm-name">${requestScope.room.getDorm().getDormName()}</td>
                                            </tr>
                                            <tr>
                                                <td><label for="room-gender">Room Gender</label></td>
                                                <td id="room-gender">${requestScope.room.getRoomGender()}</td>
                                            </tr>
                                            <tr>
                                                <td><label for="status">Status</label></td>
                                                <td>
                                                    <select name="roomStatus" id="status">
                                                        <c:forEach var="status"
                                                                   items="${requestScope.dormRoomStatusList}">
                                                            <option value="${status.getStatusId()}" ${status.getStatusId()==requestScope.room.getRoomStatus().getStatusId()?"selected":""}>${status.getStatusName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="room-type">Room Type</label></td>
                                                <td>
                                                    <select name="roomType" id="room-type">
                                                        <c:forEach var="type" items="${requestScope.roomTypeList}">
                                                            <c:set var="amount" value="${type.getRoomPrice()}"/>

                                                            <fmt:formatNumber value="${amount}" var="formattedAmount"
                                                                              type="number" groupingUsed="true"/>

                                                            <option value="${type.getRoomTypeId()}"
                                                                ${type.getRoomTypeId() == requestScope.room.getRoomType().getRoomTypeId() ? "selected" : ""}>
                                                                    ${type.getRoomCapacity()} beds - ${formattedAmount}
                                                                VND
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
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
                                                <td><label for="number-electric-used"></label>Number electric used:</td>
                                                <td><input type="number" name="numberElectricUsed"
                                                           id="number-electric-used"
                                                           value="${requestScope.roomUsageCurrentMonth.getElectricityUsed()}" ${requestScope.roomUsageCurrentMonth.getRoomUsageId()==0?"readonly":""}>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="number-water-used">Number water used:</label></td>
                                                <td><input type="number" name="numberWaterUsed" id="number-water-used"
                                                           value="${requestScope.roomUsageCurrentMonth.getWaterUsed()}" ${requestScope.roomUsageCurrentMonth.getRoomUsageId()==0?"readonly":""}>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Total Amount:</td>
                                                <c:set var="totalAmount" value="${requestScope.totalAmount}"/>

                                                <fmt:formatNumber value="${totalAmount}" var="formattedTotalAmount"
                                                                  type="number" groupingUsed="true"/>
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
                                <div class="d-md-flex justify-content-md-between">
                                    <%
                                        String encodedRoomName = request.getParameter("roomName");
                                        String decodedRoomName = URLDecoder.decode(encodedRoomName, "UTF-8");
                                    %>
                                    <a href="/admin-room?dormName=<%= decodedRoomName.charAt(0) %>"
                                       class="btn btn-warning mt-2">
                                        <div class="d-flex justify-content-between">
                                            <i class="lni lni-reply"
                                               style="font-size: 25px; padding-right: 5px"></i>
                                            <div>Back to room manager</div>
                                        </div>
                                    </a><br>
                                    <c:if test="${sessionScope.account.getAccountStatus().getStatusName().equals('active')}">
                                        <button type="submit" class="btn btn-success mt-2">
                                            <div class="d-flex justify-content-between">
                                                <i class="lni lni-checkmark-circle"
                                                   style="font-size: 25px; padding-right: 5px"></i>
                                                <div>Update</div>
                                            </div>
                                        </button>
                                    </c:if>
                                </div>
                            </form>
                            <h5 style="color: green">List of student</h5>
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col" style="width: 4%">No</th>
                                        <th scope="col" style="width: 10%">Roll number</th>
                                        <th scope="col" style="width: 15%">Full name</th>
                                        <th scope="col" style="width: 6%">DOB</th>
                                        <th scope="col" style="width: 6%">Gender</th>
                                        <th scope="col" style="width: 6%">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% int noId = 1;%>
                                    <c:forEach var="member" items="${requestScope.roomMemberList}">
                                        <tr>
                                            <td><%=noId++%>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin-update-student?rollNumber=${member.getRollNumber()}">${member.getRollNumber()}</a>
                                            </td>
                                            <td>${member.getFullName()}</td>
                                            <td>${member.getDob()}</td>
                                            <td>${member.getGender()}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/remove-from-room?rollNumber=${member.getRollNumber()}"
                                                   id="remove-student-a">Remove
                                                    from room</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <h5 style="color: green" class="mt-4">Electricity/Water usage history</h5>
                            <div class="row">
                                <div class="col-6">
                                    <div id="curve_chart_electric" ></div>
                                </div>
                                <div class="col-6">
                                    <div id="curve_chart_water" ></div>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
    document.getElementById("update-room-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to update this room?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                this.submit(); // submit the form if the user confirms
            }
        });
    });

    const status = "${sessionScope.successUpdateRoom}"
    if (status === "success") {
        ${sessionScope.remove('successUpdateRoom')}
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Update Room successfully!",
            showConfirmButton: false,
            timer: 1500
        });
    }

    document.getElementById("remove-student-a").addEventListener("click", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        const url = this.getAttribute("href");
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to remove this student?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = url;
            }
        });
    });

    const remove_status = "${sessionScope.successRemove}"
    if (remove_status === "success") {
        ${sessionScope.remove('successRemove')}
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Remove successfully!",
            showConfirmButton: false,
            timer: 1500
        });
    }

    const errorInactive = '${sessionScope.errorInactive}';
    if (errorInactive === 'error') {
        ${sessionScope.remove('errorInactive')}
        Swal.fire({
            position: "center",
            icon: "error",
            title: "You cannot make a room inactive while there are still students in the room!",
            showConfirmButton: false,
            timer: 2000
        });
    }
</script>
</body>
</html>
