package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.GoogleConstant;

import java.io.IOException;

@WebServlet("/google-login")
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scope = "email profile openid";

        String authUrl = "https://accounts.google.com/o/oauth2/auth" +
                "?scope=" + scope +
                "&redirect_uri=" + GoogleConstant.REDIRECT_URI +
                "&response_type=code" +
                "&client_id=" + GoogleConstant.CLIENT_ID +
                "&approval_prompt=force";
        resp.sendRedirect(authUrl);
    }
}

