package com.quicktron.producer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author houfeng
 * @Date 2022/7/19 19:17
 */
@TableName("tb_rocket_mq_tx_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RocketmqTransactionLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String transactionId;

    private String log;

    private Date createTime;

    private Date updateTime;
}
