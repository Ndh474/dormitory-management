<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        /* Định dạng chung cho form */
        .container_new {
            width: 1000px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        /* Tiêu đề form */
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        /* Nhãn cho các trường */
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        /* Trường nhập */
        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        /* Menu chọn */
        .form-control[type="select"] {
            appearance: none; /* Ẩn mũi tên mặc định */
            padding: 10px 20px;
            background-image: url('data:image/svg+xml;base64,U3VGRT0+'); /* Thêm icon mũi tên */
            background-repeat: no-repeat;
            background-position: right center;
            background-size: 10px;
        }

        /* Nút submit */
        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        /* Nút submit khi hover */
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
<body>
<div class="wrapper">
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container_new mt-4">
                <h2 style="color: green">Add Room</h2>
                <form action="${requestScope.request.contextPath}/admin-add-room" method="post" id="add-room-form">
                    <div class="form-group">
                        <label for="dormName">Dorm Name:</label>
                        <input type="text" class="form-control" id="dormName" value="${requestScope.dormName}" readonly
                               name="dormName">
                    </div>
                    <div class="form-group">
                        <label for="roomName">Room number:</label>
                        <input type="text" class="form-control" id="roomName" name="roomNumber"
                               placeholder="Enter Room Number" value="${requestScope.roomNumber}" maxlength="2">
                    </div>
                    <div class="form-group">
                        <label for="floor">Floor Number:</label>
                        <select class="form-control" id="floor" name="floorNumber">
                            <c:forEach begin="1" end="${requestScope.numberOfFloor}" var="i">
                                <option value="${i}" ${i==requestScope.floorNumber?"selected":""}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="roomStatus">Room Status:</label>
                        <select name="roomStatus" id="roomStatus" class="form-control" name="roomStatus">
                            <c:forEach var="status" items="${requestScope.roomStatusList}">
                                <option value="${status.getStatusId()}" ${status.getStatusId()==requestScope.roomStatus?"selected":""}>${status.getStatusName()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="roomType">Room Type:</label>
                        <select name="roomType" id="roomType" class="form-control" name="roomType">
                            <c:forEach var="type" items="${requestScope.roomTypeList}">
                                <option value="${type.getRoomTypeId()}" ${type.getRoomTypeId()==requestScope.roomType?"selected":""}>${type.getRoomCapacity()}
                                    beds - ${type.getRoomPrice()} VND
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <span style="color: red">${requestScope.roomMessage}</span>
                    </div>

                    <button type="submit" class="btn btn-success mt-3">Add</button>
                </form>
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
    document.getElementById("add-room-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to add this Room?',
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
</script>
</body>
</html>


