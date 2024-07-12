<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/8/2024
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Dorm</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .select_style select {
            height: 26px;
            background: white;
            border: 1px solid #198754;
            width: 200px;
        }

        .scrollable-table {
            max-height: 550px;
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
                    <h2 class="fw-bold">Detail room ${requestScope.room.roomName}</h2>
                    <div class="row">
                        <div class="col-8 mt-4">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col" style="width: 4%">No</th>
                                        <th scope="col" style="width: 15%">Roll number</th>
                                        <th scope="col" style="width: 25%">Address</th>
                                        <th scope="col" style="width: 6%">Gender</th>
                                    </tr>
                                    </thead>
                                    <%int id = 1;%>
                                    <tbody>
                                    <c:forEach var="lsDetailRoom" items="${requestScope.lsDetailRoom}">
                                        <tr>
                                            <td><%=id++%>
                                            </td>
                                            <td>${lsDetailRoom.getRollNumber()}</td>
                                            <td>${lsDetailRoom.getAddress()}</td>
                                            <td>${lsDetailRoom.getGender()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <p style="color: red; cursor: pointer; margin-top: 5px" onclick="history.back()">Back to
                                list</p>
                        </div>
                        <c:if test="${requestScope.information.getRoomName() == null && (requestScope.room.getNumberOfStudent()<requestScope.room.getRoomType().getRoomCapacity())}">
                            <div class="col-4 mt-4">
                                <a data-bs-toggle="modal" data-bs-target="#confirmRoomModal" class="btn btn-success"
                                   onclick="selectRoom('${requestScope.room.getRoomType().getRoomCapacity()}',
                                           '${requestScope.room.getRoomType().getRoomPrice()}','${requestScope.room.getDorm().getDormName()}',
                                           '${requestScope.room.getFloorNumber()}','${requestScope.room.getRoomName()}',
                                           '${requestScope.information.getBalance()}')">
                                    <div class="d-flex justify-content-between">
                                        <i class="lni lni-checkmark-circle"
                                           style="font-size: 25px; padding-right: 5px"></i>
                                        <div>Select Room</div>
                                    </div>
                                </a>
                            </div>
                        </c:if>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
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
        event.preventDefault();
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
            footer: '<a href="${pageContext.request.contextPath}/user-room-detail?roomName=${requestScope.information.getRoomName()}" style="color: blue; font-weight: bold; text-decoration: underline;">Go to see this room</a>'
        });
    }
</script>
</body>
</html>
