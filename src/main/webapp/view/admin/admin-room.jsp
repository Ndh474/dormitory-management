<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/1/2024
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room manager</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
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
            max-height: 515px;
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

        #add-room-form {
            display: none;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: lightcoral;
            border: 1px solid #000;
            text-align: center;
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
                            <div class="card border-1">
                                <div class="card-body py-0">
                                    <!--Choose type room form -->
                                    <form action="${pageContext.request.contextPath}/admin-room" method="get">
                                        <div class="d-flex justify-content-between mt-4">
                                            <div class="d-flex">
                                                <label for="dorm">Dorm</label>
                                                <div class="select_style">
                                                    <select id="dorm" name="dormName">
                                                        <c:forEach var="dorm" items="${requestScope.dormList}">
                                                            <option value="${dorm.getDormName()}" ${dorm.getDormName().equals(requestScope.dormName)?"selected":""}>${dorm.getDormName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="d-flex">
                                                <label for="type-of-room">Type of room</label>
                                                <div class="select_style">
                                                    <select id="type-of-room" name="roomType">
                                                        <option value="0">All</option>
                                                        <c:forEach var="type" items="${requestScope.roomTypeList}">
                                                            <c:set var="amount" value="${type.getRoomPrice()}"/>

                                                            <fmt:formatNumber value="${amount}" var="formattedAmount"
                                                                              type="number" groupingUsed="true"/>

                                                            <option value="${type.getRoomTypeId()}" ${type.getRoomTypeId()==requestScope.roomTypeId ? "selected":""}>${type.getRoomCapacity()}
                                                                beds - ${formattedAmount} VND
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="d-flex">
                                                <label for="number-of-floor">Floor: </label>
                                                <div class="select_style">
                                                    <select id="number-of-floor" name="floorNumber">
                                                        <option value="0">All</option>
                                                        <c:forEach begin="1" end="${requestScope.numberOfFloor}"
                                                                   var="i">
                                                            <option value="${i}" ${i==requestScope.floorNumber ? "selected":""}>${i}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div>
                                                <label for="room-name">Enter Room Name: </label>
                                                <input type="text" name="roomName" id="room-name"
                                                       placeholder="Room Name" value="${requestScope.roomName}">
                                            </div>
                                            <div>
                                                <button type="submit" class="btn btn-success">Filter</button>
                                                <a href="${pageContext.request.contextPath}/admin-room?dormName=${requestScope.dormName}"
                                                   class="btn btn-success">Reset</a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="fw-bold mt-2">List Of Room From Dorm ${requestScope.dormName}</h5>
                            <!-- Overview of room status-->
                            <div style="margin-top: 10px">
                                <span>Room status</span>
                                <span style="color: blue">Total: ${requestScope.totalRoom}</span>
                                <span style="color: red">Room full slot: ${requestScope.totalFullRoom}</span>
                                <span style="color: green">Room not full slot: ${requestScope.totalNotFullRoom}</span>
                            </div>
                        </div>
                        <div class="col-md-10">
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table">
                                    <table class="table table-striped-columns text-center">
                                        <tr>
                                            <th>No</th>
                                            <th>Room name</th>
                                            <th>Floor Number</th>
                                            <th>Room Gender</th>
                                            <th>Slot</th>
                                            <th>Room Status</th>
                                            <th>Detail</th>
                                        </tr>
                                        <% int noId = 1; %>
                                        <c:forEach var="room" items="${requestScope.roomList}">
                                            <tr>
                                                <td><%=noId++%>
                                                </td>
                                                <td>${room.getRoomName()}</td>
                                                <td>${room.getFloorNumber()}</td>
                                                <c:choose>
                                                    <c:when test="${room.getRoomGender().equals('male')}">
                                                        <td style="color: royalblue">${room.getRoomGender()} ♂️</td>
                                                    </c:when>
                                                    <c:when test="${room.getRoomGender().equals('female')}">
                                                        <td style="color: hotpink">${room.getRoomGender()} ♀️</td>
                                                    </c:when>
                                                    <c:when test="${room.getRoomGender().equals('not set')}">
                                                        <td style="color: black">${room.getRoomGender()}</td>
                                                    </c:when>
                                                </c:choose>
                                                <td ${room.getNumberOfStudent()==room.getRoomType().getRoomCapacity()?'style="color: red"':'style="color: blue"'}>${room.getNumberOfStudent()}/${room.getRoomType().getRoomCapacity()}</td>
                                                <c:choose>
                                                    <c:when test="${room.getRoomStatus().getStatusName().equals('active')}">
                                                        <td style="color: forestgreen">${room.getRoomStatus().getStatusName()}</td>
                                                    </c:when>
                                                    <c:when test="${room.getRoomStatus().getStatusName().equals('inactive')}">
                                                        <td style="color: red">${room.getRoomStatus().getStatusName()}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color: orange">${room.getRoomStatus().getStatusName()}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>
                                                    <a style="color: blue"
                                                       href="${pageContext.request.contextPath}/admin-detail-room?roomName=${room.getRoomName()}">Detail</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2 mt-2">
                            <c:if test="${sessionScope.account.getAccountStatus().getStatusName().equals('active')}">
                                <a href="${pageContext.request.contextPath}/admin-add-room?dormName=${requestScope.dormName}"
                                   class="btn btn-success">
                                    <div class="d-flex justify-content-between">
                                        <i class="lni lni-circle-plus"
                                           style="font-size: 25px; padding-right: 5px"></i>
                                        <div style="font-size: 15px; width: 170px">Add new room</div>
                                    </div>
                                </a><br>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/admin-dorm" class="btn btn-warning mt-2">
                                <div class="d-flex justify-content-between">
                                    <i class="lni lni-reply"
                                       style="font-size: 25px; padding-right: 5px"></i>
                                    <div style="font-size: 15px; width: 170px">Back to dorm manager</div>
                                </div>
                            </a><br>
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
<script>
    const successStatus = "${sessionScope.successAddRoom}";
    if (successStatus === "success") {
        ${sessionScope.remove("successAddRoom")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Add Room successfully!",
        });
    }
</script>
</body>
</html>
