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
            width: 300px;
            padding: 0 20px;
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
                    <h2 class="fw-bold">Add New Admin</h2>

                    <div class="row">
                        <form action="" method="post">
                            <div style="justify-content: center" class="card border-1">
                                <div class="card-body py-2">
                                    <h5>Account</h5>
                                    <hr>
                                    <table>
                                        <tr>
                                            <td class="table_l"><label for="email">Email: </label></td>
                                            <td class="table_r"><input type="text" name="email" id="email" required></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l"><label for="password">Password:</label></td>
                                            <td class="table_r"><input type="text" name="password" id="password" required></td>
                                        </tr>

                                        <tr>
                                            <td class="table_l"></td>
                                            <td class="table_r"><h7 id="isValid" style="color: #f83f3f"></h7></td>
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

<script>
    function checkPass(){
        const pass = document.getElementById("password");
        const repass = document.getElementById("rePassword");
        const isValid = document.getElementById("isValid");
        if(!(pass.value===repass.value)){
            isValid.innerText = "re-password is not match" ;

        }else {
            isValid.innerText = "password is match";
            isValid.style.color = "green"
        }
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script type="text/javascript" src="../../js/scripts.js"></script>

</body>
</html>
