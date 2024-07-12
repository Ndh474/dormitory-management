<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.News" %>
<%@ page import="java.util.List" %><%--
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
        body {
            background-color: #f0f8ff;
        }

        .news-container {
            display: flex;
            flex-direction: column;
            gap: 10px;
            height: 100vh;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .news-list {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .news-item {
            width: calc(50% - 5px);
            padding: 10px;
            border: 2px solid #959595;
            border-radius: 10px;
            box-sizing: border-box;
            text-decoration: none;
            color: inherit;
            margin-bottom: 10px;
        }

        .news-list :hover {
            background-color: #e5e3e3;
        }

        .news-date {
            font-weight: bold;
            font-size: 13px;
            margin-bottom: 5px;
            color: rgb(169, 88, 16);
        }

        .news-title {
            margin-bottom: 15px;
            color: rgb(234, 128, 35);
        }

        .search-box input {
            flex: 1;
            padding: 8px;
            border: 1px solid #ccc;
            margin-bottom: 20px;
            font-size: 16px;
            box-sizing: border-box;
        }

        .search-box button {
            border: none;
            background-color: #4caf50;
            color: #fff;
            margin-bottom: 20px;
            padding: 8px 12px;
            cursor: pointer;
        }

        .pagination {
            display: flex;
            justify-content: center;
            gap: 5px;
        }

        .pagination-link {
            padding: 5px 10px;
            border: 1px solid #ccc;
            text-decoration: none;
            color: #1eaa1e;
            cursor: pointer;
        }

        .pagination-link.active {
            background-color: #1eaa1e;
            color: #fff;
            border-color: #ffffff;
        }

        .search-field {
            width: 25rem;
            height: 38px;
            padding-left: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <div class="d-flex justify-content-between">
                        <h2 class="fw-bold">News</h2>
                        <div>
                            <form action="<%=request.getContextPath()%>/user/news" method="post">
                                <div class="d-flex justify-content-start">
                                    <input type="text" class="search-field" placeholder="Search..." name="title">
                                    <button type="submit" class="input-group-text border-0"><i
                                            class="lni lni-search-alt"></i></button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="d-flex justify-content-between">
                            <div class="col-12 col-md-12">
                                <div class="card border-1 scrollable-table">
                                    <!--code here-->
                                    <div class="news-container">
                                        <div class="news-list">
                                            <c:forEach var="news" items="${requestScope.newsList}">
                                                <a href="${pageContext.request.contextPath}/user-news-detail?id=${news.newsId}"
                                                   class="news-item">
                                                    <div class="news-date">${news.dateCreated}</div>
                                                    <div class="news-title">${news.title}</div>
                                                </a>
                                            </c:forEach>
                                        </div>
                                        <%
                                            String isSearch = (String) request.getAttribute("isSearch");
                                            if (isSearch == null) {
                                        %>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link active">1</a>
                                        </nav>
                                        <%
                                        } else {
                                            int maxPage = (int) request.getAttribute("maxPage");
                                            int currentPage = (int) request.getAttribute("page");
                                            int nextPage = currentPage + 1;
                                            int next2Page = currentPage + 2;
                                            int prePage = currentPage - 1;
                                            int pre2Page = currentPage - 2;
                                            if (maxPage < 3) {


                                                if (maxPage == 1) {%>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link active">1</a>
                                        </nav>
                                        <%
                                            }
                                            if (maxPage == 2 && currentPage == 1) {
                                        %>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link active">1</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=2"
                                               class="pagination-link ">2</a>
                                        </nav>
                                        <% } else if (maxPage == 2 && currentPage == 2) {%>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link">1</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=2"
                                               class="pagination-link active">2</a>
                                        </nav>

                                        <%
                                            }
                                        } else {
                                            if (currentPage == 1) {
                                        %>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link active">1</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=2"
                                               class="pagination-link">2</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=3"
                                               class="pagination-link">3</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= maxPage %>"
                                               class="pagination-link">>></a>
                                        </nav>
                                        <%
                                        } else if (currentPage == maxPage) {
                                        %>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link"><<</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= pre2Page %>"
                                               class="pagination-link "><%= pre2Page %>
                                            </a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= prePage %>"
                                               class="pagination-link "><%= prePage %>
                                            </a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= currentPage %>"
                                               class="pagination-link active"><%= currentPage %>
                                            </a>
                                        </nav>
                                        <%
                                        } else {
                                        %>
                                        <nav class="pagination">
                                            <a href="<%= request.getContextPath() %>/user/news?page=1"
                                               class="pagination-link"><<</a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= prePage %>"
                                               class="pagination-link "><%= prePage %>
                                            </a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= currentPage %>"
                                               class="pagination-link active"><%= currentPage %>
                                            </a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= nextPage %>"
                                               class="pagination-link "><%= nextPage %>
                                            </a>
                                            <a href="<%= request.getContextPath() %>/user/news?page=<%= maxPage %>"
                                               class="pagination-link">>></a>
                                        </nav>
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
</body>
</html>