<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/30/2024
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.News" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.getContextPath()}/css/user-style.css">
    <style>
        .news-container {
            margin: 0 auto;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap; /* Sự chia tỷ lệ cho các phần tử chồng chéo khi cần */
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        td {
            font-size: smaller;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .title {
            max-width: 250px; /* Độ rộng cố định cho cột tiêu đề */
            white-space: nowrap; /* Ngăn các nội dung quá dài bị quấn xuống dòng mới */
            overflow: hidden; /* Ẩn phần nội dung bị tràn ra ngoài */
            text-overflow: ellipsis; /* Hiển thị dấu "..." cho nội dung bị tràn ra ngoài */
        }

        .content {
            max-width: 350px; /* Độ rộng cố định cho cột tiêu đề */
            white-space: nowrap; /* Ngăn các nội dung quá dài bị quấn xuống dòng mới */
            overflow: hidden; /* Ẩn phần nội dung bị tràn ra ngoài */
            text-overflow: ellipsis; /* Hiển thị dấu "..." cho nội dung bị tràn ra ngoài */
        }

        .filter-search {
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .filter-search > div {
            margin-right: 10px;
        }

        .filter-search form {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        label {
            display: block;
            font-weight: bold;
        }

        textarea {
            width: 100%;
            height: 200px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .modify-form {
            display: none;
            background-color: whitesmoke;
            border: 1px solid green;
            padding: 10px;
            border-radius: 10px;
            width: 1000px;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 10000;
        }

        .search-field {
            width: 25rem;
            height: 38px;
            padding-left: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
        }

        .date-field {
            width: 10rem;
            height: 38px;
            padding-left: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
        }

        .title-field {
            width: 100%;
            height: 38px;
            padding-left: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
        }

        .btn-field {
            width: 60px;
            background-color: green;
            color: white;
            border: 1px solid;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="admin-sidebar.jsp"></jsp:include>
    <div id="main">
        <jsp:include page="../user/header.jsp"></jsp:include>
        <main class=" px-3">
            <div class="news-container">
                <h2 class="fw-bold">List of news</h2>
                <div class="filter-search mt-2">
                    <form action="${pageContext.request.contextPath}/admin/news" method="post">
                        <input class="date-field" type="date" id="filterDate" name="date" value="${requestScope.date}">
                        <button class="btn btn-success" style="border-radius: 0px" type="submit">Filter By Date</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/news" method="post">
                        <div class="d-flex justify-content-start">
                            <input type="text" id="searchTitle" class="search-field" placeholder="Search By Title..."
                                   value="${requestScope.title}" name="title"/>
                            <button type="submit" class="input-group-text border-0"><i
                                    class="lni lni-search-alt"></i></button>
                        </div>
                    </form>
                </div>
            </div>
            <div style=" display: flex; justify-content: space-between;flex-wrap: wrap">
                <a data-bs-toggle="modal" data-bs-target="#addNewsModal">
                    <div class="d-flex justify-content-between btn btn-success">
                        <i class="lni lni-circle-plus"
                           style="font-size: 25px; padding-right: 5px"></i>
                        <div>Add News</div>
                    </div>
                </a>
            </div>
            <div class="row mt-2">
                <div class="col-12 col-md-12">
                    <div class="card border-1">
                        <table class="table table-striped-columns">
                            <tr>
                                <th class="title">Title</th>
                                <th class="content">Content</th>
                                <th>Date Created</th>
                                <th>Create By</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <c:forEach var="news" items="${newsList}">
                                <tr>
                                    <td class="title">${news.getTitle()}</td>
                                    <td class="content">${news.getContent()}</td>
                                    <td>${news.getDateCreated()}</td>
                                    <td>${news.getAccount().getEmail()}</td>
                                    <td style="width: 5%">
                                        <a data-bs-toggle="modal" data-bs-target="#editNewsModal"
                                           onclick="EditNews(`${news.getNewsId()}`,`${news.getTitle()}`,`${news.getContent()}`)">
                                            <button class="btn-field">
                                                <div>Edit</div>
                                            </button>
                                        </a>
                                    </td>
                                    <td style="width: 5%">
                                        <a style="color: white"
                                           href="${pageContext.request.contextPath}/delete-news?id=${news.getNewsId()}" class="delete-news-a">
                                            <button class="btn-field" style="background-color: orange">Delete</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="d-flex justify-content-center">
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <a class="btn mb-2" style="color: green; border: 1px solid green; margin-left: 5px"
                                   href="${pageContext.request.contextPath}/admin/news?page=${i}">${i}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<div id="addNewsModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">Add news</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/add-news" method="post" id="add-news-form">
                    <div class="modal-body">
                        <label for="title">Title</label>
                        <input type="text" id="title" name="title" class="title-field" required
                               placeholder="Enter title"><br>
                        <label for="content" class="mt-2">Content</label>
                        <textarea id="content" name="content" required placeholder="Enter content"></textarea>
                        <div class="d-flex justify-content-end mt-2">
                            <button type="submit" class="btn btn-success">Add</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="editNewsModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="form-group">
                <div style="border-bottom: 0" class="modal-header">
                    <h5 style="color: green">Edit news</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/edit-news" method="post" id="edit-news-form">
                    <div class="modal-body">
                        <input type="text" name="newsId" id="newsId" hidden value="">
                        <label for="newsTitle">Title</label>
                        <input type="text" name="newsTitle" id="newsTitle" class="title-field" required
                               value=""><br>
                        <label for="newsContent" class="mt-2">Content</label>
                        <textarea id="newsContent" name="newsContent" rows="10"
                                  cols="30" required></textarea>
                        <div class="d-flex justify-content-end mt-2">
                            <button type="submit" class="btn btn-success">Save change</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hamburger.js"></script>
<script>
    function showAddNewsForm() {
        const show_my_form = document.getElementById("add-form");
        if (show_my_form.style.display === "none" || show_my_form.style.display === "") {
            show_my_form.style.display = "block";
        }
    }

    function closeForm(id) {
        document.getElementById(id).style.display = "none";
    }

    function EditNews(newsId, newsTitle, newsContent) {
        document.getElementById("newsId").value = newsId;
        document.getElementById("newsTitle").value = newsTitle;
        document.getElementById("newsContent").value = newsContent;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.querySelectorAll(".delete-news-a").forEach(item => {
        item.addEventListener("click", event => {
            event.preventDefault();
            Swal.fire({
                title: 'Confirm',
                text: 'Are you sure you want to delete this news?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = item.href;
                }
            });
        });
    });


    const successDeleteNews = "${sessionScope.successDeleteNews}";
    if (successDeleteNews === "success") {
        ${ sessionScope.remove("successDeleteNews") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Delete News successfully!",
        });
    }

    document.getElementById("edit-news-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to edit this news?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                this.submit(); // submit the form if the user confirms
            }
        });
    });
    const successEditNews = "${sessionScope.successEditNews}";
    if (successEditNews === "success") {
        ${ sessionScope.remove("successEditNews") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Edit News successfully!",
        });
    }

    document.getElementById("add-news-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to add this news?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                this.submit(); // submit the form if the user confirms
            }
        });
    });

    const successAddNews = "${sessionScope.successAddNews}";
    if (successAddNews === "success") {
        ${ sessionScope.remove("successAddNews") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Add News successfully!",
        });
    }
</script>
</body>
</html>
