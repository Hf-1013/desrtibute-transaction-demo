package com.quicktron.producer.client;

import com.quicktron.common.dto.CommonResponse;
import com.quicktron.producer.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("kc-consumer")
public interface CouponClient {
    
    
    @PostMapping("/coupon/add-register-free-coupon")
    CommonResponse<Boolean> addRegisterFreeCoupon(@RequestBody Member member);
}
