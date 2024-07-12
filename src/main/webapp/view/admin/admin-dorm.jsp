<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dorms</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        .select-field {
            margin-right: 5px;
            font-size: 15px;
            border-color: green;
        }

        .search-field {
            width: 30rem;
            height: 31px;
            padding-left: 10px;
            font-size: 15px;
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
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <div class="row">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h2 class=" fw-bold">Dorm manager</h2>
                            </div>
                        </div>
                        <div class="col-10">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col">No</th>
                                        <th scope="col">Dorm name</th>
                                        <th scope="col">Number of floor</th>
                                        <th scope="col">Number of room</th>
                                        <th scope="col">Available</th>
                                        <th scope="col">Status</th>
                                        <th>Update</th>
                                        <th>View</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% int noId = 1;%>
                                    <c:forEach var="d" items="${requestScope.dormList}">
                                        <tr>
                                            <td><%=noId++%>
                                            </td>
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
                                                <c:choose>
                                                    <c:when test="${sessionScope.account.getAccountStatus().getStatusName().equals('active')}">
                                                        <a data-bs-toggle="modal" data-bs-target="#updateDormModal"
                                                           onclick="populateForm('${d.dormId}','${d.dormName}', '${d.numberOfFloor}', '${d.status.statusId}')"
                                                           style="color: blue">Update</a>
                                                    </c:when>
                                                    <c:otherwise>❌</c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin-room?dormName=${d.dormName}">View</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <span style="color: red">${requestScope.updateDormMessage}</span>
                        </div>
                        <c:if test="${sessionScope.account.getAccountStatus().getStatusName().equals('active')}">
                            <div class="col-2">
                                <a href="${pageContext.request.contextPath}/admin-add-dorm" class="btn btn-success">
                                    <div class="d-flex justify-content-between">
                                        <i class="lni lni-circle-plus"
                                           style="font-size: 25px; padding-right: 5px"></i>
                                        <div>Add Dormitory</div>
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


<!-- update status dorm -->
<div id="updateDormModal" class="modal fade">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">Update Dorm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!--change dorm status form-->
                <form action="${pageContext.request.contextPath}/admin-dorm-update" method="post" id="update-dorm-form">
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td><input type="hidden" id="dormId" name="dormId"></td>
                            </tr>
                            <tr>
                                <td>Dorm name:</td>
                                <td><input type="text" id="dormName" name="dormName"></td>
                            </tr>
                            <tr>
                                <td>Number of floors:</td>
                                <td><input type="text" id="numberOfFloor" name="numberOfFloor" readonly></td>
                            </tr>
                            <tr>
                                <td>Status:</td>
                                <td>
                                    <select style="width: 50%; margin-left: 10px" class="select-field" name="status" id="statusId">
                                        <c:forEach var="s" items="${requestScope.statuses}">
                                            <option value="${s.statusId}">${s.statusName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Save changes">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script>
    function populateForm(dormId, dormName, numberOfFloor, statusId) {
        document.getElementById("dormId").value = dormId;
        document.getElementById("dormName").value = dormName;
        document.getElementById("numberOfFloor").value = numberOfFloor;
        document.getElementById("statusId").value = statusId;

    }
</script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.getElementById("update-dorm-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to update this DORM?',
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

    const successStatus = "${sessionScope.successUpdateDorm}";
    if (successStatus === "success") {
        ${sessionScope.remove("successUpdateDorm")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Update DORM successfully!",
        });
    }

    const failedStatus = "${sessionScope.failedUpdateDorm}";
    if (failedStatus === "hasStudent") {
        ${sessionScope.remove("failedUpdateDorm")}
        Swal.fire({
            icon: "error",
            title: "Failed",
            text: "Update DORM failed! There are students in this dorm!",
        });
    } else if (failedStatus === "existDormName") {
        ${sessionScope.remove("failedUpdateDorm")}
        Swal.fire({
            icon: "error",
            title: "Failed",
            text: "Dorm name already exists! Please try again!",
        });
    }
</script>
</body>
</html>