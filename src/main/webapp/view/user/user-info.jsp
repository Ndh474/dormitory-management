<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/21/2024
  Time: 8:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal information</title>
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
                    <h2 class="fw-bold">Personal information</h2>
                    <div style="display: flex">
                        <div class="add-action mb-2">
                            <a class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateUserModal">
                                <span>Update personal information</span></a>
                        </div>
                        <div style="margin-left: 10px" class="add-action mb-2">
                            <a class="btn btn-success" data-bs-toggle="modal" data-bs-target="#changePasswordModal">
                                <span>Change password</span></a>
                        </div>
                    </div>
                    <div style="justify-content: center" class="card border-1">
                        <div class="row">
                            <div class="card-body py-2 col-7">
                                <h5 class="ms-3">Basic information</h5>
                                <hr class="ms-3">
                                <table>
                                    <tr>
                                        <td class="table_l">Roll Number:</td>
                                        <td class="table_r"> ${rollNumber}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Full name:</td>
                                        <td class="table_r">${fullName}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Gender:</td>
                                        <td class="table_r"> ${gender}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Date of birth:</td>
                                        <td class="table_r">${dob}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">CCCD:</td>
                                        <td class="table_r">${cid}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Balance:</td>
                                        <%
                                            double amount = (Double) request.getAttribute("balance");
                                            NumberFormat formatter = NumberFormat.getInstance();
                                            String formattedAmount = formatter.format(amount);
                                        %>
                                        <td class="table_r"><%=formattedAmount%> VND</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="card-body py-2 col-5">
                                <h5>Contact Info</h5>
                                <hr>
                                <table>
                                    <tr>
                                        <td class="table_l">Phone number:</td>
                                        <td class="table_r">${phoneNumber}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Email:</td>
                                        <td class="table_r">${email}</td>
                                    </tr>
                                    <tr>
                                        <td class="table_l">Address:</td>
                                        <td class="table_r">${address}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="card border-1 mt-4">
                        <div class="card-body py-2" style="height: 490px">
                            <h5>Recent activity history</h5>
                            <hr>
                            <table class="table table-striped-columns">
                                <tr>
                                    <th style="width: 10%">No</th>
                                    <th style="width: 15%">Activity</th>
                                    <th style="width: 55%">Activity detail</th>
                                    <th style="width: 20%">Date</th>
                                </tr>
                                <% int noId = 1;%>
                                <c:forEach var="activityHistory" items="${requestScope.activityHistoryList}">
                                    <tr>
                                        <td><%=noId++%></td>
                                        <td>${activityHistory.getActivityName()}</td>
                                        <td>${activityHistory.getDetail()}</td>
                                        <td>${activityHistory.getDateCreated()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Update user information modal -->
<div id="updateUserModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/user/info" method="post" id="update-information-form">
                <div class="modal-header">
                    <h4 class="modal-title">Update personal information</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Phone number</label>
                        <input name="user_phonenumber" type="number" class="form-control" id="user_phonenumberInput"
                               required value="${requestScope.phoneNumber}">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input name="user_address" type="text" class="form-control" id="user_addressInput" required
                               value="${requestScope.address}">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Update">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Change password modal -->
<div id="changePasswordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/change-password" method="post" id="change-password-form">
                <div class="modal-header">
                    <h4 class="modal-title">Change password</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newPassword">New password</label>
                        <input name="newPassword" id="newPassword" type="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="reNewPassword">Confirm new password</label>
                        <input name="reNewPassword" id="reNewPassword" type="password" class="form-control" required>
                    </div>
                    <span style="color: red" id="checkMatchPassword"></span>
                </div>
                <div class="modal-footer">
                    <input type="reset" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Change">
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.getElementById("change-password-form").addEventListener("submit", function (event) {
        event.preventDefault();
        const password = document.getElementById("newPassword").value;
        const rePassword = document.getElementById("reNewPassword").value;
        if (password !== rePassword) {
            document.getElementById("checkMatchPassword").innerHTML = "Password does not match!";
        } else {
            Swal.fire({
                title: 'Confirm',
                text: 'Are you sure you want to change password?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.submit(); // submit the form if the user confirms
                }
            });
        }
    });

    const successStatus = "${sessionScope.successUpdateInformation}";
    if (successStatus === "success") {
        ${ sessionScope.remove("successUpdateInformation") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Update information successfully!",
        });
    }
</script>
</body>
</html>
