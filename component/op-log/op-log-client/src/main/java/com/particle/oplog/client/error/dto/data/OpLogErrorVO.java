package com.particle.oplog.client.error.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 操作异常日志 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Data
@Schema
public class OpLogErrorVO extends AbstractBaseIdVO {

    @Schema(description = "日志追踪id")
    private String traceId;

    @Schema(description = "操作用户id")
    private Long userId;

    @Schema(description = "操作用户姓名")
    private String userName;

    @Schema(description = "操作用户昵称")
    private String userNickname;

    @Schema(description = "操作用户头像")
    private String userAvatar;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "请求方法")
    private String requestMethod;

    @Schema(description = "请求头信息")
    private String requestHeaders;

    @Schema(description = "请求参数")
    private String requestParams;

    @Schema(description = "请求内容")
    private String requestBody;

    @Schema(description = "请求ip")
    private String requestIp;

    @Schema(description = "响应的状态码")
    private Integer responseStatus;

    @Schema(description = "响应头信息")
    private String responseHeaders;

    @Schema(description = "响应内容")
    private String responseBody;

    @Schema(description = "本地主机ip")
    private String localHostIp;

    @Schema(description = "本地主机名称")
    private String localHostName;

    @Schema(description = "异常发生时间")
    private LocalDateTime errorAt;



}
