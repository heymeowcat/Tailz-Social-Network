package com.heymeowcat.tailznet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.heymeowcat.tailznet.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HEYMEOWCAT
 */
@WebServlet(name = "ActivateAccount", urlPatterns = {"/ActivateAccount"})
public class ActivateAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String email = request.getParameter("key1");
            String hash = request.getParameter("key2");
            ResultSet rs = DB.search("Select email,hash,status from users where email='" + email + "' AND hash='" + hash + "' AND status=0 ");
            if (rs.next()) {
                DB.iud("Update users SET status=1 where email='" + email + "' AND hash='" + hash + "' ");
                response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
            }
//            ResultSet rs2 = DB.search("Select  firstname,lastname,email,hash,status from users where email='" + email + "' AND hash='" + hash + "' AND status=1");
//            if (rs.next()) {
//                if (rs.getString(1).equals(null) || rs.getString(2).equals(null)) {
//                    response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
//                } else {
//                    response.sendRedirect("verify.jsp");
//                }
//            } else if (rs2.next()) {
//                int uid = rs2.getInt(1);
//                ResultSet rs3 = DB.search("Select username from user_login where users_idusers='" + uid + "' ");
//                if (rs3.next()) {
//                    response.sendRedirect("login-register.jsp");
//                } else {
//                    response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
//                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
