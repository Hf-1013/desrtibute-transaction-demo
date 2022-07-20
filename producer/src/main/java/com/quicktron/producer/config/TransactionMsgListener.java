package com.quicktron.producer.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.domain.RocketmqTransactionLog;
import com.quicktron.producer.mapper.RocketMqTransactionLogMapper;
import com.quicktron.producer.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.nio.charset.StandardCharsets;

@RocketMQTransactionListener
@Slf4j
public class TransactionMsgListener implements RocketMQLocalTransactionListener {


    @Autowired
    private IMemberService memberService;

    @Autowired
    private RocketMqTransactionLogMapper rocketMqTransactionLogMapper;

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg,
                                                                 Object obj) {
        log.info("执行本地事务");
        MessageHeaders headers = msg.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer orderId = Integer.valueOf((String)headers.get("order_id"));
        log.info("transactionId is {}, orderId is {}",transactionId,orderId);
        Member member = (Member) msg.getPayload();
        member.setStatus(1);
        try{
            //执行本地事务，并记录日志
            memberService.updateById(member);
            rocketMqTransactionLogMapper.insert(
                    RocketmqTransactionLog.builder()
                            .transactionId(transactionId)
                            .log("注册事件")
                            .build()
            );            //执行成功，可以提交事务
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 检查本地事务的状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        log.info("检查本地事务,事务ID:{}",transactionId);
        //根据事务id从日志表检索
        QueryWrapper<RocketmqTransactionLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("transaction_id",transactionId);
        RocketmqTransactionLog rocketmqTransactionLog = rocketMqTransactionLogMapper.selectOne(queryWrapper);
        if(null != rocketmqTransactionLog){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}