package com.quicktron.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



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
        return new CommonResponse<>(data).setCode(ResponseCodeConstants.SUCCESS_CODE);
    }
    
    public Boolean isSuccess(){
        return ResponseCodeConstants.SUCCESS_CODE.equals(this.code);
    }
}
