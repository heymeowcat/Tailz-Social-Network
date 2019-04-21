package com.heymeowcat.tailznet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HEYMEOWCAT
 */
@WebServlet(name = "loginprocess", urlPatterns = {"/loginprocess"})
public class loginprocess extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String usn = request.getParameter("usn");
            String psn = DigestUtils.md5Hex(request.getParameter("psn"));
            String check = request.getParameter("check");
            java.sql.ResultSet loginrs = DB.search("Select password,users_idusers,iduser_login from sEXUaFqh92.`user_login` where username= '" + usn + "' ");
            if (loginrs.next()) {
                if (loginrs.getString(1).equals(psn)) {
                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", loginrs.getInt(2));
                    if (check != null) {
                        String encryptedString = ENCDEC.encrypt(loginrs.getString(2), new KEY().secretKey);
                        Cookie cookie = new Cookie("MEOWID", encryptedString);
                        cookie.setMaxAge(60 * 60 * 24 * 30);
                        response.addCookie(cookie);
                    }
                    DB.iud("INSERT INTO `login_sessions` ( `ip_address`, `in_time`,`user_login_iduser_login`) VALUES ('" + request.getRemoteHost() + "',CURRENT_TIMESTAMP, '" + loginrs.getString(3) + "');");
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("login-register.jsp?error=Come On!");
                }
            } else {
                response.sendRedirect("login-register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
