package controllers.admin;

import dao.impl.AccountDAOImpl;
import dao.impl.InformationDAOImpl;
import dao.impl.NewsDAOImpl;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountStatusService;
import services.RoleService;
import services.impl.AccountStatusServiceImpl;
import services.impl.RoleServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminStudentsServlet", value = "/admin/students")
public class AdminStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String searchOption = req.getParameter("searchOption");
        String searchInput = req.getParameter("searchInput");
        String currentPage = req.getParameter("currentPage");
        currentPage = currentPage==null?"1":req.getParameter("currentPage");
        InformationDAOImpl informationDAO = new InformationDAOImpl();

        List<Information> studentsList = new ArrayList<>();
        Information info;
        if (searchOption != null && searchInput != null && !searchOption.equals("all") && !searchInput.isEmpty()) {
            switch (searchOption) {
                case "byName":
                    studentsList = informationDAO.getAllByName(searchInput);
                    break;
                case "byGmail":
                    info = informationDAO.getByEmail(searchInput);
                    studentsList.add(info);
                    break;
                case "byRollNumber":
                    info = informationDAO.getByRollNumber(searchInput);
                    studentsList.add(info);
                    break;
                case "byRoom":
                    studentsList = informationDAO.getFromRoomName(searchInput);
                    break;
            }
            req.setAttribute("studentsList", studentsList);
            req.setAttribute("searchOption", searchOption);
            req.setAttribute("searchInput", searchInput);
        } else {

            studentsList = informationDAO.getAllByPaging(Integer.parseInt(currentPage),20);
            req.setAttribute("studentsList", studentsList);
        }

        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        List<AccountStatus> accountStatusList = accountStatusService.getAll();
        req.setAttribute("accountStatusList", accountStatusList);

        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getAll();
        req.setAttribute("roleList", roleList);
        req.setAttribute("currentPage",currentPage);
        req.setAttribute("maxPage",(int)Math.ceil((double)informationDAO.countStudent()/20));

        req.getRequestDispatcher("/view/admin/admin-students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
