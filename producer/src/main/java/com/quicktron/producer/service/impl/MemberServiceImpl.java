package com.quicktron.producer.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.producer.config.TransactionProduce;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.dto.RegisterDTO;
import com.quicktron.producer.listener.TransactionListenerImpl;
import com.quicktron.producer.mapper.MemberMapper;
import com.quicktron.producer.service.IMemberService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author houfeng
 * @since 2022-07-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private TransactionProduce transactionProduce;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByTel(RegisterDTO registerDTO) throws Exception {
        Member member = new Member();
        BeanUtils.copyProperties(registerDTO, member);
        //保存用户
        save(member);
        //添加优惠券，这里使用mq消息异步的方式来添加优惠券
        TransactionMQProducer producer = new TransactionMQProducer("transaction-producer-group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("10.0.90.211:9876");
        // 设置事务监听器
        producer.setTransactionListener(new TransactionListenerImpl());
        // 启动生产者
        producer.start();
        transactionProduce.sendTransactionMessage(member.getId().toString(), "test-register", null);


    }
}
