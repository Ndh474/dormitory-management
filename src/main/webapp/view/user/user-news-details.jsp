<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/30/2024
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-style.css">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        #detail-container{

            width: 100%;
            height: 80vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .detail-content {

            padding: 20px;
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            border-radius: 5px;
            overflow: auto;
            height: 80vh;
            width: 90vh;
            white-space: nowrap;
        }

        .detail-content::-webkit-scrollbar {
            width: 10px;
            height: 10px;
            background: #fffdfd;
        }

        .detail-content::-webkit-scrollbar-track {
            background: rgba(186, 185, 185, 0.1);
        }

        .detail-content::-webkit-scrollbar-thumb {
            background: rgba(174, 174, 174, 0.5);
            border-radius: 5px;
        }
        h1 {
            font-size: 28px;
            margin-top: 0;
            text-align: center;
            color: orange;
        }

        p {
            text-align: justify;
            text-indent: 20px;
        }

        .date {
            text-align: right;
        }
        .custom-pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div style="display: flex;justify-content: right;">
                <a href="${requestScope.request.contextPath()}/dashboard"
                   class="btn btn-warning mt-2">
                    <div class="d-flex justify-content-between">
                        <i class="lni lni-close"></i>
                    </div>
                </a>
            </div>
            <div id="detail-container">

                <div class="detail-content">
                    <h1 style="font-family: -apple-system" class="custom-pre">${title}</h1>
                    <p style="font-family: -apple-system" class="date"><em>${date}</em></p>
                    <%
                        String content = (String) request.getAttribute("content");
                    %>
                    <pre style="font-family: -apple-system; font-size: 16px" class="custom-pre"><%= content %></pre>
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
