package com.particle.oplog.client.error.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Gt;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 操作异常日志 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@OrderBy(value = "errorAt",asc = false)
@Data
@Schema
public class OpLogErrorPageQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "日志追踪id")
    private String traceId;

    @Schema(description = "操作用户id")
    private Long userId;

    @Like
    @Schema(description = "操作用户姓名,左前缀匹配")
    private String userName;

    @Like
    @Schema(description = "操作用户昵称,左前缀匹配")
    private String userNickname;

    @Like
    @Schema(description = "请求地址,左前缀匹配")
    private String requestUrl;


    @Schema(description = "请求方法")
    private String requestMethod;


    @Schema(description = "请求ip")
    private String requestIp;


    @Schema(description = "响应的状态码")
    private Integer responseStatus;


    @Schema(description = "本地主机ip")
    private String localHostIp;


    @Schema(description = "本地主机名称")
    private String localHostName;


    @Schema(description = "异常发生时间")
    private LocalDateTime errorAt;

    @Gt("errorAt")
    @Schema(description = "异常发生时间开始")
    private LocalDateTime errorAtStart;
}
