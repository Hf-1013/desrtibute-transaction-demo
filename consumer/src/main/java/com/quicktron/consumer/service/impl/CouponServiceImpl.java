package com.quicktron.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.consumer.domain.Coupon;
import com.quicktron.consumer.mapper.CouponMapper;
import com.quicktron.consumer.service.ICouponService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author houfeng
 * @since 2022-08-01
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

    @Override
    public Coupon getRegisterFreeCoupon(Long memberId) {
        return getById(1);
    }
}
