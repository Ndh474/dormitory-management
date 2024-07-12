package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import dao.NewsDAO;
import dao.impl.NewsDAOImpl;
import entity.Account;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditNewsSevlet", value = "/edit-news")
public class EditNewsSevlet extends HttpServlet {

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Account account = (Account) request.getSession().getAttribute("account");
        String id = request.getParameter("newsId");
        String title = request.getParameter("newsTitle");
        String content = request.getParameter("newsContent");
        NewsDAO newsDAO = new NewsDAOImpl();
        newsDAO.update(new News(Integer.parseInt(id), title, content, "", account));
        request.getSession().setAttribute("successEditNews", "success");
        response.sendRedirect(request.getContextPath() + "/admin/news");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
