package com.quicktron.producer.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.dto.RegisterDTO;
import com.quicktron.producer.mapper.MemberMapper;
import com.quicktron.producer.service.IMemberService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByTel(RegisterDTO registerDTO) throws Exception {
        Member member = new Member();
        BeanUtils.copyProperties(registerDTO, member);
        //保存用户
        save(member);
        String transactionId = UUID.randomUUID().toString();
        //添加优惠券，这里使用mq消息异步的方式来添加优惠券
        //如果可以删除订单则发送消息给rocketmq，让用户中心消费消息
        rocketMQTemplate.sendMessageInTransaction("add-amount","register", MessageBuilder.withPayload(member)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .setHeader("member_id",member.getId())
                .build(), null);

    }
}
