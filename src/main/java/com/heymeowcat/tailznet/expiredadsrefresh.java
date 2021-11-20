package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.AdsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "expiredadsrefresh", urlPatterns = {"/expiredadsrefresh"})
public class expiredadsrefresh extends HttpServlet {

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
            String Bcolor = themeColors[1];
            String Dcolor = themeColors[3];

            AdsService adsService = new AdsService();
            List<Object[]> ads = adsService.getUserExpiredAdsWithTiming(uid);
            boolean hasAds = false;

            if (ads != null) {
                for (Object[] row : ads) {
                    hasAds = true;
                    String adId = String.valueOf(row[0]);
                    String adSrc = esc((String) row[1]);
                    String adLink = esc((String) row[2]);
                    String timeDiff = (String) row[6];

                    out.write("\n");
                    out.write("                                <div class=\"col s12 m12 l4 \">\n");
                    out.write("                                    <b class=\"letter-spacing: ; grey-text\">Ad Id:#");
                    out.print(esc(adId));
                    out.write("</b>\n");
                    out.write("                                    <div class=\"card ");
                    out.print(esc(Acolor));
                    out.write(" \">\n");
                    out.write("                                        <div class=\"card-content ");
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
                    out.write("                                        <div class=\"card-action\">\n");
                    out.write("                                            <div class=\"");
                    out.print(esc(Bcolor));
                    out.write(' ');
                    out.print(esc(Dcolor));
                    out.write(" btn-floating center\">\n");
                    out.write("                                                <span><i class=\"material-icons ");
                    out.print(esc(Dcolor));
                    out.write("\">do_not_disturb_on</i></span>\n");
                    out.write("                                            </div>\n");
                    out.write("                                            ");

                    String timeremainingfrad = "";
                    if (timeDiff != null) {
                        if (timeDiff.startsWith("-")) {
                            timeremainingfrad = "Expired";
                        } else {
                            timeremainingfrad = timeDiff;
                            adsService.updateAdStatus(Integer.parseInt(adId), "4");
                        }
                    }

                    out.write("\n");
                    out.write("                                            <button class=\" ");
                    out.print(esc(Acolor));
                    out.write(' ');
                    out.print(esc(Dcolor));
                    out.write(" right btn-flat  \">");
                    out.print(esc(timeremainingfrad));
                    out.write("</button>\n");
                    out.write("                                        </div>  \n");
                    out.write("                                    </div>  \n");
                    out.write("                                </div>\n");
                    out.write("                                ");
                }
            }

            if (!hasAds) {
                out.write("<div class='center'><img src ='img/email.png' height='130px' class='animated pulse responsiveimg '></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading expired ads");
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
