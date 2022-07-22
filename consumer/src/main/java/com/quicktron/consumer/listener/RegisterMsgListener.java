package com.quicktron.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @Author houfeng
 * @Date 2022/7/22 15:56
 */
@RocketMQMessageListener(consumerGroup = "consumer", topic = "register")
public class RegisterMsgListener implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        System.out.println(o);
    }
}
