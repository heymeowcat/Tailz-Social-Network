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

@WebServlet(urlPatterns = {"/sse/expiredads"}, asyncSupported = true)
public class SseExpiredAdsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uidParam = req.getParameter("uid");
        if (uidParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uid");
            return;
        }
        int uid;
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
        final String Bcolor = themeColors[1];
        final String Dcolor = themeColors[3];

        final int finalUid = uid;

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    List<Object[]> ads = adsService.getUserExpiredAdsWithTiming(finalUid);
                    List<AdStatusUpdate> statusUpdates = new ArrayList<>();

                    if (ads != null) {
                        for (Object[] row : ads) {
                            String adId = String.valueOf(row[0]);
                            String adSrc = escapeHtml((String) row[1]);
                            String adLink = escapeHtml((String) row[2]);
                            String timeDiff = (String) row[6];

                            sb.append("\n");
                            sb.append("                                <div class=\"col s12 m12 l4 \">\n");
                            sb.append("                                    <b class=\"letter-spacing: ; grey-text\">Ad Id:#");
                            sb.append(escapeHtml(adId));
                            sb.append("</b>\n");
                            sb.append("                                    <div class=\"card ");
                            sb.append(escapeHtml(Acolor));
                            sb.append(" \">\n");
                            sb.append("                                        <div class=\"card-content ");
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
                            sb.append("                                        <div class=\"card-action\">\n");
                            sb.append("                                            <div class=\"");
                            sb.append(escapeHtml(Bcolor));
                            sb.append(' ');
                            sb.append(escapeHtml(Dcolor));
                            sb.append(" btn-floating center\">\n");
                            sb.append("                                                <span><i class=\"material-icons ");
                            sb.append(escapeHtml(Dcolor));
                            sb.append("\">do_not_disturb_on</i></span>\n");
                            sb.append("                                            </div>\n");
                            sb.append("                                            ");

                            String timeremainingfrad = "";
                            if (timeDiff != null) {
                                if (timeDiff.startsWith("-")) {
                                    timeremainingfrad = "Expired";
                                } else {
                                    timeremainingfrad = timeDiff;
                                    statusUpdates.add(new AdStatusUpdate(Integer.parseInt(adId), "4"));
                                }
                            }

                            sb.append("\n");
                            sb.append("                                            <button class=\" ");
                            sb.append(escapeHtml(Acolor));
                            sb.append(' ');
                            sb.append(escapeHtml(Dcolor));
                            sb.append(" right btn-flat  \">");
                            sb.append(escapeHtml(timeremainingfrad));
                            sb.append("</button>\n");
                            sb.append("                                        </div>  \n");
                            sb.append("                                    </div>  \n");
                            sb.append("                                </div>\n");
                            sb.append("                                ");
                        }
                    }

                    if (!statusUpdates.isEmpty()) {
                        adsService.batchUpdateAdStatus(statusUpdates);
                    }

                    if (sb.length() == 0) {
                        sb.append("<div class='center'><img src ='img/email.png' height='130px' class='animated pulse responsiveimg '></div>");
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
