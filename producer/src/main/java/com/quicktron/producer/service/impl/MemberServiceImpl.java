package com.quicktron.producer.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.dto.RegisterDTO;
import com.quicktron.producer.mapper.MemberMapper;
import com.quicktron.producer.service.IMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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




    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByTel(RegisterDTO registerDTO) {
        Member member = new Member();
        BeanUtils.copyProperties(registerDTO, member);

        //保存用户

        //添加优惠券，这里使用mq消息异步的方式来添加优惠券

    }
}
