package controllers.user;

import dao.impl.NewsDAOImpl;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "UserNewsServlet", value = "/user/news")
public class UserNewsServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        NewsDAOImpl newsDAO = new NewsDAOImpl();
        List<News> newsList = newsDAO.getAllByTitle(title, page, 8);
        int record = newsDAO.countNumberOfNews();
        request.setAttribute("maxPage", record / 8 + 1);
        request.setAttribute("newsList", newsList);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/user/user-news.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        NewsDAOImpl newsDAO = new NewsDAOImpl();
        int record = newsDAO.countNumberOfNews();
        int maxPage = (int) Math.ceil((double) record / 10);
        if (page > 0 && page <= maxPage) {
            List<News> newsList = newsDAO.getAllNewsByPagination(page);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("newsList", newsList);
            request.setAttribute("page", page);
            request.setAttribute("isSearch", "ok");
        }
        request.getRequestDispatcher("/view/user/user-news.jsp").forward(request, response);
    }
}
