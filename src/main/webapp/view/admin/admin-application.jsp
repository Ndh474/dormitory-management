<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/3/2024
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        .select-field {
            margin-left: 5px;
            margin-right: 5px;
            font-size: 15px;
            border-color: green;
        }

        .search-field {
            width: 30rem;
            height: 31px;
            padding-left: 10px;
            font-size: 15px;
        }

        a {
            cursor: pointer;
        }

        td {
            font-size: smaller;
        }

        td table {
            font-size: medium;
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
                    <div class="row">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h2 class=" fw-bold">Application</h2>
                            </div>
                            <!-- search student by name form -->

                        </div>
                        <!--sort list form -->
                        <form action="${pageContext.request.contextPath}/admin-application" method="get">
                            <div class="d-flex justify-content-end">
                                <input type="search" name="searchKey" class="search-field"
                                       placeholder="Search application" value="${param.searchKey}">
                                <button type="submit" class="input-group-text border-0"><i
                                        class="lni lni-search-alt"></i></button>
                            </div>
                        </form>
                        <div class="col-12 col-md-12">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col" style="width: 4%">No</th>
                                        <th scope="col" style="width: 10%">Email</th>
                                        <th scope="col" style="width: 20%">Application type</th>
                                        <th scope="col" style="width: 25%">Title</th>
                                        <th scope="col" style="width: 7%">Date</th>
                                        <th scope="col" style="width: 7%">Status</th>
                                        <th scope="col" style="width: 5%">View</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% int noId = 1; %>
                                    <c:forEach var="request" items="${requestScope.requestList}">
                                        <tr>
                                            <td><%=noId++%>
                                            </td>
                                            <td>${request.getAccount().getEmail()}</td>
                                            <td>${request.getRequestType().getRequestTypeName()}</td>
                                            <td>${request.getTitle()}</td>
                                            <td>${request.getDateCreated()}</td>
                                            <c:choose>
                                                <c:when test="${request.getStatus().equals('in processing')}">
                                                    <td style="color: orange">${request.getStatus()}</td>
                                                </c:when>
                                                <c:when test="${request.getStatus().equals('processed')}">
                                                    <td style="color: green">${request.getStatus()}</td>
                                                </c:when>
                                            </c:choose>
                                            <td>
                                                <a style="color: blue"
                                                   href="${pageContext.request.contextPath}/admin-detail-application?requestId=${request.getRequestId()}">View</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <div class="d-flex justify-content-center">
            <c:forEach begin="1" end="${endPage}" var="i">
                <c:choose>
                    <c:when test="${param.page == i}">
                        <a class="btn mb-2"
                           style="background-color: green; color: white; border: 1px solid green; margin-left: 5px; font-weight: bold;"
                           href="${pageContext.request.contextPath}/admin-application?page=${i}">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn mb-2" style="color: green; border: 1px solid green; margin-left: 5px"
                           href="${pageContext.request.contextPath}/admin-application?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script>
    function updateList(selectElement) {
        var selectedValue = selectElement.value;
        var searchKey = document.querySelector('.search-field').value;
        var url = '${pageContext.request.contextPath}/admin-application?filterByStatus=' + selectedValue + '&searchKey=' + encodeURIComponent(searchKey);
        window.location.href = url;
    }
</script>

</body>
</html>
