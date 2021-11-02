package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.GroupService;
import com.heymeowcat.tailznet.entities.GroupMembers;
import com.heymeowcat.tailznet.dao.GroupMembersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "leavethisgroup", urlPatterns = {"/leavethisgroup"})
public class leavethisgroup extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String groupid = request.getParameter("x");
            int memberid = 0;
            try {
                memberid = Integer.parseInt(request.getParameter("y"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID");
                return;
            }
            if (groupid == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing group ID");
                return;
            }
            GroupMembersDAO membersDAO = new GroupMembersDAO();
            List<GroupMembers> memberships = membersDAO.findByGroup(groupid);
            for (GroupMembers gm : memberships) {
                if (gm.getMemberId() == memberid) {
                    membersDAO.delete(gm);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error leaving group");
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
