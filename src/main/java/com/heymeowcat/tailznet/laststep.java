/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "laststep", urlPatterns = {"/laststep"})
public class laststep extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String hash = request.getParameter("hash");
            String usn = request.getParameter("usn");
            String pass = DigestUtils.md5Hex(request.getParameter("psn"));
            String conpass = DigestUtils.md5Hex(request.getParameter("conpsn"));
            int uid;
            boolean exist = false;

            PreparedStatement checkUsn = DB.prepare(
                    "SELECT username FROM user_login WHERE username=?");
            checkUsn.setString(1, usn);
            ResultSet rs2 = checkUsn.executeQuery();

            if (rs2.next()) {
                exist = false;
            } else {
                exist = true;
            }

            if (pass.equals(conpass) && exist) {
                PreparedStatement findUser = DB.prepare(
                        "SELECT idusers FROM users WHERE email=? AND hash=? AND status='1'");
                findUser.setString(1, email);
                findUser.setString(2, hash);
                ResultSet rs = findUser.executeQuery();
                if (rs.next()) {
                    uid = rs.getInt(1);
                    PreparedStatement insLogin = DB.prepare(
                            "INSERT INTO user_login (username, password, users_idusers) VALUES (?, ?, ?)");
                    insLogin.setString(1, usn);
                    insLogin.setString(2, conpass);
                    insLogin.setInt(3, uid);
                    insLogin.executeUpdate();
                    response.sendRedirect("firstupdateprofile.jsp?uid=" + uid);
                }
            } else {
                response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
