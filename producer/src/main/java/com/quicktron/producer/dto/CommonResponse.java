package com.quicktron.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.quicktron.producer.dto.ResponseCodeConstants.SUCCESS_CODE;


/**
 * @Author houfeng
 * @Date 2022/7/1 16:30
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResponse(T data){
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data){
        return new CommonResponse<>(data).setCode(SUCCESS_CODE);
    }
}
