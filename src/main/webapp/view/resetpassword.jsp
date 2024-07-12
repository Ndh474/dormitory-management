<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Reset password</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<link rel="stylesheet" href="../css/login-style.css">
<body>
<div class="container">
    <div class="login form">
        <div style=" display: flex;justify-content: center; margin-bottom: 1rem">
            <a href="${pageContext.request.contextPath}/view/homepage.jsp"><img
                    style="width: 135px; height: 45px; border-radius: 0.5rem"
                    src="../images/logo-fpt-dorm.png"/></a>
        </div>
        <header>Change password</header>
        <form action="${pageContext.request.contextPath}/reset-password" method="post"
              onsubmit="return validateResetPassword()">
            <input type="password" id="newPassword" class="form-control" placeholder="Enter your password"
                   name="newPassword">
            <input type="button" id="toggle1" class="fa fa-fw fa-eye field-icon toggle-password" onclick="eyeFunction('newPassword', 'toggle1')">
            <input type="password" id="reNewPassword" class="form-control" placeholder="Confirm new password"
                   name="reNewPassword">
            <span id="resetPasswordMessage" style="color: red"></span>
            <input type="button" id="toggle2" class="fa fa-fw fa-eye field-icon toggle-password" onclick="eyeFunction('reNewPassword', 'toggle2')">
            <input class="button" type="submit" value="Reset password">
        </form>
    </div>
</div>
<script src="../js/scripts.js"></script>
<script>
    function validateResetPassword() {
        const newPassword = document.getElementById("newPassword").value;
        const reNewPassword = document.getElementById("reNewPassword").value;
        if (newPassword === "" || reNewPassword === "") {
            document.getElementById("resetPasswordMessage").innerText = "Please enter new password and confirm password!";
            return false;
        }
        if (newPassword !== reNewPassword) {
            document.getElementById("resetPasswordMessage").innerText = "New password and confirm password are not the same!";
            return false;
        }
        return true;
    }
</script>
</body>
</html>
