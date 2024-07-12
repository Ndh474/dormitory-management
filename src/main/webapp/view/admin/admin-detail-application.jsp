<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Application</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/user-style.css">
    <style>

        .overlay-image {
            background-color: rgba(0, 0, 0, 0.5);
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

        .hidden {
            display: none;
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
                        <form action="${pageContext.request.contextPath}/admin-detail-application" id="response-form"
                              method="post">
                            <h3 style="color: green">Student information</h3>
                            <div class="d-flex" style="margin-top: 1rem">
                                <div class="d-flex">
                                    <table>
                                        <tr>
                                            <td class="table_l">Roll Number:</td>
                                            <td class="table_r"><a href="${pageContext.request.contextPath}/admin-update-student?rollNumber=${requestScope.information.getRollNumber()}">${requestScope.information.getRollNumber()}</a></td>
                                        </tr>
                                        <tr>
                                            <td class="table_l">Full name:</td>
                                            <td class="table_r">${requestScope.information.getFullName()}</td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr>
                                            <td class="table_l">Gender:</td>
                                            <td class="table_r">${requestScope.information.getGender()}</td>
                                        </tr>
                                        <tr>
                                            <td class="table_l">Room:</td>
                                            <td class="table_r"><a href="${pageContext.request.contextPath}/admin-detail-room?roomName=${requestScope.information.getRoomName()}">${requestScope.information.getRoomName()}</a></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <h4 style="color: green">Detail application</h4>
                            <input type="text" name="requestId" value="${requestScope.requestDetail.getRequestId()}"
                                   hidden>
                            <table class="mt-2">
                                <td class="table_l">Application type:</td>
                                <td class="table_r">${requestScope.requestDetail.getRequestType().getRequestTypeName()}</td>
                            </table>

                            <table class="mt-2">
                                <td class="table_l">Title:</td>
                                <td class="table_r">${requestScope.requestDetail.getTitle()}</td>
                            </table>
                            <table class="mt-2">
                                <td class="table_l">Content:</td>
                                <td class="table_r">${requestScope.requestDetail.getContent()}</td>
                            </table>

                            <table class="mt-2">
                                <td class="table_l">Image:</td>
                                <td class="table_r">
                                    <img src="${pageContext.request.contextPath}/application-images/${requestDetail.getImage()}"
                                         onclick="showImage('${pageContext.request.contextPath}/application-images/${requestDetail.getImage()}')"
                                         alt="" style="max-height: 100px;cursor:pointer;width: auto;">

                                </td>
                            </table>

                            <table class="mt-2">
                                <td class="table_l">Response</td>
                                <td class="table_r"><textarea name="responseContent" class="form-control"
                                                              style="height: 7rem; width: 50rem">${requestScope.requestDetail.getResponse()}</textarea>
                                </td>
                            </table>

                            <table class="mt-2">
                                <td class="table_l">Status</td>
                                <td class="table_r">
                                    <div>
                                        <select name="requestStatus">
                                            <option value="in processing" ${requestScope.requestDetail.getStatus().equals("in processing")?"selected":""}>
                                                In Processing
                                            </option>
                                            <option value="processed" ${requestScope.requestDetail.getStatus().equals("processed")?"selected":""}>
                                                Processed
                                            </option>
                                        </select>
                                    </div>
                                </td>
                            </table>

                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/admin-application"
                                   class="btn btn-warning mt-2">
                                    <div class="d-flex justify-content-between">
                                        <i class="lni lni-reply"
                                           style="font-size: 25px; padding-right: 5px"></i>
                                        <div>Back to list</div>
                                    </div>
                                </a><br>
                                <div>
                                    <button type="submit" class="btn btn-success mt-2">
                                        <div class="d-flex justify-content-between">
                                            <i class="lni lni-checkmark-circle"
                                               style="font-size: 25px; padding-right: 5px"></i>
                                            <div>Send</div>
                                        </div>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>

    </div>
    <div id="overlay-image" class="hidden">
        <div class="image-container">
            <span class="close-icon" onclick="closeOverlay()">&times;</span>
            <img id="application-image" class="full-image"
                 src="${pageContext.request.contextPath}/application-images/${requestScope.requestDetail.image}"
                 alt="application image">
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../../js/hamburger.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>

    document.getElementById("response-form").addEventListener("submit", function (event) {
        event.preventDefault(); // prevent the form from submitting immediately
        Swal.fire({
            title: 'Confirm',
            text: 'Send response to student?',
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

    const successStatus = "${sessionScope.successSendResponse}";
    if (successStatus === "success") {
        ${ sessionScope.remove("successSendResponse") }
        Swal.fire({
            icon: "success",
            title: "Success",
            text: "Response has been sent to student!",
            footer: '<a href="${pageContext.request.contextPath}/admin-application" style="color: blue; font-weight: bold; text-decoration: underline;">Go back to application list</a>'
        });

    }

    function showImage(url) {
        const overlay = document.getElementById('overlay-image');
        const image = document.getElementById('application-image');
        overlay.classList.remove('hidden')
        overlay.classList.add('overlay-image')
        image.src = url;
    }

    function closeOverlay() {
        const overlay = document.getElementById('overlay-image');
        const image = document.getElementById('application-image');
        overlay.classList.add('hidden')
        overlay.classList.remove('overlay-image')
        image.src = '';
    }
</script>
</body>
</html>
