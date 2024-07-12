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
                    <h2 class="fw-bold">Add information</h2>

                    <div class="row">
                        <form action="${pageContext.request.contextPath}/admin-add-information" method="post" id="add-student-form">
                            <div style="justify-content: center" class="card border-1">
                                <div class="card-body py-2">
                                    <h5>Account</h5>
                                    <hr>
                                    <table>
                                        <tr>
                                            <td class="table_l"><label for="email">Email: </label></td>
                                            <td class="table_r"><input type="text" name="email" id="email"
                                                                       value="${requestScope.email}" required></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="password">Password:</label></td>
                                            <td class="table_r"><input type="text" name="password" id="password"
                                                                       required></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="status">Status: </label></td>
                                            <td class="table_r">
                                                <select id="status" name="accountStatus">
                                                    <c:forEach var="status" items="${requestScope.accountStatusList}">
                                                        <option value="${status.getStatusID()}" ${status.getStatusID()==requestScope.status?"selected":""}>${status.getStatusName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="role">Role:</label></td>
                                            <td class="table_r" id="role">Student</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td style="color: red">${requestScope.accountMessage}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div style="justify-content: center; margin-top: 10px" class="card border-1">
                                <div class="card-body py-2">
                                    <h5>Information</h5>
                                    <hr>
                                    <table>
                                        <tr>
                                            <td class="table_l"><label for="roll-number">Roll Number:</label></td>
                                            <td class="table_r"><input type="text" name="rollNumber" id="roll-number"
                                                                       value="${requestScope.rollNumber}" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="full-name">Full Name:</label></td>
                                            <td class="table_r"><input type="text" name="fullName" id="full-name"
                                                                       value="${requestScope.fullName}" required></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="gender">Gender:</label></td>
                                            <td class="table_r">
                                                <select name="gender" id="gender">
                                                    <option value="male" ${requestScope.gender.equals("male")?"selected":""}>
                                                        Male
                                                    </option>
                                                    <option value="female" ${requestScope.gender.equals("female")?"selected":""}>
                                                        Female
                                                    </option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="dob">Date of birth:</label></td>
                                            <td class="table_r"><input type="date" id="dob" name="dob"
                                                                       value="${requestScope.dob}" required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="citizen-identification">Citizen
                                                Identification:</label></td>
                                            <td class="table_r"><input type="text" id="citizen-identification"
                                                                       name="citizenIdentification"
                                                                       value="${requestScope.citizenIdentification}"
                                                                       required>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="phone-number">Phone Number:</label></td>
                                            <td class="table_r"><input type="text" id="phone-number" name="phoneNumber"
                                                                       value="${requestScope.phoneNumber}">
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="table_l"><label for="address">Address:</label></td>
                                            <td class="table_r"><input type="text" id="address" name="address"
                                                                       value="${requestScope.address}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td style="color: red">${requestScope.informationMessage}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="button-containers">
                                <button type="submit" class="button">Add</button>
                            </div>
                        </form>
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
    document.getElementById("add-student-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to add this student?',
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

    const successStatus = "${sessionScope.successAdd}";
    if (successStatus === "success") {
        ${sessionScope.remove("successAdd")}
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Add student successfully!",
            footer: '<a href="${pageContext.request.contextPath}/admin/students" style="color: blue; font-weight: bold; text-decoration: underline;">Go back to student list</a>'
        });
    }
</script>

</body>
</html>
