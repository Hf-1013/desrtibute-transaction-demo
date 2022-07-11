package com.quicktron.common.constant;

public class Message {
    private String key;
    private String body;
    private Long delayMillis;

    public Message() {
    }

    public Message(String key, String body) {
        this.key = key;
        this.body = body;
    }

    public Message(String body, Long delayMillis) {
        this.body = body;
        this.delayMillis = delayMillis;
    }

    public Message(String key, String body, Long delayMillis) {
        this.key = key;
        this.body = body;
        this.delayMillis = delayMillis;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getDelayMillis() {
        return this.delayMillis;
    }

    public void setDelayMillis(Long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public static Message.MessageBuilder builder() {
        return new Message.MessageBuilder();
    }

    public String toString() {
        return "{\"key\":\"" + this.key + '"' + ",\"body\":\"" + this.body + '"' + ",\"delayMillis\":" + this.delayMillis + "}";
    }

    public static class MessageBuilder {
        private String key;
        private String body;
        private Long delayMillis;

        public MessageBuilder() {
        }

        public Message.MessageBuilder key(String key) {
            this.key = key;
            return this;
        }

        public Message.MessageBuilder body(String body) {
            this.body = body;
            return this;
        }

        public Message.MessageBuilder delayMillis(Long delayMillis) {
            this.delayMillis = delayMillis;
            return this;
        }

        public Message build() {
            return new Message(this.key, this.body, this.delayMillis);
        }
    }
}
