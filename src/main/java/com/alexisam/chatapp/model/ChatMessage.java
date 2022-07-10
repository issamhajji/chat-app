package com.alexisam.chatapp.model;

public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType(){
        return type;
    }

    public void setType(MessageType x){
        this.type = x;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String x) {
        this.content = x;
    }

    public String getSender(){
        return sender;
    }

    public void setSender(String x) {
        this.sender = x;
    }

}
