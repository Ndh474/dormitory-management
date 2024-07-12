<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/19/2024
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot password</title>
    <link rel="stylesheet" href="../css/login-style.css">
</head>
<body>
<div class="container">
    <!-- forgot password form, enter email to send code-->
    <div class="login form">
        <div id="send-div">
            <div style=" display: flex;justify-content: center; margin-bottom: 1rem">
                <a href="${pageContext.request.contextPath}/home"><img
                        style="width: 135px; height: 45px; border-radius: 0.5rem"
                        src="../images/logo-fpt-dorm.png"/></a>
            </div>
            <header>Forgot password</header>
            <form id="send-form" method="post" action="${pageContext.request.contextPath}/email-forgot-password"
                  onsubmit="return validateInput()">
                <label for="emailTo">Enter your email: </label>
                <input type="email" name="emailTo" id="emailTo" value="${emailTo}" required>
                <span id="forgotPasswordMessage" style="color: red">${forgotPasswordMessage}</span>
                <input class="button" type="submit" value="Send">
            </form>
            <div class="signup">
                <span class="signup">Already have an account?
                 <a href="${pageContext.request.contextPath}/login">Login</a>
                </span>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function validateInput() {
        const email = document.getElementById("emailTo").value;
        if (email === "") {
            document.getElementById("forgotPasswordMessage").innerText = "Please enter your email!";
            return false;
        }
        return true;
    }
</script>
</html>
