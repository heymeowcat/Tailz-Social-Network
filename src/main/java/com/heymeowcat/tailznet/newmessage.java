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
@WebServlet(name = "newmessage", urlPatterns = {"/newmessage"})
public class newmessage extends HttpServlet {

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
          
            FileItemFactory fi = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fi);
            List<FileItem> li = upload.parseRequest(request);
            int uid = 0;
            int muid = 0;
            String msg = null;
            String src = null;
            for (FileItem fileItem : li) {
                if (!fileItem.isFormField()) {
                    if (!fileItem.getName().isEmpty()) {
                        String fn = "msg" + System.currentTimeMillis() + "_" + fileItem.getName();
                        String path = request.getServletContext().getRealPath("/");
                        path = path.replace("\\", "/");
                        String folder = "uploads/";
                        File f = new File(path + folder + fn);
                        fileItem.write(f);
                        src = folder + fn;
                    } else {
                        src = "";
                    }
                } else {
                    if (fileItem.getFieldName().equals("msg")) {
                        if(fileItem.getString().isEmpty()){
                            msg = "";
                        }else{
                            msg = fileItem.getString();
                        }                        
                    } else if (fileItem.getFieldName().equals("uid")) {
                        uid = Integer.parseInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("muid")) {
                        muid = Integer.parseInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("file")) {
                        src = src;
                    }
                }
            }
           if(msg.isEmpty()&&src.isEmpty()){
                System.out.println("null");
           }else if(src.isEmpty()){
                DB.iud("INSERT INTO `chat` (`chat_text`,`user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('"+msg+"','" + uid + "', '" + muid + "',1,0);");
           }else if(msg.isEmpty()){
                DB.iud("INSERT INTO `chat` (`src`, `user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('"+src+"', '" + uid + "', '" + muid + "',1,0);");
           }else{
               DB.iud("INSERT INTO `chat` (`chat_text`, `src`, `user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('"+msg+"','"+src+"', '" + uid + "', '" + muid + "',1,0);");
           }
        } catch (Exception e) {
            e.printStackTrace();
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
