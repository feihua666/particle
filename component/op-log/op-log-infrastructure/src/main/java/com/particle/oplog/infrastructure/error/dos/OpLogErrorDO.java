package com.particle.oplog.infrastructure.error.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 操作异常日志表
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Accessors(chain = true)
@Data
@TableName("component_op_log_error")
public class OpLogErrorDO extends BaseDO {

    /**
    * 日志追踪id
    */
    private String traceId;

    /**
    * 操作用户id
    */
    private Long userId;

    /**
    * 操作用户姓名
    */
    private String userName;

    /**
    * 操作用户昵称
    */
    private String userNickname;

    /**
    * 操作用户头像
    */
    private String userAvatar;

    /**
    * 请求地址，要求带http
    */
    private String requestUrl;

    /**
    * 请求方法
    */
    private String requestMethod;

    /**
    * 请求头信息
    */
    private String requestHeaders;

    /**
    * 请求参数
    */
    private String requestParams;

    /**
    * 请求内容
    */
    private String requestBody;

    /**
    * 请求ip
    */
    private String requestIp;

    /**
    * 响应的状态码
    */
    private Integer responseStatus;

    /**
    * 响应头信息
    */
    private String responseHeaders;

    /**
    * 响应内容
    */
    private String responseBody;

    /**
    * 本地主机ip，用来表明是在哪个机器上运行的
    */
    private String localHostIp;

    /**
    * 本地主机名称，用来表明是在哪个机器上运行的
    */
    private String localHostName;

    /**
    * 异常发生时间
    */
    private LocalDateTime errorAt;
    

}
