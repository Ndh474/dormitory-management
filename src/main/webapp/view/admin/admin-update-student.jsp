<%@ page import="entity.Information" %>
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
    <style>
        input {
            width: 300px
        }

        .button-containers {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .button {
            background-color: #1eaa1e;
            width: 150px;
            height: 30px;
            border-radius: 5px;
            outline: none;
            border: none;
            text-align: center;
            margin: 20px auto;
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
                    <h2 class="fw-bold">Update information</h2>

                    <div class="row">
                        <form action="${pageContext.request.contextPath}/admin-update-student" method="post" id="update-student-form">
                            <div style="justify-content: center" class="card border-1">
                                <div class="card-body py-2">
                                    <h5>Account</h5>
                                    <hr>
                                    <table>
                                        <tr>
                                            <td class="table_l"><label for="email">Email: </label></td>
                                            <td class="table_r"
                                                id="email">${requestScope.information.getAccount().getEmail()}</td>
                                        </tr>

                                        <tr>
                                            <td class="table_l"><label for="status">Status: </label></td>
                                            <td class="table_r">
                                                <select id="status" name="accountStatus">
                                                    <c:forEach var="status" items="${requestScope.accountStatusList}">
                                                        <option value="${status.getStatusID()}" ${status.getStatusID()==requestScope.information.getAccount().getAccountStatus().getStatusID()?"selected":""}>${status.getStatusName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="role">Role:</label></td>
                                            <td class="table_r" id="role">${requestScope.information.getAccount().getRole().getRoleName()}</td>
                                        </tr>

                                    </table>
                                </div>
                            </div>
                            <div style="justify-content: center; margin-top: 10px" class="card border-1">
                                <div class="card-body py-2">
                                    <h5>Information</h5>
                                    <hr>
                                    <input type="text" name="rollNumber"
                                           value="${requestScope.information.getRollNumber()}" hidden>
                                    <div class="d-flex">
                                    <table>
                                        <tr>
                                            <td class="table_l"><label for="room">Room:</label></td>
                                            <td class="table_r" id="room"><a
                                                    href="${pageContext.request.contextPath}/admin-detail-room?roomName=${requestScope.information.getRoomName()}">${requestScope.information.getRoomName()}</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="roll-number">Roll Number:</label></td>
                                            <td class="table_r"
                                                id="roll-number">${requestScope.information.getRollNumber()}</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="full-name">Full Name:</label></td>
                                            <td class="table_r"
                                                id="full-name">${requestScope.information.getFullName()}</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="gender">Gender:</label></td>
                                            <td class="table_r" id="gender">${requestScope.information.getGender()}</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="dob">Date of birth:</label></td>
                                            <td class="table_r"><input type="date" id="dob" name="dob"
                                                                       value="${requestScope.information.getDob()}">
                                            </td>
                                        </tr>
                                    </table>
                                        <table style="margin-left: 5rem">
                                        <tr>
                                            <td class="table_l"><label for="citizen-identification">Citizen
                                                Identification:</label></td>
                                            <td class="table_r"><input type="text" id="citizen-identification"
                                                                       name="citizenIdentification"
                                                                       value="${requestScope.information.getCitizenIdentification()}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="phone-number">Phone Number:</label></td>
                                            <td class="table_r"><input type="text" id="phone-number" name="phoneNumber"
                                                                       value="${requestScope.information.getPhoneNumber()}">
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="table_l"><label for="address">Address:</label></td>
                                            <td class="table_r"><input type="text" id="address" name="address"
                                                                       value="${requestScope.information.getAddress()}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="balance">Balance:</label></td>
                                            <%
                                                double amount = (Double) ((Information) request.getAttribute("information")).getBalance();
                                                NumberFormat formatter = NumberFormat.getInstance();
                                                String formattedAmount = formatter.format(amount);
                                            %>
                                            <td class="table_r"><input type="text" id="balance" name="balance"
                                                                       value="<%=formattedAmount%>">
                                            </td>
                                        </tr>
                                    </table>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-evenly">
                                <a href="${pageContext.request.contextPath}/admin/students"
                                   class="btn btn-warning mt-2">
                                    <div class="d-flex justify-content-between">
                                        <i class="lni lni-reply"
                                           style="font-size: 25px; padding-right: 5px"></i>
                                        <div>Back to list</div>
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

                        <div style="justify-content: center; margin-top: 10px" class="card border-1">
                            <div class="card-body py-2">
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
                        <div style="justify-content: center; margin-top: 10px" class="card border-1">
                            <div class="card-body py-2">
                                <h5>Payment history</h5><hr>
                                <table class="table table-striped-columns">
                                    <tr>
                                        <th style="width: 10%">No</th>
                                        <th style="width: 50%">Bill Name</th>
                                        <th style="width: 10%">Total Amount</th>
                                        <th style="width: 10%">Status</th>
                                        <th style="width: 20%">Date</th>
                                    </tr>
                                    <% int noIdPayment = 1;%>
                                    <c:forEach var="bill" items="${requestScope.billList}">
                                        <tr>
                                            <td><%=noIdPayment++%></td>
                                            <td>${bill.getBillName()}</td>
                                            <td>${bill.getTotalAmount()}</td>
                                            <td>${bill.getBillStatus().getStatusName()}</td>
                                            <td>${bill.getDateCreated()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
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
<script type="text/javascript" src="../../js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.getElementById("update-student-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to update this student?',
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

    const successStatus = "${sessionScope.successUpdate}";
    if (successStatus === "success") {
        ${sessionScope.remove("successUpdate")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Update student successfully!",
            footer: '<a href="${pageContext.request.contextPath}/admin/students" style="color: blue; font-weight: bold; text-decoration: underline;">Go back to student list</a>'
        });

    }
</script>
</body>
</html>
