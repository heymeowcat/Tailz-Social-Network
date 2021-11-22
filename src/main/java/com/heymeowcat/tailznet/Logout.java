package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.LoginSessionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Integer uid = null;
            if (request.getSession().getAttribute("user") != null) {
                try {
                    uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user session");
                    return;
                }

                LoginSessionService sessionService = new LoginSessionService();
                sessionService.markLogout(uid);
            }

            HttpSession ses = request.getSession();
            ses.invalidate();

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("MEOWID")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }

            response.sendRedirect("login-register.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during logout");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
