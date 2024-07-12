<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Information" %>
<%@ page import="entity.Account" %>
<%@page import="services.InformationService" %>
<%@ page import="services.impl.InformationServiceImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/20/2024
  Time: 5:51 PM
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
    </div>
    <ul class="sidebar-nav">
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/dashboard" class="sidebar-link">
                <i class="lni lni-home"></i>
                <span>Home</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/user/news" class="sidebar-link">
                <i class="lni lni-popup"></i>
                <span>News</span>
            </a>
        </li>
        <%
            InformationService informationService = new InformationServiceImpl();
            Account account = (Account) request.getSession().getAttribute("account");
            Information information = informationService.getByAccountID(account.getAccountID());
        %>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/user/view-dorm" class="sidebar-link">
                <i class="lni lni-check-box"></i>
                <% if(information.getRoomName()==null){%>
                <span><%="Book room"%>
                <%}%>
                <% if(information.getRoomName()!=null){%>
                <span><%="Available room"%>
                <%}%>
            </a>
        </li>
        <% if(information.getRoomName()!=null){%>
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/user/myroom" class="sidebar-link">
                        <i class="lni lni-package"></i>
                        <span>My room</span>
                    </a>
                </li>
        <%}%>
        <li class="sidebar-item">
            <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
               data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                <i class="lni lni-checkmark-circle"></i>
                <span>My application</span>
            </a>
            <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/send-application" class="sidebar-link">Send
                        Application</a>
                </li>
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/view-application" class="sidebar-link">View
                        application</a>
                </li>
            </ul>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/payment-history" class="sidebar-link">
                <i class="lni lni-coin"></i>
                <span>Payment history</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/user/info" class="sidebar-link">
                <i class="lni lni-user"></i>
                <span>Personal information</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/user-notification" class="sidebar-link">
                <i class="lni lni-alarm"></i>
                <span>Notification</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/view/user/user-faq.jsp" class="sidebar-link">
                <i class="lni lni-support"></i>
                <span>FAQ</span>
            </a>
        </li>
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
