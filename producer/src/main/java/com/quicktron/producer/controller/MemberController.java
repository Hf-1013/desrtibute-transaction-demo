package com.quicktron.producer.controller;



import com.quicktron.producer.dto.CommonResponse;
import com.quicktron.producer.dto.RegisterDTO;
import com.quicktron.producer.dto.UserLoginDTO;
import com.quicktron.producer.service.IMemberService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @Autowired
    private IMemberService memberService;

    @PostMapping("/login-by-password")
    public CommonResponse<UserLoginDTO> loginByPassword(){
        return null;
    }

    @PostMapping("/register-by-tel")
    public CommonResponse<Object> registerByTel(@RequestBody @Validated RegisterDTO registerDTO) throws Exception {
        memberService.registerByTel(registerDTO);
        return CommonResponse.success();
    }

    public static void main(String[] args) {
        String s = "   \\\"  sjdkalsjd  \", \"  asdasd  \"   ";
        String s1 = StringEscapeUtils.unescapeJava(StringUtils.trim(s));
        System.out.println(s);
        System.out.println(s1);

    }

}

