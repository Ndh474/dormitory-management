<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/1/2024
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room reservation</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        a {
            cursor: pointer;
        }

        td {
            font-size: smaller;
        }

        .table_style{
            width: 300px;
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
                    <h2 class="fw-bold">List of reservation</h2>
                    <p style="color: red; font-size: small; margin: 0">*You can click on the name of room to detail.</p>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body py-1">
                                <div class="card border-1">
                                    <div class="card-body py-1 scrollable-table">
                                        <form>
                                            <table class="table table-striped-columns text-center">
                                                <tr>
                                                    <th scope="col" style="width: 4%">No</th>
                                                    <th scope="col" style="width: 15%">Full name</th>
                                                    <th scope="col" style="width: 10%">Roll number</th>
                                                    <th scope="col" style="width: 20%">Email</th>
                                                    <th scope="col" style="width: 15%">Phone number</th>
                                                    <th scope="col" style="width: 6%">Gender</th>
                                                    <th scope="col" style="width: 10%">Book room</th>
                                                    <th scope="col" style="width: 8%">Date</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Hàm Lợn Lee</td>
                                                    <td>QT123456</td>
                                                    <td>LeeeLQT123456@fpt.edu.vn</td>
                                                    <td>0123456789</td>
                                                    <td>Male</td>
                                                    <td>
                                                        <a style="color: blue" data-bs-toggle="modal"
                                                           data-bs-target="#detailRoomModal">A101</a>
                                                    </td>
                                                    <td>10/05/2024</td>
                                                    <td><button style="background-color: green; color: white; border: none; border-radius: 3px">Accept</button></td>
                                                    <td><button style="background-color: red; color: white; border: none; border-radius: 3px">Ignore</button></td>
                                                </tr>
                                            </table>
                                        </form>
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

<!--detail room modal, update status, water, electric number of room -->
<div id="detailRoomModal" class="modal fade">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-group">
                    <div style="border-bottom: 0" class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="col-12 col-md-12">
                        <h5 style="color: green">Dorm information</h5>
                            <div class="d-flex justify-content-evenly">
                                <div class="table_style">
                                    <table class="table table-striped-columns">
                                        <tr>
                                            <td>Room name:</td>
                                            <td>A101</td>
                                        </tr>
                                        <tr>
                                            <td>Total bed:</td>
                                            <td>6</td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td>Active</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="table_style">
                                    <table class="table table-striped-columns">
                                        <tr>
                                            <td>Dorm:</td>
                                            <td>A</td>
                                        </tr>
                                        <tr>
                                            <td>Used bed:</td>
                                            <td>3</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="table_style">
                                    <table class="table table-striped-columns">
                                        <tr>
                                            <td>Floor</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>Free bed:</td>
                                            <td>3</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        <h5 style="color: green">List of student</h5>
                        <div class="card border-1 scrollable-table">
                            <table class="table table-striped-columns">
                                <thead>
                                <tr>
                                    <th scope="col" style="width: 4%">No</th>
                                    <th scope="col" style="width: 15%">Full name</th>
                                    <th scope="col" style="width: 10%">Roll number</th>
                                    <th scope="col" style="width: 25%">Email</th>
                                    <th scope="col" style="width: 15%">Phone number</th>
                                    <th scope="col" style="width: 25%">Address</th>
                                    <th scope="col" style="width: 6%">Gender</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Hàm Lợn Lee</td>
                                    <td>QT123456</td>
                                    <td>LeeeLQT123456@fpt.edu.vn</td>
                                    <td>0123456789</td>
                                    <td>Ha Noi</td>
                                    <td>Male</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
</body>
</html>
