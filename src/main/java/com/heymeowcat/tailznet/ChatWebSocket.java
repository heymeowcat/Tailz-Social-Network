package com.heymeowcat.tailznet;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ServerEndpoint("/websocket/chat")
public class ChatWebSocket {

    private static final ConcurrentHashMap<String, Session> userSessions = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Set<String>> groupSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        String uid = (String) config.getUserProperties().get("uid");
        if (uid != null) {
            userSessions.put(uid, session);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        session.getAsyncRemote().sendText("echo:" + message);
    }

    @OnClose
    public void onClose(Session session) {
        for (String uid : userSessions.keySet()) {
            if (userSessions.get(uid) == session) {
                userSessions.remove(uid);
                break;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public static void sendMessageToUser(String uid, String message) {
        Session session = userSessions.get(uid);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageToGroup(String groupId, String message) {
        Set<String> members = groupSessions.get(groupId);
        if (members != null) {
            for (String uid : members) {
                sendMessageToUser(uid, message);
            }
        }
    }

    public static void registerGroupMember(String groupId, String uid) {
        groupSessions.computeIfAbsent(groupId, k -> ConcurrentHashMap.newKeySet()).add(uid);
    }

    public static void unregisterGroupMember(String groupId, String uid) {
        Set<String> members = groupSessions.get(groupId);
        if (members != null) {
            members.remove(uid);
        }
    }
}
