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

@WebServlet(name = "finaladdsforindexrefresh", urlPatterns = {"/finaladdsforindexrefresh"})
public class finaladdsforindexrefresh extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            try {
                uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user session");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Dcolor = themeColors[3];

            double rate = 0;
            try {
                PreparedStatement ratersPs = DB.prepare("Select idAPPHPI from apphpi");
                ResultSet raters = ratersPs.executeQuery();
                if (raters.next()) {
                    rate = Double.parseDouble(raters.getString(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int usap = 1;
            try {
                PreparedStatement ssmPs = DB.prepare("Select Preference from uap where users_idusers=?");
                ssmPs.setInt(1, uid);
                ResultSet ssm = ssmPs.executeQuery();
                if (ssm.next()) {
                    if (ssm.getInt(1) == 0) {
                        out.write("<div class='col s12 m12'>");
                        out.write("<div class='card " + esc(Acolor) + " '>");
                        out.write("<div class='card-content " + esc(Dcolor) + "'>");
                        out.write("<span class='card-title'>Sponsored Content Turned Off</span>");
                        out.write("<img src='img/seo.png' class='responsive-img center-block'>");
                        out.write("</div>");
                        out.write("<div class='card-action'>");
                        out.write("<a href='profile.jsp' class='" + esc(Dcolor) + "'>Manage Settings</a>");
                        out.write("</div>");
                        out.write("</div>");
                        out.write("</div>");
                    } else {
                        try {
                            PreparedStatement usaprsPs = DB.prepare("Select adcategory from user_followed_ad_catergories where users_idusers=?");
                            usaprsPs.setInt(1, uid);
                            ResultSet usaprs = usaprsPs.executeQuery();
                            if (usaprs.next()) {
                                usap = usaprs.getInt(1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String adSql = "SELECT a.Adid, a.src, a.link, a.users_idusers, a.forhowmanyusers, a.forhowmanyhours, " +
                                       "TIMEDIFF(at.adendtime, CURRENT_TIMESTAMP) AS time_diff " +
                                       "FROM ads a LEFT JOIN adtiming at ON a.Adid = at.Ads_Adid " +
                                       "WHERE a.status='4'";
                        if (usap != 1) {
                            adSql += " AND a.adcategory=?";
                        }

                        PreparedStatement adPs = DB.prepare(adSql);
                        if (usap != 1) {
                            adPs.setInt(1, usap);
                        }
                        ResultSet adsRs = adPs.executeQuery();

                        boolean hasAds = false;
                        while (adsRs.next()) {
                            hasAds = true;

                            String adId = adsRs.getString("Adid");
                            String adSrc = esc(adsRs.getString("src"));
                            String adLink = esc(adsRs.getString("link"));
                            String adTimeDiff = adsRs.getString("time_diff");

                            out.write("\n");
                            out.write("                                <div class=\"col s12 m12\">\n");
                            out.write("                                    <div class=\"card ");
                            out.print(esc(Acolor));
                            out.write(" \">\n");
                            out.write("                                    <div class=\"card-content ");
                            out.print(esc(Dcolor));
                            out.write("\">\n");
                            out.write("                                            <div class=\"card-image resizeimg\" style=\"overflow: hidden\">\n");
                            out.write("                                            <a href=\"");
                            out.print(adLink);
                            out.write("\">\n");
                            out.write("                                                <img src=\"");
                            out.print(adSrc);
                            out.write("\" >\n");
                            out.write("                                            </a>\n");
                            out.write("                                            </div>\n");
                            out.write("                                        </div>\n");
                            out.write("</div>");
                            out.write("                                        </div>");

                            if (adTimeDiff != null) {
                                boolean isExpired = adTimeDiff.startsWith("-");
                                PreparedStatement adsUpdPs = DB.prepare(
                                    "UPDATE `ads` SET `status` = ? WHERE `ads`.`Adid` = ?");
                                adsUpdPs.setString(1, isExpired ? "6" : "4");
                                adsUpdPs.setString(2, adId);
                                adsUpdPs.executeUpdate();
                            }
                        }

                        if (!hasAds) {
                            out.write("<div class='col s12 m12'>");
                            out.write("<div class='card " + esc(Acolor) + "'>");
                            out.write("<div class='card-content " + esc(Dcolor) + "'>");
                            out.write("<span class='card-title'>Publish Your Advertisement for only <br>Rs." + rate + "/=</span>");
                            out.write("<img src='img/uwu.png' class='responsive-img center-block'>");
                            out.write("</div>");
                            out.write("<div class='card-action'>");
                            out.write("<a href='dashboard.jsp' class=" + esc(Dcolor) + ">Publish Now</a>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading ads");
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
