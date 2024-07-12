package controllers.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import entity.Account;
import entity.Dorm;
import services.DormService;
import services.impl.DormServiceImpl;

import java.util.List;

@WebServlet(name = "UserViewDormServlet", value = "/user/view-dorm")
public class UserViewDormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DormService dormService = new DormServiceImpl();
        List<Dorm> dorms = dormService.getAll();
        request.setAttribute("dormList", dorms);
        request.getRequestDispatcher("/view/user/user-view-dorm.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}