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
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "google", urlPatterns = {"/google"})
public class google extends HttpServlet {

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
            String id = DigestUtils.md5Hex(request.getParameter("id"));
            String fullname = request.getParameter("name");
            String[] nameparts = fullname.split("\\s+");
            String profilepic = request.getParameter("profilepic");
            String newemail = request.getParameter("email");

            PreparedStatement checkEmail = DB.prepare(
                    "SELECT email FROM users WHERE email=? AND status='1'");
            checkEmail.setString(1, newemail);
            ResultSet regrs = checkEmail.executeQuery();

            if (!regrs.isBeforeFirst()) {
                // New Google user - register them
                PreparedStatement insUser = DB.prepare(
                        "INSERT INTO users (email, status, hash, user_type_iduser_type) VALUES (?, '1', ?, '2')");
                insUser.setString(1, newemail);
                insUser.setString(2, id);
                insUser.executeUpdate();

                PreparedStatement findId = DB.prepare(
                        "SELECT idusers FROM users WHERE email=? AND status='1'");
                findId.setString(1, newemail);
                ResultSet regidrs = findId.executeQuery();
                if (regidrs.next()) {
                    int uid = regidrs.getInt(1);

                    PreparedStatement updName = DB.prepare(
                            "UPDATE users SET firstname=?, lastname=? WHERE idusers=?");
                    updName.setString(1, nameparts[0]);
                    updName.setString(2, nameparts.length > 1 ? nameparts[1] : "");
                    updName.setInt(3, uid);
                    updName.executeUpdate();

                    PreparedStatement insPic = DB.prepare(
                            "INSERT INTO user_profile_pic (image, users_idusers) VALUES (?, ?)");
                    insPic.setString(1, profilepic + "?sz=180");
                    insPic.setInt(2, uid);
                    insPic.executeUpdate();

                    PreparedStatement insTheme = DB.prepare(
                            "INSERT INTO app_theme (themename, users_idusers) VALUES ('purplelight', ?)");
                    insTheme.setInt(1, uid);
                    insTheme.executeUpdate();

                    PreparedStatement insLayout = DB.prepare(
                            "INSERT INTO app_layout (users_idusers, layout) VALUES (?, 1)");
                    insLayout.setInt(1, uid);
                    insLayout.executeUpdate();

                    PreparedStatement insUap = DB.prepare(
                            "INSERT INTO uap (Preference, users_idusers) VALUES ('1', ?)");
                    insUap.setInt(1, uid);
                    insUap.executeUpdate();

                    PreparedStatement insPrivacy = DB.prepare(
                            "INSERT INTO user_privacy (privacy_name, users_idusers) VALUES ('public', ?)");
                    insPrivacy.setInt(1, uid);
                    insPrivacy.executeUpdate();

                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", uid);
                    out.write("<div class='fixed-action-btn'>");
                    out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                    out.write("<i class='large material-icons black-text'>skip_next</i>");
                    out.write("</a>");
                    out.write("</div>");
                }
            } else {
                // Existing Google user - update their info and log in
                PreparedStatement findId = DB.prepare(
                        "SELECT idusers FROM users WHERE email=? AND status='1'");
                findId.setString(1, newemail);
                ResultSet regidrs = findId.executeQuery();
                if (regidrs.next()) {
                    int uid = regidrs.getInt(1);

                    PreparedStatement updPic = DB.prepare(
                            "UPDATE user_profile_pic SET image=? WHERE users_idusers=?");
                    updPic.setString(1, profilepic + "?sz=180");
                    updPic.setInt(2, uid);
                    updPic.executeUpdate();

                    PreparedStatement updName = DB.prepare(
                            "UPDATE users SET firstname=?, lastname=? WHERE idusers=?");
                    updName.setString(1, nameparts[0]);
                    updName.setString(2, nameparts.length > 1 ? nameparts[1] : "");
                    updName.setInt(3, uid);
                    updName.executeUpdate();

                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", uid);
                    out.write("<div class='fixed-action-btn'>");
                    out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                    out.write("<i class='large material-icons black-text'>skip_next</i>");
                    out.write("</a>");
                    out.write("</div>");
                }
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
