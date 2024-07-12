package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.RequestService;
import services.impl.RequestServiceImpl;

@WebServlet(name = "AdminApplicationServlet", value = "/admin-application")
public class AdminApplicationServlet extends HttpServlet {

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
        RequestService requestService = new RequestServiceImpl();
        String filterByStatus = request.getParameter("filterByStatus");
        String searchKey = request.getParameter("searchKey");
        List<Request> requestList;
        String pageString = request.getParameter("page");
        if(pageString == null) {
            pageString = "1";
        }
        int page = Integer.parseInt(pageString);
        int numberOfRequest;
        int endPage;

        if (filterByStatus == null || filterByStatus.isEmpty()) {
            filterByStatus = "all";
        }

        if(filterByStatus.equals("all")) {
            if(searchKey == null || searchKey.trim().isEmpty()) {
                numberOfRequest = requestService.countNumberOfRequest();
                endPage = numberOfRequest / 10;
                requestList = requestService.getRequestsByPagination(page);
            } else {
                numberOfRequest = requestService.countNumberOfRequestBySearchKey(searchKey);
                endPage = numberOfRequest / 10;
                requestList = requestService.getRequestsBySearchKeyByPagination(page, searchKey);
            }
        } else {
            if(searchKey == null || searchKey.trim().isEmpty()) {
                numberOfRequest = requestService.countNumberOfRequestByStatus(filterByStatus);
                endPage = numberOfRequest / 10;
                requestList = requestService.getRequestByStatusAndPagination(page, filterByStatus);
            } else {
                numberOfRequest = requestService.countNumberOfRequestByStatusAndSearchKey(filterByStatus, searchKey);
                endPage = numberOfRequest / 10;
                requestList = requestService.getRequestsByStatusAndSearchKeyAndPagination(page, filterByStatus, searchKey);
            }

        }
        if(endPage % 10 == 1) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("requestList", requestList);
        request.setAttribute("filterByStatus", filterByStatus);
        request.getRequestDispatcher("view/admin/admin-application.jsp").forward(request, response);
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
