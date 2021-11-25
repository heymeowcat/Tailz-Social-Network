package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.heymeowcat.tailznet.dao.AdsDAO.AdStatusUpdate;
import com.heymeowcat.tailznet.service.AdsService;

@WebServlet(urlPatterns = {"/sse/ads"}, asyncSupported = true)
public class SseAdsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uidParam = req.getParameter("uid");
        if (uidParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uid");
            return;
        }
        final int uid;
        try {
            uid = Integer.parseInt(uidParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid");
            return;
        }

        resp.setContentType("text/event-stream");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Connection", "keep-alive");

        final AsyncContext async = req.startAsync();
        async.setTimeout(120000);
        final PrintWriter writer = resp.getWriter();

        final AdsService adsService = new AdsService();
        String[] themeColors;
        try {
            themeColors = ThemeHelper.getThemeColors(uid);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error getting theme colors");
            return;
        }
        final String Acolor = themeColors[0];
        final String Dcolor = themeColors[3];

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    if (adsService.getUserPreference(uid) == 1) {
                        List<Object[]> ads = adsService.getActiveAdsWithTiming(uid);
                        List<AdStatusUpdate> statusUpdates = new ArrayList<>();

                        if (ads != null) {
                            for (Object[] row : ads) {
                                String adId = String.valueOf(row[0]);
                                String adSrc = escapeHtml((String) row[1]);
                                String adLink = escapeHtml((String) row[2]);
                                String adTimeDiff = (String) row[6];

                                sb.append("\n");
                                sb.append("                                <div class=\"col s12 m12\">\n");
                                sb.append("                                    <div class=\"card ");
                                sb.append(escapeHtml(Acolor));
                                sb.append(" \">\n");
                                sb.append("                                    <div class=\"card-content ");
                                sb.append(escapeHtml(Dcolor));
                                sb.append("\">\n");
                                sb.append("                                            <div class=\"card-image resizeimg\" style=\"overflow: hidden\">\n");
                                sb.append("                                            <a href=\"");
                                sb.append(adLink);
                                sb.append("\">\n");
                                sb.append("                                                <img src=\"");
                                sb.append(adSrc);
                                sb.append("\" >\n");
                                sb.append("                                            </a>\n");
                                sb.append("                                            </div>\n");
                                sb.append("                                        </div>\n");
                                sb.append("</div>");
                                sb.append("                                        </div>");

                                if (adTimeDiff != null) {
                                    boolean isExpired = adTimeDiff.startsWith("-");
                                    statusUpdates.add(new AdStatusUpdate(Integer.parseInt(adId), isExpired ? "6" : "4"));
                                }
                            }
                        }

                        if (!statusUpdates.isEmpty()) {
                            adsService.batchUpdateAdStatus(statusUpdates);
                        }

                        if (sb.length() == 0) {
                            sb.append("<div class='col s12 m12'>");
                            sb.append("<div class='card ").append(escapeHtml(Acolor)).append("'>");
                            sb.append("<div class='card-content ").append(escapeHtml(Dcolor)).append("'>");
                            sb.append("<span class='card-title'>Publish Your Advertisement for only <br>Rs.").append(adsService.getAppHpiRate()).append("/=-</span>");
                            sb.append("<img src='img/uwu.png' class='responsive-img center-block'>");
                            sb.append("</div>");
                            sb.append("<div class='card-action'>");
                            sb.append("<a href='dashboard.jsp' class=").append(escapeHtml(Dcolor)).append(">Publish Now</a>");
                            sb.append("</div>");
                            sb.append("</div>");
                            sb.append("</div>");
                        }
                    } else {
                        sb.append("<div class='col s12 m12'>");
                        sb.append("<div class='card ").append(escapeHtml(Acolor)).append(" '>");
                        sb.append("<div class='card-content ").append(escapeHtml(Dcolor)).append("'>");
                        sb.append("<span class='card-title'>Sponsored Content Turned Off</span>");
                        sb.append("<img src='img/seo.png' class='responsive-img center-block'>");
                        sb.append("</div>");
                        sb.append("<div class='card-action'>");
                        sb.append("<a href='profile.jsp' class='").append(escapeHtml(Dcolor)).append("'>Manage Settings</a>");
                        sb.append("</div>");
                        sb.append("</div>");
                        sb.append("</div>");
                    }

                    String data = "data: " + sb.toString() + "\n\n";
                    writer.print(data);
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ScheduledFuture<?> future = SseSchedulerShutdown.SCHEDULER.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

        async.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                future.cancel(false);
            }
            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                future.cancel(false);
                writer.close();
            }
            @Override
            public void onError(AsyncEvent event) throws IOException {
                future.cancel(false);
            }
            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {}
        });
    }

    private static String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
