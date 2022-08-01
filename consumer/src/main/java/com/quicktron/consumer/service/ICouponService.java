package com.quicktron.consumer.service;

import com.quicktron.consumer.domain.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author houfeng
 * @since 2022-08-01
 */
public interface ICouponService extends IService<Coupon> {

    Coupon getRegisterFreeCoupon(Long memberId);
}
