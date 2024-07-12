package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dao.impl.NewsDAOImpl;
import entity.Account;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.NewsService;
import services.impl.NewsServiceImpl;

@WebServlet(name = "AdminNewsServlet", value = "/admin/news")
public class AdminNewsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String pageString = request.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        int page = Integer.parseInt(pageString);
        NewsService newsService = new NewsServiceImpl();
        int numberOfNews = newsService.countNumberOfNews();
        int endPage = numberOfNews / 10;
        if (endPage % 10 == 1) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);

        List<News> newsList = newsService.getAllNewsByPagination(page);
        request.setAttribute("newsList", newsList);

        request.getRequestDispatcher("/view/admin/admin-news.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String pageString = request.getParameter("page");
        List<News> newsList = new ArrayList<News>();


        if (pageString == null) {
            pageString = "1";
        }
        if ((title == null || title.isEmpty()) && (date == null || date.isEmpty())) {
            response.sendRedirect("/admin/news");
        } else {
            if (date != null) {
                NewsDAOImpl newsDAO = new NewsDAOImpl();
                newsList = newsDAO.getAllByDate(date);
                request.setAttribute("date", date);
            }
            if (title != null) {
                NewsDAOImpl newsDAO = new NewsDAOImpl();
                newsList = newsDAO.getAllByTitle(title, 1, 100);
                request.setAttribute("title", title);
            }
            request.setAttribute("newsList", newsList);
            request.getRequestDispatcher("/view/admin/admin-news.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
