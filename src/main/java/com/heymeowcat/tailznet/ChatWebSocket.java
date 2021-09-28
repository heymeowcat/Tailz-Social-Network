package com.heymeowcat.tailznet;

import java.io.IOException;
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

@ServerEndpoint("/websocket/chat")
public class ChatWebSocket {

    private static final ConcurrentHashMap<String, Session> userSessions = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Set<String>> groupSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            ChatMessage wsMsg = ChatMessage.fromJson(message);
            String uid = wsMsg.getSenderId();
            if (uid == null) return;

            if ("register".equals(wsMsg.getType())) {
                userSessions.put(uid, session);
                session.getUserProperties().put("uid", uid);
                return;
            }

            if ("register_group".equals(wsMsg.getType())) {
                userSessions.put(uid, session);
                session.getUserProperties().put("uid", uid);
                String groupId = wsMsg.getGroupId();
                if (groupId != null) {
                    registerGroupMember(groupId, uid);
                }
                return;
            }

            if ("new_message".equals(wsMsg.getType())) {
                sendMessageToUser(wsMsg.getReceiverId(), "new_message:" + wsMsg.getSenderId());
            } else if ("new_group_message".equals(wsMsg.getType())) {
                sendMessageToGroup(wsMsg.getGroupId(), "new_group_message:" + wsMsg.getSenderId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        String uid = (String) session.getUserProperties().get("uid");
        if (uid != null) {
            userSessions.remove(uid);
        }
        for (String groupId : groupSessions.keySet()) {
            Set<String> members = groupSessions.get(groupId);
            if (members != null) {
                members.remove(uid);
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
