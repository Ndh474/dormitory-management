<%@ page import="services.InformationService" %>
<%@ page import="services.impl.InformationServiceImpl" %>
<%@ page import="entity.Account" %>
<%@ page import="entity.Information" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/1/2024
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Dorm</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .select_style select {
            height: 26px;
            background: white;
            border: 1px solid #198754;
            width: 200px;
        }

        .scrollable-table {
            max-height: 490px;
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
                    <h2 class="fw-bold">View by Dorm ${requestScope.dormName}</h2>
                    <div class="row">
                        <div class="card border-1">
                            <div class="card-body py-0">
                                <!--Choose type room form -->
                                <form action="${pageContext.request.contextPath}/user-dorm-detail" method="get">
                                    <div class="d-flex justify-content-between mt-4">
                                        <div class="d-flex">
                                            <label for="dorm">Dorm </label>
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
                                                <select style="margin-left: 5px" id="type-of-room" name="roomType">
                                                    <option value="0">All</option>
                                                    <c:forEach var="type" items="${requestScope.roomTypeList}">
                                                        <c:set var="amount" value="${type.getRoomPrice()}" />

                                                        <fmt:formatNumber value="${amount}" var="formattedAmount" type="number" groupingUsed="true" />

                                                        <option value="${type.getRoomTypeId()}" ${type.getRoomTypeId()==requestScope.roomTypeId ? "selected":""}>${type.getRoomCapacity()}
                                                            beds - ${formattedAmount} VND
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="d-flex">
                                            <label for="number-of-floor">Floor </label>
                                            <div class="select_style">
                                                <select style="margin-left: 5px" id="number-of-floor"
                                                        name="floorNumber">
                                                    <option value="0">All</option>
                                                    <c:forEach begin="1" end="${requestScope.numberOfFloor}"
                                                               var="i">
                                                        <option value="${i}" ${i==requestScope.floorNumber ? "selected":""}>${i}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div>
                                            <label for="room-name">Enter Room Name </label>
                                            <input style="margin-left: 5px" type="text" name="roomName" id="room-name"
                                                   placeholder="Room Name" value="${requestScope.roomName}">
                                        </div>
                                        <div>
                                            <button type="submit" class="btn btn-success">Filter</button>
                                            <a href="${pageContext.request.contextPath}/user-dorm-detail?dormName=${requestScope.dormName}"
                                               class="btn btn-success">Reset</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="fw-bold mt-2">List of room</h5>
                            <!-- Overview of room status-->
                        </div>
                        <div class="col-md-8">
                            <!-- thay tuùy vaào giới tinh student -->
                            <c:choose>
                                <c:when test="${sessionScope.information.getGender().equals('male')}">
                                    <p style="color: red; font-size: small; margin: 0">*Male only</p>
                                </c:when>
                                <c:when test="${sessionScope.information.getGender().equals('female')}">
                                    <p style="color: red; font-size: small; margin: 0">*Female only</p>
                                </c:when>
                            </c:choose>
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table" style="height: 440px">
                                    <table class="table table-striped-columns text-center">
                                        <tr>
                                            <th>No</th>
                                            <th>Room name</th>
                                            <th>Floor Number</th>
                                            <th>Room Gender</th>
                                            <th>Slot</th>
                                            <th>Room Status</th>
                                            <th>View</th>
                                            <th>Select</th>
                                        </tr>
                                        <%
                                            int noId = 1;
                                        %>
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
                                                    <c:choose>
                                                        <c:when test="${!requestScope.information.getRoomName().equals(room.getRoomName())}">
                                                            <a style="color: blue"
                                                               href="${pageContext.request.contextPath}/user-room-detail?roomName=${room.getRoomName()}">Detail</a>
                                                        </c:when>
                                                        <c:when test="${requestScope.information.getRoomName().equals(room.getRoomName())}">
                                                            <a style="color: blue"
                                                               href="${pageContext.request.contextPath}/user/myroom">Detail</a>
                                                        </c:when>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${requestScope.bookRoom == false}">
                                                            ❌
                                                        </c:when>
                                                        <c:when test="${room.getNumberOfStudent()==room.getRoomType().getRoomCapacity()}">
                                                            FULL
                                                        </c:when>
                                                        <c:when test="${requestScope.information.getRoomName() == null && room.getRoomStatus().getStatusName().equals('active') && !requestScope.information.getAccount().getAccountStatus().getStatusName().equals('inactive')}">
                                                            <a style="color: blue" data-bs-toggle="modal"
                                                               data-bs-target="#confirmRoomModal"
                                                               onclick="selectRoom('${room.getRoomType().getRoomCapacity()}',
                                                                       '${room.getRoomType().getRoomPrice()}','${room.getDorm().getDormName()}',
                                                                       '${room.getFloorNumber()}','${room.getRoomName()}',
                                                                       '${requestScope.information.getBalance()}')">Select</a>
                                                        </c:when>
                                                        <c:when test="${requestScope.information.getRoomName().equals(room.getRoomName())}">
                                                            (YOUR ROOM)
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <h5 class="fw-bold mt-2">Summary</h5>
                            <div class="card border-1">
                                <div class="card-body py-1">
                                    <table class="table table-striped-columns text-center">
                                        <tr>
                                            <th>Floor</th>
                                            <th>Total</th>
                                            <th>Room full slot</th>
                                            <th>Room not full slot</th>
                                        </tr>
                                        <tr>
                                            <td>${requestScope.numberOfFloor}</td>
                                            <td style="color: blue">${requestScope.totalRoom}</td>
                                            <td style="color: red">${requestScope.totalFullRoom}</td>
                                            <td style="color: green">${requestScope.totalNotFullRoom}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <a style="color: red; cursor: pointer"
                               href="${pageContext.request.contextPath}/user/view-dorm">Back to list</a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!--confirm and payment room -->
<div id="confirmRoomModal" class="modal fade">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-group">
                    <div style="border-bottom: 0" class="modal-header">
                        <h5 style="color: green"> Confirm and payment</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                                onclick="closeModal()"></button>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-8">
                            <!-- insert confirm and payment form here -->
                            <form action="${pageContext.request.contextPath}/user-dorm-detail" id="payment-form"
                                  method="post">
                                <div class="card border-1 scrollable-table">
                                    <table class="table table-striped">
                                        <tr>
                                            <td class="table_l"><label for="select-type">Room Type: </label></td>
                                            <td class="table_r"><input type="text" name="roomType" id="select-type"
                                                                       readonly></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="select-dorm">Dorm: </label></td>
                                            <td class="table_r"><input type="text" name="dormName" id="select-dorm"
                                                                       readonly></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="select-floor">Floor: </label></td>
                                            <td class="table_r"><input type="text" name="floorNumber" id="select-floor"
                                                                       readonly></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="select-room">Room Name: </label></td>
                                            <td class="table_r"><input type="text" name="roomName" id="select-room"
                                                                       readonly>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-success mt-4 me-md-2" id="confirm-button">
                                        <div class="d-flex justify-content-between">
                                            <i class="lni lni-checkmark-circle"
                                               style="font-size: 25px; padding-right: 5px"></i>
                                            <div>Confirm</div>
                                        </div>
                                    </button>
                                </div>
                                <p style="color: red; font-size: 12px; text-align: end">*Please check carefully before
                                    deciding to confirm</p>
                            </form>
                        </div>
                        <div class="col-md-4">
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table">
                                    <h4 style="color: green; margin-top: 10px">Your balance</h4>
                                    <hr>
                                    <h6>Your account Balance</h6>
                                    <p id="your-balance"></p>
                                    <h6>The total amount payable</h6>
                                    <p id="paid-amount"></p>
                                    <h6>Your Balance after booking</h6>
                                    <p id="remaining-balance"></p>
                                </div>
                            </div>
                            <span id="message"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function selectRoom(roomCapacity, roomPrice, dorm, floor, roomName, balance) {
        const formattedRoomPrice = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(roomPrice);

        const formattedBalance = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(balance);

        const formattedBalanceLeft = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(parseFloat(balance) - parseFloat(roomPrice));

        document.getElementById("select-type").value = roomCapacity + " beds - " + formattedRoomPrice;
        document.getElementById("select-dorm").value = dorm;
        document.getElementById("select-floor").value = floor;
        document.getElementById("select-room").value = roomName;
        document.getElementById("your-balance").innerHTML = formattedBalance;
        document.getElementById("paid-amount").innerHTML = formattedRoomPrice;
        document.getElementById("remaining-balance").innerHTML = formattedBalanceLeft;
        if (parseFloat(balance) < parseFloat(roomPrice)) {
            document.getElementById("remaining-balance").style.color = "red";
            document.getElementById("message").style.color = "red";
            document.getElementById("message").innerHTML = "Your balance is not enough to pay for this room";
            document.getElementById("confirm-button").style.display = "none";
        } else {
            document.getElementById("remaining-balance").style.color = "blue";
            document.getElementById("message").style.color = "blue";
            document.getElementById("message").innerHTML = "You can book this room!";
            document.getElementById("confirm-button").style.display = "block";
        }
    }

    function closeModal() {
        document.getElementById("payment-form").reset();
    }

    document.getElementById("payment-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to join this room?',
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

    const successStatus = "${sessionScope.successPayment}";
    if (successStatus === "success") {
        ${sessionScope.remove("successPayment")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "You have successfully booked a room",
            footer: '<a href="${pageContext.request.contextPath}/user/myroom" style="color: blue; font-weight: bold; text-decoration: underline;">Go to see this room</a>'
        });

    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
</body>
</html>
