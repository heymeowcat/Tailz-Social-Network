package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
import com.heymeowcat.tailznet.service.ChatService;
import com.heymeowcat.tailznet.service.GroupChatService;

@WebServlet(urlPatterns = {"/sse/msgoverview"}, asyncSupported = true)
public class SseMsgOverviewServlet extends HttpServlet {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

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
        async.setTimeout(0);
        final PrintWriter writer = resp.getWriter();

        final ChatService chatService = new ChatService();
        final GroupChatService groupChatService = new GroupChatService();
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

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    List<Object[]> userResults = chatService.getMessageOverview(uid);

                    if (userResults == null || userResults.isEmpty()) {
                        sb.append("<div class='center'><img src='img/conversation.png' class='responsiveimg' style='margin-top: 100px'></div>");
                        sb.append("<div class='grey-text center'>Icons made by <a href='https://www.freepik.com/' title='Freepik'>Freepik</a> from <a href='https://www.flaticon.com/' title='Flaticon'>www.flaticon.com</a> is licensed by <a href='http://creativecommons.org/licenses/by/3.0/' title='Creative Commons BY 3.0' target='_blank'>CC 3.0 BY</a></div>");
                    }

                    if (userResults != null) {
                        for (Object[] row : userResults) {
                            int muiddd = ((Number) row[3]).intValue();
                            int unseenSent = ((Number) row[4]).intValue();
                            int unseenRecv = ((Number) row[5]).intValue();
                            int unseenTotal = unseenSent + unseenRecv;

                            String outString;
                            if (unseenTotal > 9) {
                                outString = "<b>9+</b>";
                            } else if (unseenTotal == 0) {
                                outString = "<i class='material-icons " + escapeHtml(Dcolor) + "'>add</i>";
                            } else {
                                outString = "<b>" + unseenTotal + "</b>";
                            }

                            sb.append("<div class='col s6 m3 l2'>");
                            sb.append("    <div class='").append(escapeHtml(Acolor)).append(" card-panel hoverable'>");
                            sb.append("        <img src='").append(escapeHtml((String) row[3])).append("' class='circle responsive-img'>");
                            sb.append("        <div class='card-content center ").append(escapeHtml(Dcolor)).append("'>");
                            sb.append("            <p class='truncate'>").append(escapeHtml((String) row[0])).append(" ").append(escapeHtml((String) row[1])).append("</p>");
                            sb.append("            <a onclick='setuser(").append(muiddd).append(");' class='btn-floating ").append(escapeHtml(Bcolor)).append(" ").append(escapeHtml(Dcolor)).append(" waves-effect'>");
                            sb.append(outString);
                            sb.append("            </a>");
                            sb.append("        </div>");
                            sb.append("    </div>");
                            sb.append("</div>");
                        }
                    }

                    List<Object[]> groupResults = groupChatService.getGroupMessageOverview(uid);
                    String groupOutString = "<i class='material-icons " + escapeHtml(Dcolor) + "'>add</i>";
                    if (groupResults != null) {
                        for (Object[] row : groupResults) {
                            sb.append("<div class='col s6 m3 l2'>");
                            sb.append("    <div class='").append(escapeHtml(Acolor)).append(" card-panel hoverable'>");
                            sb.append("        <img src='").append(escapeHtml((String) row[2])).append("' class='circle responsive-img'>");
                            sb.append("        <div class='card-content center ").append(escapeHtml(Dcolor)).append("'>");
                            sb.append("            <p class='truncate'>").append(escapeHtml((String) row[1])).append("</p>");
                            sb.append("            <a onclick=\"setusergroup('").append(escapeHtml((String) row[0])).append("');\" class='btn-floating ").append(escapeHtml(Bcolor)).append(" ").append(escapeHtml(Dcolor)).append(" waves-effect'>");
                            sb.append(groupOutString);
                            sb.append("            </a>");
                            sb.append("        </div>");
                            sb.append("    </div>");
                            sb.append("</div>");
                        }
                    }

                    String data = "data: " + sb.toString() + "\n\n";
                    writer.print(data);
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

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
