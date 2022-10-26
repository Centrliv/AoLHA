package com.maltsev.lab1.servlets;

import com.maltsev.lab1.database.UserTable;
import com.maltsev.lab1.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authorization", value =  "/authorization")
public class Authorization extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/authorization.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        User user = new User(login, password);

        boolean authFlag = UserTable.checkUser(user);

        req.setAttribute("authFlag", authFlag);
        req.getRequestDispatcher("views/authorization.jsp").forward(req, resp);
    }
}
