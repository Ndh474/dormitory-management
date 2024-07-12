package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;

import dao.impl.NewsDAOImpl;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserNewsDetailServlet", value = "/user-news-detail")
public class UserNewsDetailServlet extends HttpServlet {

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
        int newsId = Integer.parseInt(request.getParameter("id"));
        NewsDAOImpl newsDAO = new NewsDAOImpl();
        News news = newsDAO.getById(newsId);
        request.setAttribute("title", news.getTitle());
        request.setAttribute("content", news.getContent());
        request.setAttribute("date", news.getDateCreated());
        PrintWriter out = response.getWriter();
        out.println(news.getContent());
        request.getRequestDispatcher("/view/user/user-news-details.jsp").forward(request, response);
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
