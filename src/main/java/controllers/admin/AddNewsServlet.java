package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Account;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.NewsService;
import services.impl.NewsServiceImpl;

@WebServlet(name = "AddNewsServlet", value = "/add-news")
public class AddNewsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Account account = (Account) request.getSession().getAttribute("account");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NewsService newsService = new NewsServiceImpl();
        newsService.add(new News(title, content, "", account));

        request.getSession().setAttribute("successAddNews", "success");
        response.sendRedirect(request.getContextPath() + "/admin/news");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
