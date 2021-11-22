package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.UserLoginService;
import com.heymeowcat.tailznet.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "laststep", urlPatterns = {"/laststep"})
public class laststep extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String email = request.getParameter("email");
            String hash = request.getParameter("hash");
            String usn = request.getParameter("usn");
            String pass = DigestUtils.md5Hex(request.getParameter("psn"));
            String conpass = DigestUtils.md5Hex(request.getParameter("conpsn"));

            UserLoginService loginService = new UserLoginService();

            boolean usnExists = loginService.getLoginByUsername(usn) != null;

            if (pass.equals(conpass) && !usnExists) {
                UserService userService = new UserService();
                Integer uid = userService.getUserIdByEmailAndHash(email, hash);
                if (uid != null) {
                    loginService.createLogin(uid, usn, conpass);
                    response.sendRedirect("firstupdateprofile.jsp?uid=" + uid);
                } else {
                    response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
                }
            } else {
                response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
