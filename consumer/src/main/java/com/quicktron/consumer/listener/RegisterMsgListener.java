package com.quicktron.consumer.listener;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.quicktron.consumer.domain.Coupon;
import com.quicktron.consumer.domain.Member;
import com.quicktron.consumer.domain.UserCoupon;
import com.quicktron.consumer.service.ICouponService;
import com.quicktron.consumer.service.IUserCouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;

/**
 * @Author houfeng
 * @Date 2022/7/22 15:56
 */
@RocketMQMessageListener(consumerGroup = "demo_consumer", topic = "register")
@Component
@Slf4j
public class RegisterMsgListener implements RocketMQListener<Member> {

    @Autowired
    private ICouponService couponService;

    @Autowired
    private IUserCouponService userCouponService;

    @Override
    public void onMessage(Member o) {
        //消费者消费到消息， 说明用户一定是注册成功， 此时使用rocketMQ的消费机制保证这条消息至少会被消费一次，所以这里要做防重处理
        log.info("用户注册成功：{}", JSONObject.toJSONString(o));
        //给用户添加优惠券
        Coupon registerFreeCoupon = couponService.getRegisterFreeCoupon(o.getId());
        //简单的防重复消费
        UserCoupon one =
                userCouponService.getOne(Wrappers.<UserCoupon>lambdaQuery().eq(UserCoupon::getMemberId, o.getId()).eq(UserCoupon::getCouponId,
                registerFreeCoupon.getId()));
        if(one == null){
            UserCoupon userCoupon = UserCoupon.builder().couponId(registerFreeCoupon.getId()).memberId(o.getId()).build();
            userCouponService.save(userCoupon);
        }
    }
}
