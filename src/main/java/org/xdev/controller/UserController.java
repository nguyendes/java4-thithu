package org.xdev.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.xdev.entity.User;

import java.io.IOException;

@WebServlet({"/login","/logout","/demo"})
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/login")) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
         if (uri.contains("/logout")) {
            req.getSession().invalidate();
            resp.sendRedirect("login.jsp");
        }
         else if (uri.contains("/demo")) {
             User user =  (User) req.getSession().getAttribute("user");
             req.setAttribute("user", user);
             req.getRequestDispatcher("demo.jsp").forward(req, resp);
         }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // Xác thực
            if ("admin".equals(username) && "admin".equals(password)) {
                user.setRole("admin"); // Gán role admin
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/phieu-giam-gia/hien-thi");
            } else if ("user".equals(username) && "user".equals(password)) {
                user.setRole("user"); // Gán role user
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("demo.jsp");
            } else {
                req.setAttribute("error", "Invalid username or password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }

}
