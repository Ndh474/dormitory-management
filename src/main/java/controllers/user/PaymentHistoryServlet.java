package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entity.Account;
import entity.Bill;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.BillService;
import services.InformationService;
import services.impl.BillServiceImpl;
import services.impl.InformationServiceImpl;

@WebServlet(name = "PaymentHistoryServlet", value = "/payment-history")
public class PaymentHistoryServlet extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());
        request.setAttribute("information", information);

        BillService billService = new BillServiceImpl();
        List<Bill> billList = billService.getByInformationId(information.getInformationId());
        request.setAttribute("billList", billList);

        request.getRequestDispatcher("view/user/user-payment-history.jsp").forward(request, response);

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
