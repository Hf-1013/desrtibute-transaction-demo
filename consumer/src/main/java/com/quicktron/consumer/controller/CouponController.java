package com.quicktron.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.quicktron.consumer.domain.Coupon;
import com.quicktron.consumer.domain.Member;
import com.quicktron.consumer.domain.UserCoupon;
import com.quicktron.common.dto.CommonResponse;
import com.quicktron.consumer.service.ICouponService;
import com.quicktron.consumer.service.IUserCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponController {
    
    @Autowired
    private ICouponService couponService;
    
    @Autowired
    private IUserCouponService userCouponService;
    
    @PostMapping("/add-register-free-coupon")
    public CommonResponse<Boolean> addRegisterFreeCoupon(@RequestBody Member member){
        //消费者消费到消息， 说明用户一定是注册成功， 此时使用rocketMQ的消费机制保证这条消息至少会被消费一次，所以这里要做防重处理
        log.info("用户注册成功：{}", JSONObject.toJSONString(member));
        //给用户添加优惠券
        Coupon registerFreeCoupon = couponService.getRegisterFreeCoupon(member.getId());
        //简单的防重复调用
        UserCoupon one =
                userCouponService.getOne(Wrappers.<UserCoupon>lambdaQuery().eq(UserCoupon::getMemberId, member.getId()).eq(UserCoupon::getCouponId,
                        registerFreeCoupon.getId()));
        if(one == null){
            UserCoupon userCoupon = UserCoupon.builder().couponId(registerFreeCoupon.getId()).memberId(member.getId()).build();
            userCouponService.save(userCoupon);
        }
        return CommonResponse.success(Boolean.TRUE);
    }

}
