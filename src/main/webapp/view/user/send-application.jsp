<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/3/2024
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.RequestType" %>
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .table_l, .table_r {
            padding-bottom: 10px;
            color: black;
            font-size: 17px;
        }

        .table_l {
            font-weight: 600;
        }

        p {
            font-size: smaller;
            color: red;
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
                    <h2 class="fw-bold">Send application</h2>
                    <p>Lưu ý: V/v gửi đơn/email đến các phòng ban
                        Bộ phận xử lý đơn sẽ trả lời đơn/ email của sinh viên trong vòng 48h.</p>

                    <p>Để hạn chế SPAM, sẽ giãn thời gian trả lời đơn/email có tính chất SPAM theo nguyên tắc: Khi sinh
                        viên gửi N đơn/email (N>1) cho cùng một yêu cầu thì thời gian trả lời trong vòng Nx48h.</p>

                    <p>Vì vậy sinh viên cần cân nhắc trước khi gửi đơn/email với cùng một nội dung để nhận được trả
                        lời/giải quyết nhanh nhất theo quy định.</p>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="card border-1">
                                <div class="card-body py-1">
                                    <!-- send application form -->
                                    <form action="${pageContext.request.contextPath}/send-application" method="post" enctype="multipart/form-data" id="send-application-form">
                                        <table class="mt-4">
                                            <td class="table_l">Application type</td>
                                            <td class="table_r">
                                                <div>
                                                    <select name="requestTypeId" id="select-requestType">
                                                        <c:forEach items="${requestScope.requestTypeList}"
                                                                   var="requestType">
                                                            <option value="${requestType.requestTypeId}">
                                                                    ${requestType.requestTypeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </td>
                                        </table>
                                        <table class="mt-4">
                                            <td class="table_l">Title</td>
                                            <td class="table_r">
                                                <div>
                                                    <input type="text" name="title" class="form-control"
                                                           style="width: 50rem">
                                                </div>
                                            </td>
                                        </table>
                                        <table class="mt-4">
                                            <td class="table_l">Content</td>
                                            <td class="table_r">
                                                <div>
                                                    <textarea class="form-control" rows="5" cols="103"
                                                              name="content"></textarea>
                                                </div>
                                            </td>
                                        </table>
                                        <table class="mt-4" id="select-image-field">
                                            <td class="table_l">Select image</td>
                                            <td class="table_r">
                                                <div style="display: flex;justify-content: space-between;align-items: center;">
                                                    <i class="lni lni-image" id="select-image-icon" onclick="openFileChooser()" style="font-size: 60px ; font-weight: normal;display:block;cursor: pointer ; margin-right: 50px;"></i>
                                                    <input type="file" name="image" id="select-image-input" onclick="loadImage()" style="display: none" accept="image/*">
                                                    <div style="position: relative" >
                                                        <img id="previewImage" src="" alt="Image preview" style="display:none; width: 150px; border: 1px solid #ddd; padding: 5px; border-radius: 5px;">
                                                        <i class="lni lni-close" id="remove-image-icon" style="cursor:pointer;display:none;position: absolute;top: 0;right: 0;transform: translate(50%,-50%);background-color: #959595;border-radius: 50%;font-size: 10px;padding: 5px;)"></i>
                                                    </div>

                                                </div>
                                            </td>
                                        </table>
                                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                            <c:choose>
                                                <c:when test="${requestScope.information.getRoomName()!=null}">
                                                    <button type="submit" class="btn btn-success mt-4 me-md-2">
                                                        <div class="d-flex justify-content-between">
                                                            <i class="lni lni-checkmark-circle"
                                                               style="font-size: 25px; padding-right: 5px"></i>
                                                            <div>Send</div>
                                                        </div>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: red">You need to have room to submit your application </span>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hamburger.js"></script>
<script type="text/javascript">
    document.getElementById("send-application-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to send this application?',
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

    var status = "${requestScope.status}";
    if (status === "success") {
        Swal.fire({
            icon: "success",
            title: "Successfully",
            text: "Request submitted successfully",
        }).then((result) => {
            if (result.isConfirmed || result.isDismissed) {
                window.location.href = window.location.href;
            }
        });
    } else if (status === "failed") {
        Swal.fire({
            icon: "error",
            title: "Failed",
            text: "Request submission failed! Title or content must not be empty.",
        }).then((result) => {
            if (result.isConfirmed || result.isDismissed) {
                window.location.href = window.location.href;
            }
        });
    }

    function openFileChooser() {
         document.getElementById("select-image-input").click();
    }
    function loadImage(){

    }
    document.getElementById('select-image-input').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {

                const previewImage = document.getElementById('previewImage');
                const remove = document.getElementById('remove-image-icon');
                previewImage.src = e.target.result;
                previewImage.style.display = 'block';
                remove.style.display='block';
            }
            reader.readAsDataURL(file);
        }
    });
        document.getElementById('remove-image-icon').addEventListener('click', function() {
        document.getElementById('select-image-input').value = '';
        document.getElementById('previewImage').style.display = 'none';
        document.getElementById('remove-image-icon').style.display = 'none';
        document.getElementById('previewImage').src = '';
    });
    const select = document.getElementById('select-requestType');
    select.onchange = ()=>{
        if(select.value =='3'){
            document.getElementById('select-image-field').classList.add('mt-4');
            document.getElementById('select-image-field').classList.remove('hidden');
        }
        else{
            document.getElementById('select-image-field').classList.remove('mt-4');
            document.getElementById('select-image-field').classList.add('hidden');
        }
    }
</script>
</body>
</html>
