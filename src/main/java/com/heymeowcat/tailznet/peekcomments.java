/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.heymeowcat.tailznet.entities.Post;
import com.heymeowcat.tailznet.entities.PostComment;
import com.heymeowcat.tailznet.service.CommentService;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "peekcomments", urlPatterns = {"/peekcomments"})
public class peekcomments extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int pid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                pid = Integer.parseInt(request.getParameter("pid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/pid parameter");
                return;
            }
            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            PostService postService = new PostService();
            CommentService commentService = new CommentService();
            UserService userService = new UserService();
            UserProfilePicService profilePicService = new UserProfilePicService();

            Post post = postService.getPostById(pid);
            if (post != null) {
                out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + " '>close</i>");
                out.write("<div class='" + Acolor + " card-panel' >");
                out.write("<div class='row'>");
                out.write("<div class='col s12 m6' style='max-height:100% ;overflow: scroll' > ");
                if (String.valueOf(post.getPostTypeId()).equals("1")) {
                    out.write("<img class='responsive-img materialboxed' src='" + ENCDEC.decrypt(post.getImage(), "default-key") + "' ");
                } else if (String.valueOf(post.getPostTypeId()).equals("2")) {
                    out.write(ENCDEC.decrypt(post.getImage(), "default-key"));
                }
                out.write("<span><h5>" + ENCDEC.decrypt(post.getHeading(), "default-key") + "</h5></span>");
                out.write("<p>" + ENCDEC.decrypt(post.getDetail(), "default-key") + "</p>");
                out.write("<br>");
                String imgup = profilePicService.getProfilePicPath(post.getUserId());
                String fnamepost = userService.getUserFirstName(post.getUserId());
                String lnamepost = userService.getUserLastName(post.getUserId());
                String postdate = "";
                String posttime = "";
                String likecount = " ";
                String postTime = post.getPostTime();
                if (postTime != null && !postTime.isEmpty()) {
                    postdate = postTime;
                }
                likecount = String.valueOf(postService.getLikeCount(pid));
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'><img src='" + imgup + "'>" + fnamepost + " " + lnamepost + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + postdate + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + posttime + "</div>");
                out.write("<div onclick='$('#peekwholikesthis').modal('open')'; class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>😍 " + likecount + "</div>");
                out.write("</div>");
                out.write("<div class='col s12 m6'>");
                out.write("<h6>Comments</h6>");
                out.write("<ul id='commentsection' class='" + Acolor + " collection' style='width: 100%;height: 55vh;overflow: scroll; border-color:"+Ccolor+"' >");
            }

            List<PostComment> comments = commentService.getCommentsForPost(pid);
            if (comments == null || comments.isEmpty()) {
                out.write("<div class='center' style='top:40%; position:relative'><img src ='img/commentlive.png' class='animated pulse responsiveimg '></div>");
            } else {
                for (PostComment pc : comments) {
                    String cmpic = profilePicService.getProfilePicPath(pc.getUserId());
                    String cmfn = userService.getUserFirstName(pc.getUserId());
                    String cmln = userService.getUserLastName(pc.getUserId());
                    out.write("<li class='collection-item avatar  " + Acolor + " " + Dcolor + "' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + esc(cmfn) + " " + esc(cmln) + "</span>");
                    out.write("<p>" + commentEscape(pc.getCommentText()) + "<br>");
                    out.write("" + commentEscape(ENCDEC.decrypt(pc.getImage(), "default-key")) + " ");
                    out.write("</p>");
                    out.write("</li>");
                }
            }

            out.write("</ul>");
            out.write("<div class='input-field'>");
            out.write("<input type='text' id='commenttextarea' required placeholder='Add a Comment...' class=' " + Dcolor + " ' >");
            out.write("</div><a class='" + Bcolor + " " + Dcolor + " btn' onclick='comment(" + pid + "," + uid + ")'; >Add Comment</a>");
            out.write("</div>");
            out.write("</div>");
            out.write("</div>");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading post");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    private String commentEscape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
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
