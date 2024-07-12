<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        a {
            cursor: pointer;
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
                                <h2 class=" fw-bold">Manager Account</h2>
                            </div>
                            <!-- search student by name form -->
                        </div>
                        <p>List Account:</p>
                        <div class=" col-8">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr style=" text-align: center;">
                                        <th scope="col" style="width: 4%;">No</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Role</th>
                                        <th scope="col" colspan="3">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% int noId = 1;%>
                                    <c:forEach var="account" items="${requestScope.accountList}">
                                        <tr>
                                            <td><%=noId++%>
                                            </td>
                                            <td>${account.getEmail()}</td>
                                            <c:choose>
                                                <c:when test="${account.getAccountStatus().getStatusName().equals('active')}">
                                                    <td style="color: green">${account.getAccountStatus().getStatusName()}</td>
                                                </c:when>
                                                <c:when test="${account.getAccountStatus().getStatusName().equals('inactive')}">
                                                    <td style="color: orange">${account.getAccountStatus().getStatusName()}</td>
                                                </c:when>
                                                <c:when test="${account.getAccountStatus().getStatusName().equals('deleted')}">
                                                    <td style="color: red">${account.getAccountStatus().getStatusName()}</td>
                                                </c:when>
                                            </c:choose>
                                            <td>${account.getRole().getRoleName()}</td>
                                            <td>
                                                <c:if test="${account.getAccountID()!=1}">
                                                    <a data-bs-toggle="modal" data-bs-target="#editAccountModal"
                                                       onclick="editAccount(`${account.getEmail()}`,`${account.getAccountStatus().getStatusID()}`)">
                                                        <button class="btn-field">
                                                            <div>Edit</div>
                                                        </button>
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <a data-bs-toggle="modal" data-bs-target="#addAdminAccountModal" class="btn btn-success">
                                <div class="d-flex justify-content-between">
                                    <i class="lni lni-circle-plus"
                                       style="font-size: 25px; padding-right: 5px"></i>
                                    <div>Add Account</div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<div id="addAdminAccountModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">Add Account</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/add-admin-account" method="post" id="add-account-form">
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td><label for="new-email" style="font-weight: bold">Email</label></td>
                                <td><input type="text" id="new-email" name="email" style="width: 20rem" required></td>
                            </tr>
                            <tr>
                                <td><label for="new-password" style="font-weight: bold">Password</label></td>
                                <td><input type="text" id="new-password" name="password" style="width: 20rem" required>
                                </td>
                            </tr>
                            <tr>
                                <td><label for="new-role" style="font-weight: bold">Role</label></td>
                                <td id="new-role"><span>Admin</span></td>
                            </tr>
                            <tr>
                                <td><label for="new-status" style="font-weight: bold">Status</label></td>
                                <td>
                                    <select style="width: 10rem" name="status" id="new-status">
                                        <c:forEach var="accountStatus" items="${requestScope.accountStatusList}">
                                            <option value="${accountStatus.getStatusID()}">${accountStatus.getStatusName()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div class="d-flex justify-content-end mt-2">
                            <button type="submit" class="btn btn-success">Add</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="editAccountModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">Edit Account</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/edit-admin-account" method="post"
                      id="edit-account-form">
                    <div class="modal-body">
                        <table style="font-size: 20px">
                            <tr>
                                <td><label for="email">Email</label></td>
                                <td><input type="text" id="email" name="email" readonly></td>
                            </tr>
                            <tr>
                                <td><label for="role">Role</label></td>
                                <td id="role"><span>Admin</span></td>
                            </tr>
                            <tr>
                                <td><label for="status">Status</label></td>
                                <td>
                                    <select name="status" id="status">
                                        <c:forEach var="accountStatus" items="${requestScope.accountStatusList}">
                                            <option value="${accountStatus.getStatusID()}">${accountStatus.getStatusName()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div class="d-flex justify-content-end mt-2">
                            <button type="submit" class="btn btn-success">Update</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hamburger.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function editAccount(email, status) {
        document.getElementById("email").value = email;
        document.getElementById("status").value = status;
    }

    document.getElementById("edit-account-form").addEventListener("submit", function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to update this Account?',
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

    const editAccountStatus = `${sessionScope.editAccountStatus}`;
    if (editAccountStatus === 'success') {
        ${sessionScope.remove("editAccountStatus")}
        Swal.fire({
            title: 'Success',
            text: 'Account status updated successfully',
            icon: 'success',
            confirmButtonText: 'OK'
        });
    } else if (editAccountStatus === 'fail') {
        ${sessionScope.remove("editAccountStatus")}
        Swal.fire({
            title: 'Error',
            text: 'Nothing changed!',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    }

    document.getElementById("add-account-form").addEventListener("submit", function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to add this Account?',
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

    const addAccountStatus = `${sessionScope.addAccountStatus}`;
    if (addAccountStatus === 'success') {
        ${sessionScope.remove("addAccountStatus")}
        Swal.fire({
            title: 'Success',
            text: 'Account added successfully',
            icon: 'success',
            confirmButtonText: 'OK'
        });
    } else if (addAccountStatus === 'fail') {
        ${sessionScope.remove("addAccountStatus")}
        Swal.fire({
            title: 'Error',
            text: 'Email already exists!',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    }
</script>
</body>
</html>
