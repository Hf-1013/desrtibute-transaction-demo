package com.quicktron.producer.controller;



import com.quicktron.producer.dto.CommonResponse;
import com.quicktron.producer.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author houfeng
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/member")
public class MemberController {


    @PostMapping("/login-by-password")
    public CommonResponse<UserLoginDTO> loginByPassword(){
        return null;
    }


}

