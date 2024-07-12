<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/28/2024
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book room</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>
        .select_style {
            margin-left: 10px;
        }

        .select_style select {
            height: 26px;
            background: white;
            border: 1px solid #198754;
            width: 200px;
        }

        .scrollable-table {
            max-height: 547px;
            overflow: auto;
        }

        a {
            cursor: pointer;
        }

        .table_l, .table_r {
            padding-bottom: 10px;
            color: black;
            font-size: 17px;
        }

        .table_l {
            font-weight: 600;
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
                    <h2 class="fw-bold">Booking room</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card border-1">
                                <div class="card-body py-1">
                                    <!--Choose type room form -->
                                    <form action="">
                                        <div class="d-flex justify-content-between mt-4">
                                            <div class="d-flex">
                                                <p>Type of room</p>
                                                <div class="select_style">
                                                    <select>
                                                        <option>All</option>
                                                        <option>3 beds - 1.150.000 VND</option>
                                                        <option>4 beds - 1.050.000 VND</option>
                                                        <option>6 beds - 850.000 VND</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="d-flex">
                                                <p>Dorm </p>
                                                <div class="select_style">
                                                    <select>
                                                        <option>All</option>
                                                        <option>Dorm A</option>
                                                        <option>Dorm B</option>
                                                        <option>Dorm C</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="d-flex">
                                                <p>Floor </p>
                                                <div class="select_style">
                                                    <select>
                                                        <option>All</option>
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div>
                                                <button type="submit" class="btn btn-success"> Search</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <h5 class="fw-bold mt-4">Available room</h5>
                            <!-- thay tuùy vaào giới tinh student -->
                            <p style="color: red; font-size: small; margin: 0">*Male only</p>
                            <p style="color: red; font-size: small; margin: 0">*Female only</p>
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table">
                                    <form>
                                        <table class="table table-striped-columns text-center">
                                            <tr>
                                                <th>No</th>
                                                <th>Room type</th>
                                                <th>Room name</th>
                                                <th>Dorm</th>
                                                <th>Floor</th>
                                                <th>Total bed</th>
                                                <th>Used bed</th>
                                                <th>Free bed</th>
                                                <th>Detail</th>
                                                <th></th>
                                            </tr>
                                            <tr>
                                                <td>1</td>
                                                <td>6 beds</td>
                                                <td>A101</td>
                                                <td>A</td>
                                                <td>1</td>
                                                <td>6</td>
                                                <td>3</td>
                                                <td>3</td>
                                                <td>
                                                    <a style="color: blue" href="user-detail-room.jsp">Detail</a>
                                                </td>
                                                <td>
                                                    <a style="color: blue" data-bs-toggle="modal"
                                                       data-bs-target="#confirmRoomModal">Select</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- list detail student in this room -->
<%--<div id="detailRoomModal" class="modal fade">--%>
<%--    <div class="modal-dialog modal-xl">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-body">--%>
<%--                <div class="form-group">--%>
<%--                    <div style="border-bottom: 0" class="modal-header">--%>
<%--                        <h5 style="color: green">List of student</h5>--%>
<%--                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                    </div>--%>
<%--                    <div class="col-12 col-md-12">--%>
<%--                        <div class="card border-1 scrollable-table">--%>
<%--                            <table class="table table-striped-columns">--%>
<%--                                <thead>--%>
<%--                                <tr>--%>
<%--                                    <th scope="col" style="width: 4%">No</th>--%>
<%--                                    <th scope="col" style="width: 25%">Full name</th>--%>
<%--                                    <th scope="col" style="width: 25%">Email</th>--%>
<%--                                    <th scope="col" style="width: 10%">Roll number</th>--%>
<%--                                    <th scope="col" style="width: 25%">Address</th>--%>
<%--                                    <th scope="col" style="width: 6%">Gender</th>--%>
<%--                                </tr>--%>
<%--                                </thead>--%>
<%--                                <tbody>--%>
<%--                                <tr>--%>
<%--                                    <td>1</td>--%>
<%--                                    <td>Hàm Lợn Lee</td>--%>
<%--                                    <td>LeeeLQT123456@fpt.edu.vn</td>--%>
<%--                                    <td>QT123456</td>--%>
<%--                                    <td>Ha Noi</td>--%>
<%--                                    <td>Male</td>--%>
<%--                                </tr>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!--confirm and payment room -->
<div id="confirmRoomModal" class="modal fade">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-group">
                    <div style="border-bottom: 0" class="modal-header">
                        <h5 style="color: green"> Confirm and payment</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-8">
                            <!-- insert confirm and payment form here -->
                            <form>
                                <div class="card border-1 scrollable-table">
                                    <table class="table table-striped">
                                        <tr>
                                            <td class="table_l">Room type:</td>
                                            <td class="table_r">3 bed - 1.150.000VND</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l">Dorm:</td>
                                            <td class="table_r">A</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l">Floor:</td>
                                            <td class="table_r">1</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l">Room name:</td>
                                            <td class="table_r">A101</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="form-floating mt-3">
                                        <textarea class="form-control" placeholder="Note here" id="floatingTextarea2"
                                                  style="height: 94px"></textarea>
                                    <label for="floatingTextarea2">Note:</label>
                                </div>
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-success mt-4 me-md-2">
                                        <div class="d-flex justify-content-between">
                                            <i class="lni lni-checkmark-circle"
                                               style="font-size: 25px; padding-right: 5px"></i>
                                            <div>Confirm</div>
                                        </div>
                                    </button>
                                </div>
                                <p style="color: red; font-size: 12px; text-align: end">*Please check carefully before deciding to confirm</p>
                            </form>
                        </div>
                        <div class="col-md-4">
                            <div class="card border-1">
                                <div class="card-body py-1 scrollable-table">
                                    <h4 style="color: green; margin-top: 10px">Your balance</h4><hr>
                                    <h6>Your account Balance</h6>
                                    <p>5.000.000 VND</p>
                                    <h6>The total amount payable</h6>
                                    <p>4.600.000 VND</p>
                                    <h6>Your Balance after booking</h6>
                                    <p>400.000 VND</p>
                                </div>
                            </div>
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
