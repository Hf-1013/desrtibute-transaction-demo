package com.quicktron.common.annotatiion;

import org.springframework.stereotype.Component;


/**
 * @Author houfeng
 * @Date 2022/7/11 10:13
 */
@Component
public @interface Consumer {
    String topic() default "";
    String tag() default "";
    String group() default "";

}
