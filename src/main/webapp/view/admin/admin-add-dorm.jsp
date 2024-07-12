<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

        .input-label {
            min-width: 200px;
            height: 20px;
            margin: 20px 0;
            text-align: right;
        }

        .input-field {
            width: 500px;
            margin-left: 80px;
        }

        .input-field-label {
            width: 100px;

        }

        .form-containers {
            max-width: 500px;
            padding: 10px 20px 20px 30px;
            margin-top: 250px;
            border: 1px #212529 solid;
            box-shadow: 0 10px 26px rgba(0, 0, 0, 0.3);
        }

        .select-field-containers {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button-add-room {
            background-color: #1eaa1e;
            width: 150px;
            height: 30px;
            border-radius: 5px;
            outline: none;
            border: none;
            text-align: center;
            margin: 20px auto;

        }

        .button-containers {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
        }

        .input-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .select-field {
            width: 100px;
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
                    <div class="row">
                        <div class="form-containers">
                            <form action="${pageContext.request.contextPath}/admin-add-dorm" method="post"
                                  id="add-dorm-form">
                                <div class="modal-header">
                                    <h4 style="color: green">Add new dorm</h4>
                                    <a type="button" class="btn-close" href="${pageContext.request.contextPath}/admin-dorm"></a>
                                </div>
                                <div class="modal-body">
                                    <table style="font-size: large">
                                        <tr class="input-item">
                                            <td class="table_l input-label"><label for="dorm-name">Dorm Name:</label>
                                            </td>
                                            <td><input class="input-field" type="text" name="dormName" id="dorm-name"
                                                       value="${requestScope.dormName}" maxlength="1"
                                                       pattern="[A-Za-z]*" required>
                                            </td>
                                        </tr>
                                        <tr class="input-item">
                                            <td class="table_l input-label"><label for="number-of-floor">Number of
                                                Floor:</label></td>
                                            <td>
                                                <div class="input-field">
                                                    <select class="input-field-label" name="numberOfFloor"
                                                            id="number-of-floor">
                                                        <c:forEach var="i" begin="1" end="5">
                                                            <option value="${i}" ${i==requestScope.numberOfFloor?"selected":""}>${i}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </td>
                                        </tr>
                                        <tr class="input-item">
                                            <td class="input-label"><label for="dorm-status">Dorm status:</label></td>
                                            <td>
                                                <select name="dormStatus" class="input-field" id="dorm-status">
                                                    <c:forEach var="status" items="${requestScope.dormRoomStatus}">
                                                        <option value="${status.getStatusId()}" ${status.getStatusId()==requestScope.dormStatus}>${status.getStatusName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <td class="table_l input-label"><span
                                                style="color: red">${requestScope.dormMessage}</span>
                                        </td>
                                    </table>
                                </div>
                                <div class="button-containers">
                                    <input type="submit" class="btn btn-success"
                                           style="box-shadow: 7px 7px 8px #888888;" value="Add ">
                                </div>
                            </form>
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
    document.getElementById("add-dorm-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to add this DORM?',
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

    const successStatus = "${sessionScope.successAddDorm}";
    if (successStatus === "success") {
        ${sessionScope.remove("successAddDorm")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Add DORM successfully!",
            footer: '<a href="${pageContext.request.contextPath}/admin-dorm" style="color: blue; font-weight: bold; text-decoration: underline;">Go back to DORM list</a>'
        });

    }
</script>

</body>
</html>


