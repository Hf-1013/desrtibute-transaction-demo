package com.quicktron.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.consumer.domain.UserCoupon;
import com.quicktron.consumer.mapper.UserCouponMapper;
import com.quicktron.consumer.service.IUserCouponService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户优惠券表 服务实现类
 * </p>
 *
 * @author houfeng
 * @since 2022-08-01
 */
@Service
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCoupon> implements IUserCouponService {

}
