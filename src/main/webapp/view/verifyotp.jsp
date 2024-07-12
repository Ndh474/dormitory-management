<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/23/2024
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Verify OTP</title>
    <link rel="stylesheet" href="../css/login-style.css">
</head>
<body>
<div class="container">
    <div class="login form">
        <div style=" display: flex;justify-content: center; margin-bottom: 1rem">
            <a href="${pageContext.request.contextPath}/home"><img
                    style="width: 135px; height: 45px; border-radius: 0.5rem"
                    src="../images/logo-fpt-dorm.png"/></a>
        </div>
        <header>Verify OTP</header>
        <form id="otp-form" action="${pageContext.request.contextPath}/verify-otp" method="post">
            <p id="success_send_mail">We sent a code to ${emailTo}, please check it and enter code here!</p>
            <input type="text" name="otpInput" id="otpInput" placeholder="Enter code" required>
            <p style="color: red">${verifyOtpMessage}</p>
            <input class="button" type="submit" value="Verify"><br>
            <a href="${pageContext.request.contextPath}/email-forgot-password" id="a_enter_again">This is not your
                email? Enter again</a>
        </form>
    </div>
</div>
</body>
</html>
