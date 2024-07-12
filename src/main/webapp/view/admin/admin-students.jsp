<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 530px;
            overflow: auto;
        }

        .select-field {
            margin-right: 5px;
            font-size: 13px;
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
                                <h2 class=" fw-bold">Students</h2>
                            </div>
                            <!-- search student by name form -->

                            <form action="${pageContext.request.contextPath}/admin/students">
                                <div class="d-flex justify-content-start">
                                    <select class="select-field" name="searchOption">
                                        <option value="all">All</option>
                                        <option value="byName" ${requestScope.searchOption.equals("byName")?"selected":""}>
                                            Search by name
                                        </option>
                                        <option value="byGmail" ${requestScope.searchOption.equals("byGmail")?"selected":""}>
                                            Search by gmail
                                        </option>
                                        <option value="byRollNumber" ${requestScope.searchOption.equals("byRollNumber")?"selected":""}>
                                            Search by roll number
                                        </option>
                                        <option value="byRoom" ${requestScope.searchOption.equals("byRoom")?"selected":""}>
                                            Search by room
                                        </option>
                                    </select>
                                    <input type="search" class="search-field" name="searchInput"
                                           value="${requestScope.searchInput}"
                                           placeholder="Type here"/>
                                    <button type="submit" class="input-group-text border-0"><i
                                            class="lni lni-search-alt"></i></button>
                                </div>
                            </form>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>List students:</p>
                            <div><a href="${pageContext.request.contextPath}/admin-add-information"
                                    class="btn btn-success">
                                <div class="d-flex justify-content-between">
                                    <i class="lni lni-circle-plus"
                                       style="font-size: 25px; padding-right: 5px"></i>
                                    <div>Add student</div>
                                </div>
                            </a></div>
                        </div>
                        <div class="col-12 col-md-12" style="height: 500px">
                            <div class="card border-1 scrollable-table">
                                <table class="table table-striped-columns">
                                    <thead>
                                    <tr>
                                        <th scope="col" style="width: 4%;">No</th>
                                        <th scope="col" style="width: 10%">Roll Number</th>
                                        <th scope="col" style="width: 15%">Full Name</th>
                                        <th scope="col" style="width: 20%">Email</th>
                                        <th scope="col" style="width: 6%">Gender</th>
                                        <th scope="col" style="width: 6%">Room</th>
                                        <th style="width: 8%">Status</th>
                                        <th scope="col" style="width: 5%">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% int noId = 1;%>
                                    <c:forEach var="information" items="${requestScope.studentsList}">
                                        <tr>
                                            <td><%=noId++%>
                                            </td>
                                            <td>${information.getRollNumber()}</td>
                                            <td>${information.getFullName()}</td>
                                            <td>${information.getAccount().getEmail()}</td>
                                            <c:choose>
                                                <c:when test="${information.getGender().equals('male')}">
                                                    <td style="color: blue">Male♂️</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td style="color: deeppink">Female♀️</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin-detail-room?roomName=${information.getRoomName()}">${information.getRoomName()}</a>
                                            </td>
                                            <c:choose>
                                                <c:when test="${information.getAccount().getAccountStatus().getStatusName().equals('active')}">
                                                    <td style="color: green">${information.getAccount().getAccountStatus().getStatusName()}</td>
                                                </c:when>
                                                <c:when test="${information.getAccount().getAccountStatus().getStatusName().equals('inactive')}">
                                                    <td style="color: #e6b400">${information.getAccount().getAccountStatus().getStatusName()}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td style="color: red">${information.getAccount().getAccountStatus().getStatusName()}</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin-update-student?rollNumber=${information.getRollNumber()}">Detail</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <c:if test="${searchOption==null||searchOption=='All'}">
                                <div class="page-containers"
                                     style="display: flex;justify-content: center;align-items: center;">
                                    <c:set var="currentPage" value="${requestScope.currentPage}"/>
                                    <c:set var="maxPage" value="${requestScope.maxPage}"/>
                                    <c:set var="start" value="${currentPage - 4}"/>
                                    <c:if test="${start < 1}">
                                        <c:set var="start" value="1"/>
                                    </c:if>
                                    <c:set var="end" value="${start + 8}"/>
                                    <c:if test="${end > maxPage}">
                                        <c:set var="end" value="${maxPage}"/>
                                    </c:if>
                                    <c:set var="start" value="${end - 8}"/>


                                        <%--show paging--%>
                                    <c:if test="${start < 1}">
                                        <c:set var="start" value="1"/>
                                    </c:if>

                                    <c:if test="${start>1}">
                                        <a href="${pageContext.request.getContextPath()}/admin/students?currentPage=1"
                                           style=" color: green; width: 30px;height: 30px;text-align: center; padding:2px;margin-right: 10px; border:1px solid; border-radius: 15px;">1</a>
                                        <span style="margin-right: 10px;">...</span>
                                    </c:if>
                                    <c:forEach var="i" begin="${start}" end="${end}">
                                        <c:choose>
                                            <c:when test="${currentPage == i}">
                                                <a href="${pageContext.request.contextPath}/admin/students?currentPage=${i}"
                                                   style="color: orange; width: 30px; height: 30px; text-align: center; padding: 2px; margin-right: 10px; border:1px solid; border-radius: 15px;">
                                                        ${i}
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/admin/students?currentPage=${i}"
                                                   style="color: green; width: 30px; height: 30px; text-align: center; padding: 2px; margin-right: 10px; border:1px solid; border-radius: 15px;">
                                                        ${i}
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${end < maxPage}">
                                        <span style="margin-right: 10px;">...</span>
                                        <a href="${pageContext.request.getContextPath()}/admin/students?currentPage=${maxPage}"
                                           style=" color: green; width: 30px;height: 30px;text-align: center; padding:2px;margin-right: 10px; border:1px solid;border-radius: 15px;">${maxPage}</a>
                                    </c:if>
                                </div>
                            </c:if>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hamburger.js"></script>
</body>
</html>
