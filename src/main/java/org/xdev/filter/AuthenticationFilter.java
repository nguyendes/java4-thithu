package org.xdev.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.xdev.entity.User;

import java.io.IOException;

@WebFilter({"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        HttpSession session = request.getSession(false); // Không tự tạo session mới
        if (session == null || session.getAttribute("user") == null) {
            // Người dùng chưa đăng nhập
            if (path.contains("/login")) {
                filterChain.doFilter(request, response); // Cho phép truy cập login
            } else {
                response.sendRedirect(request.getContextPath() + "/login"); // Chuyển về login
            }
            return;
        }

// Kiểm tra quyền truy cập
        User user = (User) session.getAttribute("user");
        if ("admin".equals(user.getRole())) {
            filterChain.doFilter(request, response); // Admin được truy cập tất cả
        } else if (path.contains("/demo")) {
            filterChain.doFilter(request, response); // User được truy cập /demo
        } else {
            response.sendRedirect("404.jsp"); // Chặn truy cập trái phép
        }

    }}
