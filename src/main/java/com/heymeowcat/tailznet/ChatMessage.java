package com.heymeowcat.tailznet;

import java.util.HashMap;
import java.util.Map;

public class ChatMessage {
    private String type;
    private String senderId;
    private String receiverId;
    private String groupId;
    private String content;
    private String src;
    private String timestamp;

    public ChatMessage() {
    }

    public ChatMessage(String type, String senderId, String receiverId, String content) {
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public static ChatMessage fromJson(String json) {
        ChatMessage msg = new ChatMessage();
        try {
            String remaining = json.trim();
            if (remaining.startsWith("{")) {
                remaining = remaining.substring(1, remaining.length() - 1);
            }
            String[] parts = remaining.split(",");
            for (String part : parts) {
                String[] kv = part.split(":");
                if (kv.length >= 2) {
                    String key = kv[0].trim().replace("\"", "");
                    String value = kv[1].trim().replace("\"", "");
                    switch (key) {
                        case "type": msg.type = value; break;
                        case "senderId": msg.senderId = value; break;
                        case "receiverId": msg.receiverId = value; break;
                        case "groupId": msg.groupId = value; break;
                        case "content": msg.content = value; break;
                        case "src": msg.src = value; break;
                        case "timestamp": msg.timestamp = value; break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String toJson() {
        return "{"
                + "\"type\":\"" + type + "\","
                + "\"senderId\":\"" + senderId + "\","
                + "\"receiverId\":\"" + receiverId + "\","
                + "\"groupId\":\"" + groupId + "\","
                + "\"content\":\"" + content + "\","
                + "\"src\":\"" + src + "\","
                + "\"timestamp\":\"" + timestamp + "\""
                + "}";
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
