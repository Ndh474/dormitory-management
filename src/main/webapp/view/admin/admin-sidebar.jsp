<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/28/2024
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        #sidebar {
            background-color: green;
            position: fixed;
            height: 100vh;
        }

        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<aside id="sidebar">
    <div class="d-flex">
        <button style="padding-top: 20px" class="toggle-btn" type="button">
            <i class="lni lni-grid-alt"></i>
        </button>
        <div class="sidebar-logo">
            <a class="sidebar-link">
                <span class="sidebar-link" style="font-size: 16px">Welcome Admin!</span>
            </a>
        </div>
    </div>
    <ul class="sidebar-nav">
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/dashboard" class="sidebar-link">
                <i class="lni lni-dashboard"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/admin/students" class="sidebar-link">
                <i class="lni lni-users"></i>
                <span>Students</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/admin-dorm" class="sidebar-link">
                <i class="lni lni-apartment"></i>
                <span>Doom & Room</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/admin-application" class="sidebar-link">
                <i class="lni lni-envelope"></i>
                <span>Application</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/admin/news" class="sidebar-link">
                <i class="lni lni-popup"></i>
                <span>News</span>
            </a>
        </li>
        <c:if test="${sessionScope.account.getAccountID()==1}">
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/admin-account-manager" class="sidebar-link">
                    <i class="lni lni-user"></i>
                    <span>Admin Account Manager</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/config-setting" class="sidebar-link">
                    <i class="lni lni-cog"></i>
                    <span>Setting</span>
                </a>
            </li>
        </c:if>
    </ul>
    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/logout" class="sidebar-link">
            <i class="lni lni-exit"></i>
            <span>Logout</span>
        </a>
    </div>
</aside>
</body>
</html>
