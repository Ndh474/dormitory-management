package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.impl.AccountServiceImpl;
import util.SendOtp;

import javax.mail.MessagingException;

@WebServlet(name = "EmailForgotPasswordServlet", value = "/email-forgot-password")
public class EmailForgotPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertBrandServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertBrandServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/view/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        AccountServiceImpl accountService = new AccountServiceImpl();
        String emailTo = request.getParameter("emailTo");
        if (!accountService.checkEmailExist(emailTo)) {
            request.setAttribute("emailTo", emailTo);
            request.setAttribute("forgotPasswordMessage", "Email does not exist!");
            request.getRequestDispatcher("view/forgotpassword.jsp").forward(request, response);
            return;
        }
        String verifyCode = SendOtp.generateOTP();
        try {
            SendOtp.sendOtpCode(emailTo, "Reset your password", "Your OTP is: " + verifyCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("verifyCode", verifyCode);
        request.getSession().setAttribute("emailTo", emailTo);
        response.sendRedirect("/verifyOtp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
