package com.particle.oplog.client.error.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 操作异常日志 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Data
@Schema
public class OpLogErrorCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "日志追踪id 不能为空")
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


    @NotEmpty(message = "异常内容 不能为空")
    @Schema(description = "异常内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;


    public static OpLogErrorCreateCommand create(String traceId, String content) {
        OpLogErrorCreateCommand opLogErrorCreateCommand = new OpLogErrorCreateCommand();
        opLogErrorCreateCommand.traceId = traceId;
        opLogErrorCreateCommand.content = content;
        return opLogErrorCreateCommand;
    }
}
