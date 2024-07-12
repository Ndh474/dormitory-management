<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/30/2024
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Setting</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-style.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class=" fw-bold ">Setting</h2>
                    <div class="row">
                        <div class="col-5">
                            <div class="card border-1">
                                <div class="card-body py-4">
                                    <form action="${pageContext.request.contextPath}/config-setting" method="post" id="config-setting-form">
                                        <table>
                                            <tr>
                                                <td><label for="room-and-utility-bill-issue-day">Room and Utility Bill
                                                    Issue Day: </label></td>
                                                <td><input type="text" name="roomAndUtilityBilIssueDay"
                                                           value="${requestScope.roomAndUtilityBilIssueDay}"
                                                           id="room-and-utility-bill-issue-day"></td>
                                            </tr>
                                            <tr>
                                                <td><label for="notification-day">Notification Day: </label></td>
                                                <td><input type="text" name="notificationDay"
                                                           value="${requestScope.notificationDay}"
                                                           id="notification-day"></td>
                                            </tr>
                                            <tr>
                                                <td><label for="room-and-utility-payment-day">Room and Utility Payment
                                                    Date: </label></td>
                                                <td><input type="text" name="roomAndUtilityPaymentDay"
                                                           value="${requestScope.roomAndUtilityPaymentDay}"
                                                           id="room-and-utility-payment-day"></td>
                                            </tr>
                                            <tr>
                                                <td><label for="book-room">Book Room: </label></td>
                                                <td>
                                                    <select name="bookRoom" id="book-room">
                                                        <option value="true" ${requestScope.bookRoom==true? "selected":""}>
                                                            Yes
                                                        </option>
                                                        <option value="false" ${requestScope.bookRoom==false? "selected":""}>
                                                            No
                                                        </option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label for="electricity-price">Electricity Price: </label></td>
                                                <c:set var="electricityPrice" value="${requestScope.electricityPrice}" />

                                                <fmt:formatNumber value="${electricityPrice}" var="formattedElectricityAmount" type="number" groupingUsed="true" />
                                                <td><input type="text" name="electricityPrice"
                                                           value="${formattedElectricityAmount}"
                                                           id="electricity-price"></td>
                                            </tr>
                                            <tr>
                                                <td><label for="water-price">Water Price: </label></td>
                                                <c:set var="waterPrice" value="${requestScope.waterPrice}" />

                                                <fmt:formatNumber value="${waterPrice}" var="formattedWaterAmount" type="number" groupingUsed="true" />
                                                <td><input type="text" name="waterPrice"
                                                           value="${formattedWaterAmount}" id="water-price">
                                                </td>
                                            </tr>
                                        </table>
                                        <c:if test="${sessionScope.account.getAccountID()==1}">
                                            <button type="submit" class="btn btn-primary">Save</button>
                                        </c:if>

                                    </form>
                                </div>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.getElementById("config-setting-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to change this setting?',
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

    const successStatus = "${sessionScope.successChangeSetting}";
    if (successStatus === "success") {
        ${ sessionScope.remove("successChangeSetting") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Change Setting successfully!",
        });

    }
</script>
</body>
</html>
