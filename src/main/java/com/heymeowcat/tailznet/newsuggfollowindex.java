package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.FollowService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "newsuggfollowindex", urlPatterns = {"/newsuggfollowindex"})
public class newsuggfollowindex extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int loggeduid = 0;
            int x = 0;
            try {
                loggeduid = Integer.parseInt(request.getParameter("loggedid"));
                x = Integer.parseInt(request.getParameter("x"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(loggeduid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Dcolor = themeColors[3];

            FollowService followService = new FollowService();
            followService.follow(loggeduid, x);

            List<Object[]> suggestions = followService.getSuggestedUsers(loggeduid);
            boolean m = false;
            if (suggestions != null && !suggestions.isEmpty()) {
                int count = 0;
                for (Object[] row : suggestions) {
                    if (count >= 5) break;
                    m = true;
                    String image = esc((String) row[2]);
                    String firstname = esc((String) row[0]);
                    String lastname = esc((String) row[1]);
                    String idusers = esc(String.valueOf(row[3]));

                    out.write("\n");
                    out.write("                                    <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                    out.print(image);
                    out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle \" ><div class=\"");
                    out.write('"');
                    out.write('>');
                    out.print(firstname);
                    out.write(' ');
                    out.print(lastname);
                    out.write("</div></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a class=\" btn ");
                    out.print(esc(Bcolor));
                    out.write(' ');
                    out.print(esc(Dcolor));
                    out.write(" waves-effect\" onclick=\"followthissugg('");
                    out.print(idusers);
                    out.write("')\"><i class=\"material-icons\">person_add</i></a></h6></td></tr>\n");
                    out.write("                                            ");
                    count++;
                }
            }
            out.write("\n");
            out.write("                                    ");
            if (m) {
                out.write("\n");
                out.write("                                           <tr style='border-color: transparent'><td class=\"");
                out.print(esc(Dcolor));
                out.write("\"><a class=\"");
                out.print(esc(Dcolor));
                out.write("\" href=\"search-trending.jsp\">More...</a></td></tr>\n");
                out.write("                                    ");
            }
            out.write("\n");
            out.write("                                 \n");
            out.write("                                </table>\n");
            out.write("                                ");
            if (!m) {
                out.write("\n");
                out.write("                                <div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>\n");
                out.write("                            </div>\n");
                out.write("                            ");
                out.write("");

            }
            out.write("\n");
            out.write("                            </div>\n");
            out.write("                        </div>\n");
            out.write("                    </li>\n");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading suggestions");
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
