package com.quicktron.producer.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.mapper.MemberMapper;
import com.quicktron.producer.service.IMemberService;
import org.springframework.stereotype.Service;

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

}
