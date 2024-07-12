<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/24/2024
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ</title>
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
                    <h2 class="fw-bold">FAQ</h2>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="card border-1">
                                <div class="card-body py-1">
                                    <h4>Booking Bed</h4>
                                    <h6>1. Can't click Confirm button to book bed</h6>
                                    <p>Please wait to a few second until policy popup is shown. If it takes too long, please reload the page.</p>
                                    <h6>2. Can't book bed because of unpaid bill</h6>
                                    <p>Please deposit enough money in fap wallet and wait until the dormitory manager makes deducts the bill.</p>
                                    <h4>Other</h4>
                                    <h6>How to use the OCD website</h6>
                                    <p>Please contact Dormitory management.</p>
                                    <h4>System</h4>
                                    <h6>For problems related to website operation</h6>
                                    <ul>
                                        <li>- Features misbehaving (Click create a request but no request is generated, ...)</li>
                                        <li>- Website return error</li>
                                        <li>- Any feature of the website loads very slowly</li>
                                        <li>- Please contact: phongnthe172583@fpt.edu.vn</li>
                                    </ul>
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
</body>
</html>
