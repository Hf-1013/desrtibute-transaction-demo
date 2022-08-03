package com.quicktron.producer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quicktron.producer.domain.Member;
import com.quicktron.producer.dto.RegisterDTO;

/**
 * <p>
 * 会员 服务类
 * </p>
 *
 * @author houfeng
 * @since 2022-07-01
 */
public interface IMemberService extends IService<Member> {

    Boolean registerByTelWithRocketMqTx(RegisterDTO registerDTO) throws Exception;
    
    Boolean registerByTelWithWithSyncCall(RegisterDTO registerDTO);
}
