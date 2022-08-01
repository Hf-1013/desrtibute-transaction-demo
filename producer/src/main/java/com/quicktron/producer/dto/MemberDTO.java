package com.quicktron.producer.dto;

import lombok.Data;

/**
 * @Author houfeng
 * @Date 2022/8/1 15:20
 */
@Data
public class MemberDTO {

    private Long id;

    /**
     * 用户名
     */
    private String username;
}
