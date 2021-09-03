/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application lifecycle listener. Initialises the database connection on
 * startup using parameters defined in web.xml.
 *
 * @author heymeowcat
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String server   = ctx.getInitParameter("Server");
        String port     = ctx.getInitParameter("Port");
        String db       = ctx.getInitParameter("DB");
        String username = ctx.getInitParameter("Username");
        String password = ctx.getInitParameter("Password");
        // DB connection uses static configuration in DB class
        ctx.log("AppContextListener: Database connection initialised for " + server + ":" + port + "/" + db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nothing to clean up; connection will be garbage-collected.
    }

}
