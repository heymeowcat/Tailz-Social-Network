/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "update", urlPatterns = {"/update"})
public class update extends HttpServlet {

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
            try {
                FileItemFactory fi = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(fi);
                List<FileItem> li = upload.parseRequest(request);
                String uid = request.getParameter("uid");
                String fin = "";
                String ln = "";
                String fp = "";
                for (FileItem fileItem : li) {
                    if (!fileItem.isFormField()) {
                        String fn = System.currentTimeMillis() + "_" + fileItem.getName();
                        String path = request.getServletContext().getRealPath("/");
                        path = path.replace("\\", "/");
                        String folder = "img/";
                        File f = new File(path + folder + fn);
                        fileItem.write(f);
                        fp = folder + fn;
                    } else {
                        if (fileItem.getFieldName().equals("fn")) {
                            fin = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("ln")) {
                            ln = fileItem.getString();
                        }else if(fileItem.equals(null)){
                            fp = "img/Profile_avatar_placeholder_large.png";
                        }
                    }
                }
                DB.iud("UPDATE `users` SET `firstname` = '" + fin + "', `lastname` = '" + ln + "' WHERE `users`.`idusers` = '" + uid + "';");
                DB.iud("UPDATE `user_profile_pic` SET `image` = '" + fp + "' WHERE `user_profile_pic`.`users_idusers`='" + uid + "';");
                response.sendRedirect("profile.jsp");
            } catch (Exception e) {
                e.printStackTrace();
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
