package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.dao.AdsDAO.AdStatusUpdate;
import com.heymeowcat.tailznet.service.AdsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

            AdsService adsService = new AdsService();
            double rate = adsService.getAppHpiRate();
            int userPreference = adsService.getUserPreference(uid);

            if (userPreference == 0) {
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
                List<Object[]> ads = adsService.getActiveAdsWithTiming(uid);
                boolean hasAds = false;
                List<AdStatusUpdate> statusUpdates = new ArrayList<>();

                if (ads != null) {
                    for (Object[] row : ads) {
                        hasAds = true;

                        String adId = String.valueOf(row[0]);
                        String adSrc = esc((String) row[1]);
                        String adLink = esc((String) row[2]);
                        String adTimeDiff = (String) row[6];

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
                            statusUpdates.add(new AdStatusUpdate(Integer.parseInt(adId), isExpired ? "6" : "4"));
                        }
                    }
                }

                if (!statusUpdates.isEmpty()) {
                    adsService.batchUpdateAdStatus(statusUpdates);
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
