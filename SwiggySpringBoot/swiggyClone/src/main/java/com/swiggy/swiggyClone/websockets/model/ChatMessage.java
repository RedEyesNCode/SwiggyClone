package com.swiggy.swiggyClone.websockets.model;

public class ChatMessage {
    private String content;
    private String sender;
    private String type;

 /*   public enum MessageType {
        CHAT, LEAVE, JOIN
    }
*/
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
