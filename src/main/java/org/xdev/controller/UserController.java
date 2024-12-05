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

            User user = new User();
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));

            if ((user.getUsername().equals("admin") && user.getPassword().equals("admin"))||(user.getUsername().equals("user") && user.getPassword().equals("user")) ) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/phieu-giam-gia/hien-thi");
            }
            else {
                resp.sendRedirect("login.jsp");
            }
        }

    }
}
