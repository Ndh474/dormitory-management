package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;



public class imageDemo extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator +"test-image";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Lấy phần file từ request
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        String filePath = uploadPath + File.separator + fileName;

        // Ghi file lên server
        filePart.write(filePath);

        // Trả về kết quả
        request.setAttribute("message", "File uploaded successfully! File path: " + filePath);
        PrintWriter out = response.getWriter();
        request.setAttribute("path",fileName);
        request.getRequestDispatcher("/view/send-image.jsp").forward(request, response);
    }
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
