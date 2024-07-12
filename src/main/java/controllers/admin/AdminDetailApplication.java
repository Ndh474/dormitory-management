package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Information;
import entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.InformationService;
import services.RequestService;
import services.impl.InformationServiceImpl;
import services.impl.RequestServiceImpl;

@WebServlet(name = "AdminDetailApplication", value = "/admin-detail-application")
public class AdminDetailApplication extends HttpServlet {

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
        String requestId = request.getParameter("requestId");

        RequestService requestService = new RequestServiceImpl();
        Request requestDetail = requestService.getById(Integer.parseInt(requestId));
        request.setAttribute("requestDetail", requestDetail);

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(requestDetail.getAccount().getAccountID());
        request.setAttribute("information", information);

        request.getRequestDispatcher("view/admin/admin-detail-application.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String requestId = request.getParameter("requestId");
        String responseContent = request.getParameter("responseContent");
        String requestStatus = request.getParameter("requestStatus");
        RequestService requestService = new RequestServiceImpl();
        Request requestDetail = requestService.getById(Integer.parseInt(requestId));
        requestDetail.setResponse(responseContent);
        requestDetail.setStatus(requestStatus);
        requestService.update(requestDetail);

        request.getSession().setAttribute("successSendResponse", "success");
        response.sendRedirect(request.getContextPath() + "/admin-detail-application?requestId=" + requestId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
