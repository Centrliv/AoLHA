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

@WebServlet(name = "AddUser", value =  "/add-user")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add-user.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        User user = new User(login, password);

        boolean submitFlag = UserTable.addUser(user);

        req.setAttribute("submitFlag", submitFlag);
        req.getRequestDispatcher("views/add-user.jsp").forward(req, resp);
    }
}
