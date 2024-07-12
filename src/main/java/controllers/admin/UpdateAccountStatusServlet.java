package controllers.admin;

import dao.impl.AccountDAOImpl;
import entity.Account;
import entity.AccountStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "updateAccountStatusServlet", value = "/admin/updateAccountStatus")
public class UpdateAccountStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select = request.getParameter("select");
        int accountId = Integer.parseInt(request.getParameter("accountId"));

        AccountDAOImpl accountDAO = new AccountDAOImpl();
        Account account = accountDAO.getById(accountId);
        PrintWriter out = response.getWriter();
        if (select.equals("deactive")) {
            account.setAccountStatus(new AccountStatus(2, "deactivate"));
        } else if (select.equals("active")) {
            account.setAccountStatus(new AccountStatus(1, "activate"));
        } else if (select.equals("delete")) {
            account.setAccountStatus(new AccountStatus(3, "deleted"));
        }


        accountDAO.update(account);
        response.sendRedirect(request.getContextPath() + "/admin/students");

    }
}
