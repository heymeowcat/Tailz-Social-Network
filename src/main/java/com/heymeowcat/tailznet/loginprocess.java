package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.LoginSessionService;
import com.heymeowcat.tailznet.service.UserLoginService;
import com.heymeowcat.tailznet.entities.UserLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "loginprocess", urlPatterns = {"/loginprocess"})
public class loginprocess extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String usn = request.getParameter("usn");
            String psn = request.getParameter("psn");
            String check = request.getParameter("check");
            if (usn == null || psn == null) {
                response.sendRedirect("login-register.jsp");
                return;
            }
            String encryptedPsn = DigestUtils.md5Hex(psn);

            UserLoginService loginService = new UserLoginService();
            UserLogin login = loginService.getLoginByUsername(usn);

            if (login != null) {
                if (login.getPassword().equals(encryptedPsn)) {
                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", login.getUserId());
                    if (check != null) {
                        String encryptedString = ENCDEC.encrypt(String.valueOf(login.getUserId()), new KEY().secretKey);
                        Cookie cookie = new Cookie("MEOWID", encryptedString);
                        cookie.setMaxAge(60 * 60 * 24 * 30);
                        response.addCookie(cookie);
                    }
                    LoginSessionService sessionService = new LoginSessionService();
                    sessionService.logLoginSession(login.getId(), request.getRemoteHost());
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("login-register.jsp?error=Come On!");
                }
            } else {
                response.sendRedirect("login-register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login");
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
