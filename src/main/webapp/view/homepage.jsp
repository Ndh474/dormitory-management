<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 5/15/2024
  Time: 11:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Home</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap 5.2.3)-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <!-- Add cdn BS5.2.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .btn-xl {
            padding: 1.25rem 2.5rem;
            font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            font-size: 1.125rem;
            font-weight: 700;
        }
    </style>
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="#page-top"><img style="width: 135px; height: 45px; border-radius: 0.5rem; "
                                                      src="${pageContext.request.contextPath}/images/logo-fpt-dorm.png" alt="..."/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                <li class="nav-item"><a class="nav-link" href="#about">Thông tin</a></li>
                <li class="nav-item"><a class="nav-link" href="#faq">FAQ</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Big banner -->
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img style="height: 90%" src="../images/bg2.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <a class="btn btn-success btn-xl text-uppercase" href="#services">Ký túc xá Đại học FPT</a>
                <p>Không gian sống xanh </p>
            </div>
        </div>
        <div class="carousel-item">
            <img style="height: 90%" src="../images/bg3.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <a class="btn btn-success btn-xl text-uppercase" href="#services">Ký túc xá Đại học FPT</a>
                <p>Đầy đủ điện nghi</p>
            </div>
        </div>
        <div class="carousel-item">
            <img style="height: 90%" src="../images/bg5.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <a class="btn btn-success btn-xl text-uppercase" href="#services">Ký túc xá Đại học FPT</a>
                <p>Văn minh lịch sự</p>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>

<!-- Services-->
<section class="page-section" id="services">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase fw-bold">Kênh thông tin</h2>
            <h3 class="section-subheading text-muted">Ký túc xá Đại học FPT</h3>
        </div>
        <div class="row text-center">
            <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-warning"></i>
                            <i class="fas fa-regular fa-bell fa-stack-1x fa-inverse"></i>
                        </span>
                <a style="color: black" href="#about"><h4 class="my-3">Thông tin KTX Đại học FPT</h4></a>
            </div>
            <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-warning"></i>
                            <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                        </span>
                <a style="color: black" href="${pageContext.request.contextPath}/login"><h4 class="my-3">Đăng ký sử dụng
                    KTX</h4></a>
            </div>
            <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-warning"></i>
                            <i class="fas fa-solid fa-question fa-stack-1x fa-inverse"></i>
                        </span>
                <a style="color: black" href="#faq"><h4 class="my-3">Các câu hỏi thường gặp</h4></a>
            </div>
        </div>
    </div>
</section>
<!-- About-->
<section class="page-section" id="about">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase fw-bold">Thông tin KTX Đại học FPT</h2>
            <h3 class="section-subheading text-muted"></h3>
        </div>
        <ul class="timeline">
            <li>
                <div class="timeline-image"><img style="height: 158px;width: 170px" class="rounded-circle img-fluid"
                                                 src="../images/bg1.jpg" alt="..."/></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                    </div>
                    <div class="timeline-body"><p class="text-muted">Bằng việc đầu tư, xây dựng khu Ký túc xá xịn sò.
                        Đầy đủ trang thiết bị cần thiết, không gian thoáng mát, sạch sẽ. Để đáp ứng nhu cầu và tạo không
                        gian học tập, sinh hoạt thoải mái nhất cho sinh viên. KTX Đại học FPT cũng được xem như ngôi nhà
                        thứ 2 của nhiều sinh viên.</p></div>
                </div>
            </li>
            <li class="timeline-inverted">
                <div class="timeline-image"><img style="height: 158px;width: 170px" class="rounded-circle img-fluid"
                                                 src="../images/bg3.jpg" alt="..."/></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                    </div>
                    <div class="timeline-body"><p class="text-muted">Hiện nay, một vấn đề các bạn tân sinh viên sau khi
                        biết kết quả trúng tuyển Đại học. Đó là tìm kiếm cho mình một chỗ ở phù hợp, vừa tiết kiệm vừa
                        đảm bảo an ninh, môi trường học tập. Không chỉ các tân sinh viên mà các bạn sinh viên các khóa
                        trước
                        hầu hết cũng đều mong muốn ở có KTX gần trường để thuận lợi cho việc di chuyển.
                        Và để tiết kiệm chi phí, có một trường tốt nhất để học tập và sinh hoạt.</p></div>
                </div>
            </li>
            <li>
                <div class="timeline-image"><img style="height: 158px;width: 170px" class="rounded-circle img-fluid"
                                                 src="../images/room5.jpg" alt="..."/></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                    </div>
                    <div class="timeline-body"><p class="text-muted">Ký túc xá Đại học FPT được xây dựng với thiết kế
                        hiện đại,
                        thoáng mát và đầy đủ tiện nghi. Khu KTX gồm các tòa nhà. Mỗi tòa KTX có các tầng rộng rãi, sạch
                        sẽ, có cả wifi,
                        máy bán nước tự động, máy giặt sấy tự động... Xung quanh còn là cây cối xanh mướt trong lành, dễ
                        chịu, thoáng mát.
                        Phòng ở được thiết kế hiện đại, không gian thoải mái, thiết kế phù hợp cho từng loại phòng
                        3-4-6 người. </p></div>
                </div>
            </li>
            <li class="timeline-inverted">
                <div class="timeline-image"><img style="height: 158px;width: 170px" class="rounded-circle img-fluid"
                                                 src="../images/room6.jpg" alt="..."/></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                    </div>
                    <div class="timeline-body"><p class="text-muted">Mỗi phòng sẽ được trang bị các thiết bị cần thiết,
                        đầy đủ phục vụ cho
                        những nhu cầu thiết yếu của sinh viên như giường tầng, bàn học, giá phơi quần áo, bình nóng
                        lạnh, điều hòa, tủ để giày,
                        nhà vệ sinh riêng cho mỗi phòng… giúp sinh viên an tâm học tập trong quãng thời gian gắn bó với
                        đại học, đem đến cho
                        sinh viên cảm giác thoải mái tiện nghi như ở nhà.</p></div>
                </div>
            </li>
        </ul>
    </div>
</section>
<!-- FAQ Grid-->
<section class="page-section bg-light" id="faq">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase fw-bold">FAQ</h2>
        </div>
        <div class="row">
            <div class="col-lg-4 col-sm-6 mb-4">
                <!-- Portfolio item 1-->
                <div class="portfolio-item">
                    <div class="portfolio-caption">
                        <div class="portfolio-caption-subheading text-muted"><a class="portfolio-link text-warning"
                                                                                data-bs-toggle="modal"
                                                                                href="#portfolioModal1">1. Khi ở KTX cần
                            lưu ý điều gì?</a></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6 mb-4">
                <!-- Portfolio item 2-->
                <div class="portfolio-item">
                    <div class="portfolio-caption">
                        <div class="portfolio-caption-subheading text-muted"><a class="portfolio-link text-warning"
                                                                                data-bs-toggle="modal"
                                                                                href="#portfolioModal2">2. Điểm uy tín
                            là gì?</a></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6 mb-4">
                <!-- Portfolio item 3-->
                <div class="portfolio-item">
                    <div class="portfolio-caption">
                        <div class="portfolio-caption-subheading text-muted"><a class="portfolio-link text-warning"
                                                                                data-bs-toggle="modal"
                                                                                href="#portfolioModal3">3. Làm thế nào
                            để gửi yêu cầu tới Ban Quản lý KTX?</a></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
                <!-- Portfolio item 4-->
                <div class="portfolio-item">
                    <div class="portfolio-caption">
                        <div class="portfolio-caption-subheading text-muted"><a class="portfolio-link text-warning"
                                                                                data-bs-toggle="modal"
                                                                                href="#portfolioModal4">4. Làm thế nào
                            để báo cáo sửa chữa đồ dùng trong phòng?</a></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
                <!-- Portfolio item 5-->
                <div class="portfolio-item">
                    <div class="portfolio-caption">
                        <div class="portfolio-caption-subheading text-muted"><a class="portfolio-link text-warning"
                                                                                data-bs-toggle="modal"
                                                                                href="#portfolioModal5">5. Thông tin
                            liên lạc của bảo vệ và y tế là gì?</a></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="portfolio-caption-subheading text-muted"><a class="text-warning" href="#about">6. Thông tin
                    KTX Đại học FPT</a></div>
            </div>
        </div>
    </div>
</section>
<!-- Footer-->
<footer class="footer py-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-4 text-lg-start">Copyright &copy; FPTU 2024</div>
            <div class="col-lg-4 my-3 my-lg-0">
                <a class="btn btn-dark btn-social mx-2" href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#" aria-label="LinkedIn"><i
                        class="fab fa-linkedin-in"></i></a>
            </div>
            <div class="col-lg-4 text-lg-end">
                <a class="link-dark text-decoration-none me-3" href="#">Privacy Policy</a>
                <a class="link-dark text-decoration-none" href="#">Terms of Use</a>
            </div>
        </div>
    </div>
</footer>
<!-- Portfolio Modals-->
<!-- Portfolio item 1 modal popup-->
<div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="modal-body">
                            <!-- Project details-->
                            <h3 class="item-intro ">Ký túc xá có một số điều cần lưu ý khi ở như sau:</h3>
                            <ul style="text-align: start" class="list-inline">
                                <li>- Không được nuôi vật nuôi, thú cưng (chó, mèo,...).</li>
                                <li>- Không được uống rượu, bia, chơi cờ bạc, sử dụng các chất kích thích và chất cấm.
                                </li>
                                <li>- Không được nấu ăn trong ký túc xá.</li>
                                <li>- Không được đưa người lạ không ở trong ký túc xá vào phòng sau giờ giới nghiêm.
                                </li>
                                <li>- Giờ giới nghiêm trong ký túc xá là sau 10 giờ 30 phút tối.</li>
                                <li>- Giữ gìn vệ sinh chung và đổ rác trước 9 giờ sáng.</li>
                            </ul>
                            <p style="color: darkred">Tất cả các lỗi vi phạm đều bị trừ dựa trên điểm uy tín dựa trên
                                mức độ lỗi vi phạm.</p>
                            <button class="btn btn-warning btn-lg text-uppercase text-white" data-bs-dismiss="modal"
                                    type="button">
                                <i class="fas fa-xmark me-1"></i>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Portfolio item 2 modal popup-->
<div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="modal-body">
                            <!-- Project details-->
                            <h3 class="item-intro ">Điểm uy tín (Credibility in FPT Dormitory - CFD score) là một trong
                                những yếu tố để tạo ra môi trường KTX văn minh và lành mạnh hơn</h3>
                            <ul style="text-align: start" class="list-inline">
                                <li>- Điểm uy tín là tiêu chí để đánh giá ý thức của sinh viên khi sử dụng dịch vụ ký
                                    túc xá.
                                </li>
                                <li>- Điểm uy tín thay đổi dựa theo những hành vi, hoạt động và sự đóng góp của sinh
                                    viên trong suốt thời gian ở ký túc xá.
                                </li>
                                <li>- Điểm uy tín sẽ được tăng, giảm tương ứng theo các quy định đã được đề ra trong nội
                                    quy KTX.
                                </li>
                                <li>- Điểm uy tín là một trong những tiêu chí được dùng để xét duyệt xem sinh viên có
                                    được sử dụng ký túc xá trong kỳ hay không.
                                </li>
                            </ul>
                            <button class="btn btn-warning btn-lg text-uppercase text-white" data-bs-dismiss="modal"
                                    type="button">
                                <i class="fas fa-xmark me-1"></i>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Portfolio item 3 modal popup-->
<div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="modal-body">
                            <!-- Project details-->
                            <h3 class="item-intro ">Làm thế nào để gửi yêu cầu tới Ban Quản lý KTX?</h3>
                            <ul style="text-align: start" class="list-inline">
                                <li>Bước 1: Vào chức năng My request.</li>
                                <li>Bước 2: Bấm vào nút Create new request -> Chọn loại yêu cầu (Type request) thích
                                    hợp.
                                </li>
                                <li>Bước 3: Điền nội dung của yêu cầu ở phần Content.</li>
                                <li>Bước 4: Bấm vào nút Create request.</li>
                            </ul>
                            <button class="btn btn-warning btn-lg text-uppercase text-white" data-bs-dismiss="modal"
                                    type="button">
                                <i class="fas fa-xmark me-1"></i>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Portfolio item 4 modal popup-->
<div class="portfolio-modal modal fade" id="portfolioModal4" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="modal-body">
                            <!-- Project details-->
                            <h3 class="item-intro "> Làm thế nào để báo cáo sửa chữa đồ dùng trong phòng?</h3>
                            <ul style="text-align: start" class="list-inline">
                                <li>Bước 1: Vào chức năng My request</li>
                                <li>Bước 2: Bấm vào nút Create new request -> Chọn Báo cáo vấn đề kỹ thuật ở mục Type
                                    request
                                </li>
                                <li>Bước 3: Hệ thống sẽ dẫn tới trang https://cim.fpt.edu.vn/</li>
                                <li>Bước 4: Điền những thông tin cần thiết và gửi ảnh tình trạng thiết bị (trên hệ thống
                                    CIM)
                                </li>
                                <li>Bước 5: Bấm vào nút Create (trên hệ thống CIM)</li>
                            </ul>
                            <button class="btn btn-warning btn-lg text-uppercase text-white" data-bs-dismiss="modal"
                                    type="button">
                                <i class="fas fa-xmark me-1"></i>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Portfolio item 5 modal popup-->
<div class="portfolio-modal modal fade" id="portfolioModal5" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="modal-body">
                            <!-- Project details-->
                            <h3 class="item-intro ">Thông tin liên lạc của phòng bảo vệ và phòng y tế (24/7):</h3>
                            <ul style="text-align: start" class="list-inline">
                                <li>- Phòng bảo vệ:(024) 668 05913</li>
                                <li>- Phòng y tế:(024) 668 05917</li>
                            </ul>
                            <button class="btn btn-warning btn-lg text-uppercase text-white" data-bs-dismiss="modal"
                                    type="button">
                                <i class="fas fa-xmark me-1"></i>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
