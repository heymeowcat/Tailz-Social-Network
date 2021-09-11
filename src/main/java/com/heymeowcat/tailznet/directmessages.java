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

@WebServlet(name = "directmessages", urlPatterns = {"/directmessages"})
public class directmessages extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int muid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                muid = Integer.parseInt(request.getParameter("muid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/muid parameter");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];
            String muidFirstName = "";
            String uidFirstName = "";
            String uidLastName = "";
            String uidImage = "";
            PreparedStatement muidPs = DB.prepare("SELECT firstname FROM users WHERE idusers=?");
            muidPs.setInt(1, muid);
            ResultSet muidRs = muidPs.executeQuery();
            if (muidRs.next()) {
                muidFirstName = muidRs.getString(1);
            }
            PreparedStatement uidPs = DB.prepare("SELECT firstname, lastname, image FROM users JOIN user_profile_pic ON users.idusers = user_profile_pic.users_idusers WHERE idusers=?");
            uidPs.setInt(1, uid);
            ResultSet uidRs = uidPs.executeQuery();
            if (uidRs.next()) {
                uidFirstName = uidRs.getString(1);
                uidLastName = uidRs.getString(2);
                uidImage = uidRs.getString(3);
            }

            PreparedStatement chatPs = DB.prepare(
                    "SELECT * FROM chat WHERE (user_sender=? AND users_receiver=?) OR (user_sender=? AND users_receiver=?) ORDER BY chat_datetime ASC");
            chatPs.setInt(1, muid);
            chatPs.setInt(2, uid);
            chatPs.setInt(3, uid);
            chatPs.setInt(4, muid);
            ResultSet rs = chatPs.executeQuery();
            while (rs.next()) {
                if (rs.getString(5).equals("" + muid)) {
                    out.write("<div class='message__list'>");
                    out.write("<div class='message__item message__item--bot'>");
                    out.write("<span class='message message--bot " + Dcolor + "'  data-balloon='" + esc(rs.getString(4)) + "' data-balloon-pos='right' >");
                    out.write("<b>" + esc(muidFirstName) + "</b><br>");
                    if (rs.getString(2) != null) {
                        out.write(esc(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)));
                    }
                    if (rs.getString(3) != null) {
                        if (rs.getString(2) == null) {
                            out.write("<img class='responsive-img' src='" + esc(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                        } else {
                            out.write("<br><img class='responsive-img' src='" + esc(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                        }
                    }
                    out.write("</span>");
                    out.write("</div>");
                    out.write("</div>");
                } else if (rs.getString(5).equals("" + uid)) {
                    out.write("<div class='message__list'>");
                    out.write("<div class='message__item message__item--user'>");
                    out.write("<span class='message message--user " + Dcolor + "' data-balloon='" + esc(rs.getString(4)) + "' data-balloon-pos='left' >");
                    out.write("<b class='right'>Me</b><br>");
                    if (rs.getString(2) != null) {
                        out.write(esc(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)));
                    }
                    if (rs.getString(3) != null) {
                        if (rs.getString(2) == null) {
                            out.write("<img class='responsive-img' src='" + esc(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                        } else {
                            out.write("<br><img class='responsive-img' src='" + esc(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                        }
                    }
                    out.write("</span>");
                    out.write("</div>");
                    out.write("</div>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading messages");
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
