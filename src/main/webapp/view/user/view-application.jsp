<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/5/2024
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.Format" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send application</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        table {
            font-size: smaller;
        }

        .truncate {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .overlay-image {
            background-color: rgba(0,0,0,0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            position: fixed;
            width: 100vw;
            height: 100vh;
            z-index: 1000;
            overflow: auto; /* Cho phép cuộn toàn bộ overlay */
        }
        .image-container {
            position: relative;

            width: 1000px;
            max-width: 90%; /* Giới hạn chiều rộng container */
            max-height: 90%; /* Giới hạn chiều cao container */
            overflow: auto; /* Cho phép cuộn container */
        }
        .close-icon {
            position: fixed;

            top: 10px;
            right: 50px;
            font-size: 44px;
            color: white;
            cursor: pointer;
            z-index: 1001;
        }
        .full-image {
            display: block;
            width: 100%;
            height: auto;
            max-width: none;
            max-height: none;
        }
        .hidden{
            display:none;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="user-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="header.jsp"></jsp:include>
        <main class="content px-3">
            <div class="container-fluid">
                <div class="mb-3">
                    <h2 class="fw-bold">View application</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card border-1">
                                <div class="card-body py-1">
                                    <table class="table table-striped-columns text-center scrollable-table">
                                        <tr>
                                            <th>No</th>
                                            <th>Application type</th>
                                            <th style="width: 18rem">Title</th>
                                            <th style="width: 25rem">Content</th>
                                            <th style="width: 10rem">image</th>
                                            <th>Create date</th>
                                            <th style="width: 20rem">Response</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                        <c:forEach items="${requestScope.requestList}" var="request" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td>
                                                <td>${request.requestType.requestTypeName}</td>
                                                <td class="truncate" style="max-width: 18rem">${request.title}</td>
                                                <td class="truncate" style="max-width: 25rem">${request.content}</td>
                                                <td>
                                                    <c:if test="${request.image!=null}">
                                                        <img onclick="showImage('${pageContext.request.contextPath}/application-images/${request.image}')" style="max-height: 50px;cursor:pointer;width: auto;" src="${pageContext.request.contextPath}/application-images/${request.image}">
                                                    </c:if>
                                                </td>
                                                <td>${request.dateCreated}</td>
                                                <td class="truncate" style="max-width: 20rem">${request.response}</td>
                                                <c:choose>
                                                    <c:when test="${request.getStatus().equals('in processing')}">
                                                        <td style="color: orange">In processing</td>
                                                    </c:when>
                                                    <c:when test="${request.getStatus().equals('processed')}">
                                                        <td style="color: green">Processed</td>
                                                    </c:when>
                                                </c:choose>
                                                <td>
                                                    <a class="btn btn-success" data-bs-toggle="modal" data-bs-target="#viewApplicationModal"
                                                    onclick="viewApplication(`${request.title}`,`${request.content}`, `${request.requestType.requestTypeName}` ,
                                                            `${request.dateCreated}`, `${request.response}`, `${request.getStatus()}`)">View</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
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
                           href="${pageContext.request.contextPath}/view-application?page=${i}">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn mb-2" style="color: green; border: 1px solid green; margin-left: 5px"
                           href="${pageContext.request.contextPath}/view-application?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
    <div id="overlay-image" class="hidden">
        <div class="image-container">
            <span class="close-icon" onclick="closeOverlay()">&times;</span>
            <img id="application-image"  class="full-image" src="${pageContext.request.contextPath}/application-images/${requestScope.requestDetail.image}" alt="application image">
        </div>
    </div>


</div>

<div id="viewApplicationModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">View Application</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" style="font-size: 14px">
                    <span style="font-weight: bold">Title: </span>
                    <span id="appTitle"></span><br><br>
                    <span style="font-weight: bold">Content: </span>
                    <span id="appContent"></span><br><br>
                    <span style="font-weight: bold">Type: </span>
                    <span id="appType"></span><br><br>
                    <span style="font-weight: bold">Date: </span>
                    <span id="appDate"></span><br><br>
                    <span style="font-weight: bold">Response: </span>
                    <span id="appResponse"></span><br><br>
                    <span style="font-weight: bold">Status: </span>
                    <span id="appStatus"></span><br><br>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function viewApplication(appTitle, appContent, appType, appDate, appResponse, appStatus) {
        document.getElementById("appTitle").innerHTML = appTitle;
        document.getElementById("appContent").innerHTML = appContent;
        document.getElementById("appType").innerHTML = appType;
        document.getElementById("appDate").innerHTML = appDate;
        document.getElementById("appResponse").innerHTML = appResponse;
        document.getElementById("appStatus").innerHTML = appStatus;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script>
    function showImage(url){
        const overlay = document.getElementById('overlay-image');
        const image = document.getElementById('application-image');
        overlay.classList.remove('hidden')
        overlay.classList.add('overlay-image')
        image.src = url;

    }
    function closeOverlay(){
        const overlay = document.getElementById('overlay-image');
        const image = document.getElementById('application-image');
        overlay.classList.add('hidden')
        overlay.classList.remove('overlay-image')
        image.src = '';
    }
</script>
</body>
</html>
