package controllers.user;

import entity.Account;
import entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.RequestService;
import services.impl.RequestServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewApplicationServlet", value = "/view-application")
public class ViewApplicationServlet extends HttpServlet {

    private final RequestService requestService = new RequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        String pageString = request.getParameter("page");
        if(pageString == null) {
            pageString = "1";
        }
        int page = Integer.parseInt(pageString);
        int numberOfRequest = requestService.countNumberOfRequestByAccount(account.getAccountID());
        int endPage = numberOfRequest / 10;
        if(endPage % 10 == 1) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);
        List<Request> requestListByAccount = requestService.getRequestOfAccountByPagination(page, account.getAccountID());
        request.setAttribute("requestList", requestListByAccount);
        request.getRequestDispatcher("/view/user/view-application.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {

    }
}
