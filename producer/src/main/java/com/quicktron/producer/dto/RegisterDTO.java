package com.quicktron.producer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author houfeng
 * @Date 2022/7/9 15:30
 */
@Data
public class RegisterDTO {

    @NotNull
    private String mobile;

    @NotNull
    private String verifyCode;

    @NotNull
    private String username;

}
