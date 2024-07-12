
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!---Custom CSS File--->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-style.css">
    <style>
        .google-login-button {
            background-color: #db4437; /* Google brand color */
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
            height: 60px;
        }

        .google-login-button:hover {
            background-color: #c1351d; /* Darker shade for hover effect */
        }

        .google-login-button i {
            margin-right: 8px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form">
        <div style=" display: flex;justify-content: center; margin-bottom: 1rem">
            <a href="${pageContext.request.contextPath}/home"><img
                    style="width: 135px; height: 45px; border-radius: 0.5rem; border: 1px"
                    src="${pageContext.request.contextPath}/images/logo-fpt-dorm.png"></a>
        </div>
        <header>Login</header>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" placeholder="Enter your email" name="email" value="${requestScope.inputEmail}">
            <div>
                <input type="password" id="myInput" class="form-control" placeholder="Enter your password"
                       name="password" value="${requestScope.inputPassword}">
                <input type="button" class="fa fa-fw fa-eye field-icon toggle-password" onclick="myFunction()">
            </div>
            <div style="display: flex; justify-content: space-between">
<%--                <div class="remember-me">--%>
<%--                    <input style="height: 20px; width: 20px;" type="checkbox" id="remember_me">--%>
<%--                    <label for="remember_me" style="font-size: 15px">Remember me</label>--%>
<%--                </div>--%>
                <a style="font-size: 15px" href="${pageContext.request.contextPath}/email-forgot-password">Forgot
                    password?</a>
            </div>
            <span style="color: red">${requestScope.loginMessage}</span>
            <input type="submit" class="button" value="Login">
        </form>
        <div class="google-login">
            <form action="${pageContext.request.contextPath}/google-login">
                <button type="submit" class="google-login-button"><i class="fa-brands fa-google"></i> Login By Google</button>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
