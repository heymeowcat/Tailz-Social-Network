package com.heymeowcat.tailznet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HEYMEOWCAT
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String newemail = request.getParameter("newmail");
            if (newemail == null || newemail.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email parameter is required");
                return;
            }
            Random random = new Random();
            int randomNum = random.nextInt(999999);
            String myhash = DigestUtils.md5Hex("" + randomNum);

            PreparedStatement checkActive = DB.prepare(
                    "SELECT email FROM users WHERE email=? AND status='1'");
            checkActive.setString(1, newemail);
            ResultSet regrs = checkActive.executeQuery();

            PreparedStatement checkAny = DB.prepare(
                    "SELECT email FROM users WHERE email=?");
            checkAny.setString(1, newemail);
            ResultSet regrss = checkAny.executeQuery();

            if (!regrs.next()) {
                SendingEmail se = new SendingEmail();
                se.sendMail(newemail, myhash);
                if (!regrss.next()) {
                    PreparedStatement ins = DB.prepare(
                            "INSERT INTO users (email, status, hash, user_type_iduser_type) VALUES (?, '0', ?, '2')");
                    ins.setString(1, newemail);
                    ins.setString(2, myhash);
                    ins.executeUpdate();
                }
                response.sendRedirect("verify.jsp");
            } else {
                response.sendRedirect("login-register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during registration");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
