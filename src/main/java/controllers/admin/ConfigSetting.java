package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.AppConfig;
import util.Notification;
import util.RoomAndUtilityIssue;
import util.RoomAndUtilityPayment;

@WebServlet(name = "ConfigSetting", value = "/config-setting")
public class ConfigSetting extends HttpServlet {

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
        request.setAttribute("roomAndUtilityBilIssueDay", AppConfig.roomAndUtilityBilIssueDay);
        request.setAttribute("roomAndUtilityPaymentDay", AppConfig.roomAndUtilityPaymentDay);

        request.setAttribute("notificationDay", AppConfig.notificationDay);

        request.setAttribute("bookRoom", AppConfig.bookRoom);

        request.setAttribute("electricityPrice", AppConfig.electricityPrice);
        request.setAttribute("waterPrice", AppConfig.waterPrice);

        request.getRequestDispatcher("view/admin/admin-setting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int oldRoomAndUtilityBilIssueDay = AppConfig.roomAndUtilityBilIssueDay;
        int oldRoomAndUtilityPaymentDay = AppConfig.roomAndUtilityPaymentDay;
        int oldNotificationDay = AppConfig.notificationDay;

        AppConfig.roomAndUtilityBilIssueDay = Integer.parseInt(request.getParameter("roomAndUtilityBilIssueDay"));
        AppConfig.roomAndUtilityPaymentDay = Integer.parseInt(request.getParameter("roomAndUtilityPaymentDay"));

        AppConfig.notificationDay = Integer.parseInt(request.getParameter("notificationDay"));

        AppConfig.bookRoom = Boolean.parseBoolean(request.getParameter("bookRoom"));

        AppConfig.electricityPrice = Double.parseDouble(request.getParameter("electricityPrice").replace(".",""));
        AppConfig.waterPrice = Double.parseDouble(request.getParameter("waterPrice").replace(".",""));

        if (AppConfig.roomAndUtilityBilIssueDay != oldRoomAndUtilityBilIssueDay) {
            RoomAndUtilityIssue.scheduleTask(AppConfig.roomAndUtilityBilIssueDay);
        }
        if (AppConfig.roomAndUtilityPaymentDay != oldRoomAndUtilityPaymentDay) {
            RoomAndUtilityPayment.scheduleTask(AppConfig.roomAndUtilityPaymentDay);
        }
        if (AppConfig.notificationDay != oldNotificationDay) {
            Notification.scheduleTask(AppConfig.notificationDay);
        }
        request.getSession().setAttribute("successChangeSetting", "success");
        response.sendRedirect(request.getContextPath() + "/config-setting");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
