package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "VerifyOtpServlet", value = "/verify-otp")
public class VerifyOtpServlet extends HttpServlet {

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
        request.getRequestDispatcher(request.getContextPath() + "view/verifyotp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String otpInput = request.getParameter("otpInput");
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        if (!otpInput.equals(verifyCode)) {
            request.setAttribute("verifyOtpMessage", "Wrong OTP code!");
            request.getRequestDispatcher("/view/verifyotp.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("verifyCode");
            response.sendRedirect("/resetPassword");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
