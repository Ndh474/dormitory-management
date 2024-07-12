package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entity.Account;
import entity.AdminNotification;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AdminNotificationService;
import services.InformationService;
import services.impl.AdminNotificationServiceImpl;
import services.impl.InformationServiceImpl;

@WebServlet(name = "UserNotificationServlet", value = "/user-notification")
public class UserNotificationServlet extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());

        AdminNotificationService adminNotificationService = new AdminNotificationServiceImpl();
        List<AdminNotification> notificationList = adminNotificationService.getByInformationId(information.getInformationId());
        request.setAttribute("notificationList", notificationList);


        request.getRequestDispatcher("view/user/user-notification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
