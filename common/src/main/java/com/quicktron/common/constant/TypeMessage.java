package com.quicktron.common.constant;

import com.alibaba.fastjson.JSON;

public class TypeMessage<T> extends Message {
    private String key;
    private T body;
    private Long delayMillis;

    public TypeMessage() {
    }

    public TypeMessage(String key, T body) {
        this.key = key;
        this.body = body;
    }

    public TypeMessage(T body, Long delayMillis) {
        this.body = body;
        this.delayMillis = delayMillis;
    }

    public TypeMessage(String key, T body, Long delayMillis) {
        this.key = key;
        this.body = body;
        this.delayMillis = delayMillis;
    }

    public String getBody() {
        return this.body == null ? "" : JSON.toJSONString(this.body);
    }

    public void setBody(T body) {
        this.body = body;
    }

    public static <T> TypeMessage.MessageBuilder<T> build() {
        return new TypeMessage.MessageBuilder();
    }

    public static <T> TypeMessage<T> body(T body) {
        return (TypeMessage<T>) build().body(body).build();
    }

    public String toString() {
        return "{\"key\":\"" + this.key + '"' + ",\"body\":\"" + this.body + '"' + ",\"delayMillis\":" + this.delayMillis + "}";
    }

    public static class MessageBuilder<T> {
        private String key;
        private T body;
        private Long delayMillis;

        public MessageBuilder() {
        }

        public TypeMessage.MessageBuilder<T> key(String key) {
            this.key = key;
            return this;
        }

        public TypeMessage.MessageBuilder<T> body(T body) {
            this.body = body;
            return this;
        }

        public TypeMessage.MessageBuilder<T> delayMillis(Long delayMillis) {
            this.delayMillis = delayMillis;
            return this;
        }

        public TypeMessage<T> build() {
            return new TypeMessage(this.key, this.body, this.delayMillis);
        }
    }
}