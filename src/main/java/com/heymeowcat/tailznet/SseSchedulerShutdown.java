package com.heymeowcat.tailznet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SseSchedulerShutdown implements ServletContextListener {

    public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(8);

    @Override
    public void contextInitialized(ServletContextEvent sce) {}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SCHEDULER.shutdown();
        try {
            if (!SCHEDULER.awaitTermination(5, TimeUnit.SECONDS)) {
                SCHEDULER.shutdownNow();
            }
        } catch (InterruptedException e) {
            SCHEDULER.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
