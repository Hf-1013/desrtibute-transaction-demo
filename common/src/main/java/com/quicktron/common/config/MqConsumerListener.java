package com.quicktron.common.config;


public interface MqConsumerListener {
    void consume(String msg);
}
