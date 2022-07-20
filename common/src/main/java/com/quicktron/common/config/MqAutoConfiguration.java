/*
package com.quicktron.common.config;

import com.quicktron.common.annotatiion.MqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
@EnableConfigurationProperties(MqProperties.class)
public class MqAutoConfiguration {
    @Autowired
    private List<MqConsumerListener> mqConsumerListenerList;
    @Autowired
    private MqProperties mqProperties;

    @PostConstruct
    public void init() {
        try {
            initMqConsumer();
        } catch (Exception e) {
            log.error("rocketMQ consumer starter error", e);
        }
    }

    */
/**
     * 初始化消费者
     *//*

    public void initMqConsumer() throws MQClientException {
        log.info("rocketMQ consumer init start count:{}", mqConsumerListenerList.size());
        for (MqConsumerListener mqConsumerListener : mqConsumerListenerList) {
            Class<?> clazz = mqConsumerListener.getClass();
            MqConsumer mqConsumer = clazz.getAnnotation(MqConsumer.class);
            DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer();
            pushConsumer.setNamesrvAddr(mqProperties.getNameServerAddr());
            pushConsumer.setConsumeFromWhere(mqConsumer.consumeFromWhere());
            pushConsumer.subscribe(mqConsumer.topic(), mqConsumer.tag());
            pushConsumer.setConsumerGroup(mqConsumer.consumerGroup());
            pushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                try {
                    for (MessageExt message : msgs) {
                        String body = new String(message.getBody(), StandardCharsets.UTF_8);
                        mqConsumerListener.consume(body);
                    }
                }catch (Exception e){
                    log.error("rocketMQ consumer fail",e);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            pushConsumer.start();
        }
    }

    */
/**
     * 设置默认的生产者
     *
     * @return
     *//*

    @ConditionalOnMissingBean(MqProducer.class)
    @Bean
    public MqProducer initDefaultProducer() {
        log.info("rocketMq producer bean create");
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setProducerGroup(mqProperties.getProducerGroup());
        defaultMQProducer.setNamesrvAddr(mqProperties.getNameServerAddr());
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            log.error("rocketMq producer init error", e);
        }
        return new MqProducerImpl(defaultMQProducer) ;
    }

}
*/
