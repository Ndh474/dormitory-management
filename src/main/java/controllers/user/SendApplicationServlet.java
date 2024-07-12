package controllers.user;


import entity.Account;
import entity.Information;
import entity.Request;
import entity.RequestType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Part;
import services.InformationService;
import services.RequestService;
import services.RequestTypeService;
import services.impl.InformationServiceImpl;
import services.impl.RequestServiceImpl;
import services.impl.RequestTypeServiceImpl;
import util.UploadImage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;

@WebServlet(name = "SendApplicationServlet", value = "/send-application")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*50)
public class SendApplicationServlet extends HttpServlet {

    private final RequestTypeService requestTypeService = new RequestTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());
        request.setAttribute("information", information);
        refreshData(request);
        request.getRequestDispatcher("/view/user/send-application.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
       String title = request.getParameter("title");
       String content = request.getParameter("content");



        // Kiểm tra xem title và content có phải là null hoặc rỗng không
        if (title.trim().isEmpty() && content.trim().isEmpty()) {
            request.setAttribute("status", "failed");
            refreshData(request);
            request.getRequestDispatcher("/view/user/send-application.jsp").forward(request, response);
            return;
        } else if (title.trim().isEmpty()) {
            request.setAttribute("status", "failed");
            refreshData(request);
            request.getRequestDispatcher("/view/user/send-application.jsp").forward(request, response);
            return;
        } else if (content == null || content.trim().isEmpty()) {
            refreshData(request);
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("/view/user/send-application.jsp").forward(request, response);
            return;
        }
        //Lấy requestType
        int requestTypeId = Integer.parseInt(request.getParameter("requestTypeId"));
        RequestType requestType = requestTypeService.getByRequestTypeId(requestTypeId);
        //Lấy thời gian người dùng tạo request
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        // up ảnh vào folder application-images
        Part image = request.getPart("image");
        String fileName = null;
        if(image.getSize()>0){
            fileName = account.getEmail().split("@")[0]+"_"+requestType.getRequestTypeId()+"_"+ System.currentTimeMillis() +"."+getFileType(image);
            String uploadPath = getServletContext().getRealPath("/") + "application-images";
            UploadImage.upload(image,uploadPath+"/"+fileName);
        }

        //save request to db
        RequestService requestService = new RequestServiceImpl();
        requestService.add(new Request(title, content, "", formattedDateTime, "in processing", requestType, account,fileName));
        request.setAttribute("status", "success");
        refreshData(request);
        request.getRequestDispatcher("/view/user/send-application.jsp").forward(request, response);

    }
    private String getFileType(Part part){
        String []fileType = part.getContentType().split("/");
        return fileType[1];
    }
    private void refreshData(HttpServletRequest request) {
        List<RequestType> requestTypeList = requestTypeService.getAll();
        request.setAttribute("requestTypeList", requestTypeList);
    }

}
