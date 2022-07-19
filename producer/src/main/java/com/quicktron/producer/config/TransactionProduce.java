package com.quicktron.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.producer.TransactionSendResult;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionProduce {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendTransactionMessage(String msg, String topic, String tag) {
        log.info("start sendTransMessage hashKey:{}", msg);
        Message<String> message = new GenericMessage<>(msg);
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(null, formatTopicAndTags(topic, tag), message, null);
        //发送状态
        String sendStatus = result.getSendStatus().name();
        // 本地事务执行状态
        String localTxState = result.getLocalTransactionState().name();
        log.info("send tx message sendStatus:{},localTXState:{}", sendStatus, localTxState);
    }

    public String formatTopicAndTags(String topic, String tag){
        if(StringUtils.isBlank(topic)){
            throw new RuntimeException("send message failed topic cant be null");
        }
        if(StringUtils.isNotBlank(tag)){
            return topic + ":" + tag;
        }
        return topic;
    }
}